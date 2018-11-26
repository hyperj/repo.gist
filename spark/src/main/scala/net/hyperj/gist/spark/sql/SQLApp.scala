package net.hyperj.gist.spark.sql

import org.apache.spark.sql.SparkSession

object SQLApp {

  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
    import spark.sqlContext.implicits._
    val pairs = Seq(Seq(Pair("key11", "value11"), Pair("key12", "value12")),
      Seq(Pair("key21", "value21"), Pair("key22", "value22")))
      .toDF("pairs")
    // flatMap
    pairs
      .select($"pairs")
      .as[Seq[Pair]]
      .flatMap(_.toSeq)
      .toDF()
      .show
    import org.apache.spark.sql.functions._
    // explode
    pairs
      .withColumn("pair_temp", explode($"pairs"))
      .withColumn("key", $"pair_temp.key")
      .withColumn("value", $"pair_temp.value")
      .select("key", "value")
      .show
  }

}

case class Pair(key: String, value: String)
