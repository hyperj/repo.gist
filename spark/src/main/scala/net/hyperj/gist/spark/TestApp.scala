package net.hyperj.gist.spark

import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

class TestApp extends FunSuite {

  test("main test") {
    val spark = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
  }

}
