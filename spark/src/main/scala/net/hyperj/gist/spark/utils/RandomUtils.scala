package net.hyperj.gist.spark.utils

import scala.util.Random

object RandomUtils {

  def randomString(count: Int, length: Int): Seq[String] = {
    count match {
      case 1 => Seq(Random.nextString(length))
      case _ => Seq(Random.nextString(length)) ++ randomString(count - 1, length)
    }
  }

  def randomInt(count: Int, length: Int): Seq[Int] = {
    count match {
      case 1 => Seq(Random.nextInt(length))
      case _ => Seq(Random.nextInt(length)) ++ randomInt(count - 1, length)
    }
  }

}
