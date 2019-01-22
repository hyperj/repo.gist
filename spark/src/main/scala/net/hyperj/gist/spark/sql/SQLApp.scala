package net.hyperj.gist.spark.sql

import com.yahoo.sketches.frequencies.{ErrorType, ItemsSketch}
import net.hyperj.gist.spark.utils.RandomUtils._
import org.apache.spark.sql.SparkSession
import org.scalatest._

class SqlApp extends FunSuite {

  val spark = SparkSession.builder()
    .appName(getClass.getSimpleName)
    .master("local[*]")
    .getOrCreate()
  val sqlContext = spark.sqlContext

  test("pair to items") {
    import sqlContext.implicits._
    val pair = Seq(Seq(Pair("key11", "value11"), Pair("key12", "value12")),
      Seq(Pair("key21", "value21"), Pair("key22", "value22")))
      .toDF("pair")
    // flatMap
    pair
      .select($"pair")
      .as[Seq[Pair]]
      .flatMap(_.toSeq)
      .toDF()
      .show
    import org.apache.spark.sql.functions._
    // explode
    pair
      .withColumn("temp", explode($"pair"))
      .withColumn("key", $"temp.key")
      .withColumn("value", $"temp.value")
      .select("key", "value")
      .show
  }

  test("bucket check") {
    import sqlContext.implicits._
    val df = randomInt(1 << 12, 20).toDF("id").repartition(10, $"id")
    df.foreachPartition(rows => {
      val sketch = new ItemsSketch[String](64)
      rows.map(_.get(0).toString).foreach(sketch.update)
      val result = sketch.getFrequentItems(ErrorType.NO_FALSE_POSITIVES)
      result.foreach(row => println(row.toString))
    })
    df.foreachPartition(rows => {
      val sketch = new ItemsSketch[String](64)
      rows.map(_.get(0).toString).foreach(sketch.update)
      val result = sketch.getFrequentItems(ErrorType.NO_FALSE_NEGATIVES)
      result.foreach(row => println(row.toString))
    })
  }


  test("approx percentile & count distinct") {
    import org.apache.spark.sql.functions._
    import sqlContext._
    import sqlContext.implicits._
    val percentiles = sql(
      raw""" select percentile_approx(cast(item as double), array(.0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0), 10000)
           |   from lateral view explode(array(.0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0, .0, .0, .2, .2, .4, .4, .6, .6, .8, .8, 1.0)) t as item
         """.stripMargin).head().getSeq[Double](0).distinct
    val df = Seq(.0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0, .0, .0, .2, .2, .4, .4, .6, .6, .8, .8, 1.0)
      .toDF("id").cache()
    for ((it, in) <- percentiles.zipWithIndex) {
      if (in < percentiles.length - 1) {
        df.where("id between " + percentiles(in) + " and " + percentiles(in + 1)).select(approx_count_distinct($"id")).show
      }
    }

  }

  test("explain") {
    import spark.implicits._
    val ds = Seq(1, 2, 3).toDS()
    val res = ds.filter(_ > 0).map(_ + 1)
    println("-" * 64)
    println(res.explain(true))
    println("-" * 64)
    println(res.queryExecution.sparkPlan.prettyJson)
  }

}

case class Pair(key: String, value: String)

