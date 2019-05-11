package net.hyperj.gist.spark

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.commons.lang3.StringUtils

import scala.collection.mutable

object TestApp {

  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)

  def json_to_array(json: String): Array[String] = {
    if (StringUtils.isNotBlank(json)) {
      try {

        return mapper.readValue[Array[Object]](json).map(_.toString)
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
    Array.empty
  }

  def to_json(any: AnyRef): String = {
    if (any != null) {
      try {
        return mapper.writeValueAsString(any)
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
    ""
  }

  def json_to_path(str: String, parent: String, set: mutable.Set[String]): Unit = {
    try {
      val tree = mapper.readTree(str)
      if (tree.isArray) {
        mapper.readValue[Array[JsonNode]](str)
          .foreach(i => json_to_path(i.toString, parent + ".[", set))
      } else {
        val it = tree.fields()
        while (it.hasNext) {
          val next = it.next()
          set.add(parent + "." + next.getKey)
          json_to_path(next.getValue.toString, parent + "." + next.getKey, set)
        }
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def main(args: Array[String]): Unit = {
    val json = """[1,"a",{"z":1}]"""
    println(json_to_array(json).mkString(","))
    val map = Map("a" -> "1")
    println(to_json(map))
    val json2 ="""{"a":{"b":{"c":1}}}"""
    val set = mutable.Set[String]().empty
    json_to_path(json2, "$", set)
    println(set)
  }

}
