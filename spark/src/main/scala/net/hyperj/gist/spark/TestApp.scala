package net.hyperj.gist.spark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.commons.lang3.StringUtils
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

object TestApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
    val sqlContext = spark.sqlContext
    import sqlContext._
    @transient lazy val mapper = new ObjectMapper() with ScalaObjectMapper

    def json_array(json: String): Array[String] = {
      if (StringUtils.isNotBlank(json)) {
        try {
          return mapper.readValue[Array[Object]](json).map(_.toString)
        } catch {
          case e: Exception => e.printStackTrace()
        }
      }
      null
    }

    udf.register("json_array", (json: String) => json_array(json))
    sql(
      raw""" select item
           |   from lateral view explode(json_array('[{"asd":123},{"qwe":"sdf"}]')) t as item
         """.stripMargin).show(false)
  }
}
