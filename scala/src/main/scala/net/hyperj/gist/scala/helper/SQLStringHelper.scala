package net.hyperj.gist.scala.helper

object SQLStringHelper {

  implicit class StringContextHelper(val sc: StringContext) {
    def sql(args: Any*): String = sc.standardInterpolator(identity, args)
  }

}

object SQLStringHelperTest {

  import SQLStringHelper._

  def main(args: Array[String]): Unit = {
    println(
      sql""" select cast("1" as int) as int_type,
           |        get_json_object('{"a":1}', '$$.a') as json_value
           |""".stripMargin)
  }

}