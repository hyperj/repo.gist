package net.hyperj.gist.spark.sketch

import org.apache.spark.util.sketch.CountMinSketch
import org.scalatest.FunSuite
import net.hyperj.gist.spark.utils.RandomUtils._

import scala.util.Random

class SketchApp extends FunSuite {

  test("CountMinSketch") {
    val eps = 0.1
    val confidence = 0.9
    val seed = 1024
    val nums = Random.shuffle(0 until 1024 toList).take(128)
    val numRef = CountMinSketch.create(eps, confidence, seed)
    nums.foreach(numRef.add)
    println(numRef.estimateCount(nums(0)))
    println(numRef.totalCount())
    val strs = randomString(128, 5)
    val strRef = CountMinSketch.create(eps, confidence, seed)
    strs.foreach(strRef.add)
    println(strRef.estimateCount(strs(0)))
    println(strRef.totalCount())
  }


}
