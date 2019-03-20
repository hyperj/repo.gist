package net.hyperj.gist.flink.streaming.join

import java.io.Serializable
import java.util.Random

import org.apache.flink.streaming.api.scala._
import net.hyperj.gist.flink.streaming.join.WindowJoin.{Grade, Salary}
import net.hyperj.gist.flink.streaming.utils.ThrottledIterator
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}


import scala.collection.JavaConverters._
/**
  * Sample data for the [[WindowJoin]] example.
  */
object WindowJoinSampleData {

  private[streaming] val NAMES = Array("tom", "jerry", "alice", "bob", "john", "grace")
  private[streaming] val GRADE_COUNT = 5
  private[streaming] val SALARY_MAX = 10000

  /**
    * Continuously generates (name, grade).
    */
  def getGradeSource(env: StreamExecutionEnvironment, rate: Long): DataStream[Grade] = {
    env.fromCollection(new ThrottledIterator(new GradeSource().asJava, rate).asScala)
  }

  /**
    * Continuously generates (name, salary).
    */
  def getSalarySource(env: StreamExecutionEnvironment, rate: Long): DataStream[Salary] = {
    env.fromCollection(new ThrottledIterator(new SalarySource().asJava, rate).asScala)
  }

  // --------------------------------------------------------------------------

  class GradeSource extends Iterator[Grade] with Serializable {

    private[this] val rnd = new Random(hashCode())

    def hasNext: Boolean = true

    def next: Grade = {
      Grade(NAMES(rnd.nextInt(NAMES.length)), rnd.nextInt(GRADE_COUNT) + 1)
    }
  }

  class SalarySource extends Iterator[Salary] with Serializable {

    private[this] val rnd = new Random(hashCode())

    def hasNext: Boolean = true

    def next: Salary = {
      Salary(NAMES(rnd.nextInt(NAMES.length)), rnd.nextInt(SALARY_MAX) + 1)
    }
  }

}
