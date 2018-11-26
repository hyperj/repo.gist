package net.hyperj.gist.spark.sql

object ArgsParser {

  def parser(args: Array[String]): Map[String, String] = {
    var map = Map.empty[String, String]
    for (arg <- args) {
      val key_val = arg.split(":")
      if (key_val.length >= 2)
        map += (key_val(0) -> key_val(1))
    }
    map
  }

}