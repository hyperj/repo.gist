package net.hyperj.gist.scala.basic

import java.io.{FileNotFoundException, FileReader}

import org.scalatest.FunSuite

class Basic extends FunSuite {

  test("for") {
    for ((x, y) <- for (i <- 0 to 9 if i % 3 == 0; j = 9 - i; k <- j until 9) yield (k, k * k)) println(x + " -> " + y)
  }

  test("string") {
    val name = "James"
    val height = 1.9d
    println(s"Hello,$name")
    println(f"$name%s is $height%2.2f meters tall")
    println(s"1+1=${1 + 1}")
    println(raw"a\nb")
  }

  test("collection") {
    println(Array("scala") ++ Array("spark", "flink") mkString ",")
    println(List("scala") ::: "spark" :: "flink" :: Nil)
    println(Set("scala") ++ Set("spark", "flink"))
    println(Map("0" -> "scala") ++ Map("1" -> "spark", "2" -> "flink"))
  }

  test("try catch finally") {
    try {
      new FileReader("file.txt")
    } catch {
      case _: FileNotFoundException => {
        println("FileNotFoundException")
      }
      case _: Exception => {
        println("Exception")
      }
    } finally {
      println("finally...")
    }
  }

}
