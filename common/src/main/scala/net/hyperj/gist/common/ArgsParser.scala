package net.hyperj.gist.common

import org.scalatest.FunSuite

class ArgsParser extends FunSuite {

  def parser(args: Seq[String], split: String = "="): Map[String, String] = {
    if (args.isEmpty) return Map.empty
    args
      .map(_.split(split))
      .filter(_.length == 2)
      .map(i => (i(0), i(1)))
      .toMap[String, String]
  }

  test("args parser") {
    println(parser(Seq("a=1", "b", "c=d=3")).toString())
    println(parser(Seq("a:1", "b", "c:d:3"), ":").toString())
  }

}