package net.hyperj.gist.spark.histogrammar

import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

import scala.util.Random

class HistogrammarApp extends FunSuite {

  test("histogrammar") {
    val spark = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
    import org.dianahep.histogrammar.tutorial.cmsdata
    val events = cmsdata.EventIterator()
    val rdd = spark.sparkContext.parallelize(Random.shuffle(0 until 1000 toList).take(100))
    import org.dianahep.histogrammar._
    val empty = Bin(10, 0, 1000, { id: Int => id })
    val filled = rdd.aggregate(empty)(new Increment, new Combine)
    import org.dianahep.histogrammar.ascii._
    filled.println
  }

}
