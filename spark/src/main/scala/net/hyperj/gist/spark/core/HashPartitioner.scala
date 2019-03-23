package net.hyperj.gist.spark.core

import org.apache.spark.sql.SparkSession
import org.apache.spark.{Partitioner, TaskContext}


class HashPartitioner(partitions: Int) extends Partitioner {
  require(partitions >= 0, s"Number of partitions ($partitions) cannot be negative.")

  def numPartitions: Int = partitions

  def getPartition(key: Any): Int = key match {
    case null => 0
    case _ => nonNegativeMod(key.hashCode, numPartitions)
  }

  private def nonNegativeMod(x: Int, mod: Int): Int = {
    val rawMod = x % mod
    rawMod + (if (rawMod < 0) mod else 0)
  }

  override def equals(other: Any): Boolean = other match {
    case h: HashPartitioner =>
      h.numPartitions == numPartitions
    case _ =>
      false
  }

  override def hashCode: Int = numPartitions
}

object HashPartitionerTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
    val words = spark.sparkContext
      .textFile(getClass.getClassLoader.getResource("log4j.properties").getFile)
      .map(_.toLowerCase.replaceAll("[^a-zA-Z]", " "))
      .flatMap(_.split(" "))
      .filter(!_.isEmpty)
      .map((_, 1))
      .reduceByKey(_ + _)
      .map(item => (item._2, item._1))
    implicit val caseInsensitiveOrdering = new Ordering[Int] {
      override def compare(a: Int, b: Int) = b.compareTo(a)
    }
    words.repartitionAndSortWithinPartitions(new HashPartitioner(5))
      .foreachPartition(items => {
        println(TaskContext.getPartitionId + ": " + items.mkString(", "))
      })
  }
}