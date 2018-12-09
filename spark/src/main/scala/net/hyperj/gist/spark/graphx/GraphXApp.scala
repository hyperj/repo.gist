package net.hyperj.gist.spark

import org.apache.spark.graphx.{Edge, EdgeDirection, Graph, Pregel}
import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

import scala.reflect.ClassTag

class GraphXApp extends FunSuite {

  val spark = SparkSession.builder()
    .appName(getClass.getSimpleName)
    .master("local[*]")
    .getOrCreate()
  val sc = spark.sparkContext

  val maxIterations = 1 << 4

  def parents[VD: ClassTag, ED: ClassTag](graph: Graph[VD, ED], maxIterations: Int = maxIterations): Graph[String, ED] = {
    val bfsGraph = graph.mapVertices((id, _) => id.toString)
    Pregel(bfsGraph, "", maxIterations, EdgeDirection.In)(
      (_, attr, msg) => if (msg.isEmpty) attr else (attr + "," + msg).split(",").toSet.mkString(","),
      edge => if (edge.dstAttr.isEmpty) Iterator.empty else Iterator((edge.srcId, edge.dstAttr)),
      (a, _) => a)
  }

  def levels[VD: ClassTag, ED: ClassTag](graph: Graph[VD, ED], id: Long, maxIterations: Int = maxIterations): Graph[Int, ED] = {
    val bfsGraph = graph.mapVertices((_id, _) => if (_id == id) 0 else -1)
    Pregel(bfsGraph, -1, maxIterations, EdgeDirection.Out)(
      (_, attr, msg) => math.max(attr, msg),
      edge => if (edge.srcAttr != -1) Iterator((edge.dstId, edge.srcAttr + 1)) else Iterator.empty,
      (a, b) => math.max(a, b)
    )
  }

  test("parent & level") {
    val vertexes = sc.parallelize(
      Seq(
        (1L, "1"), (2L, "1"), (3L, "1"), (4L, "1"),
        (5L, "1"), (6L, "1"), (7L, "1"), (8L, "1"),
        (9L, "0"), (10L, "0"), (11L, "0"), (12L, "0")
      ), 1)
    val edges = sc.parallelize(
      Seq(
        Edge(12L, 11L, ("p")), Edge(12L, 10L, ("p")), Edge(11L, 9L, ("p")),
        Edge(10L, 8L, ("p")), Edge(9L, 7L, ("p")), Edge(7L, 5L, ("p")),
        Edge(5L, 2L, ("p")), Edge(8L, 2L, ("p"))
      ), 1)
    val graph = Graph.apply(vertexes, edges)
    // parent
    val parent = parents(graph)
    println(parent.vertices.filter(i => i._1.toString != i._2).collect().mkString("\n"))
    println("-" * (2 << 4))
    // level
    val level = levels(graph, 12L)
    println(level.vertices.filter(_._2 >= 0).collect().mkString("\n"))
  }

}
