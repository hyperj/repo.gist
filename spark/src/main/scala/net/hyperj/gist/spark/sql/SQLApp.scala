package net.hyperj.gist.spark.sql

import org.apache.spark.sql.SparkSession

case class Struct2(key: String, value: String)

object SQLApp {
  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .master("local[*]")
      .getOrCreate()
    import spark.sqlContext.implicits._
    val pairs = Seq(Seq(Struct2("key11", "value11"), Struct2("key12", "value12")),
      Seq(Struct2("key21", "value21"), Struct2("key22", "value22")))
      .toDF("pairs")
    // flatMap
    pairs
      .select($"pairs")
      .as[Seq[Struct2]]
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