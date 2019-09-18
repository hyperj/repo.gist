package net.hyperj.gist.flink.table.udf

import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.functions.TableFunction

object SplitTableFunction {

  def main(args: Array[String]): Unit = {
    import org.apache.flink.table.api.TableEnvironment
    val env = ExecutionEnvironment.getExecutionEnvironment
    val tEnv = TableEnvironment.getTableEnvironment(env)
    import net.hyperj.gist.flink.helper.TableHelper._
    val split = new Split(",")
    val lines = env.fromElements(("1,3"), ("2,4")).toTable(tEnv, 'line)
    lines.join(split('line) as ('word)).select('line, 'word).show
    lines.leftOuterJoin(split('line) as ('word)).select('line, 'word).show
    tEnv.registerTable("lines", lines)
    tEnv.registerFunction("split", split)
    tEnv.sqlQuery("SELECT line,word FROM lines, LATERAL TABLE(split(line)) as T(word)").show
    tEnv.sqlQuery("SELECT line,word FROM lines LEFT JOIN LATERAL TABLE(split(line)) as T(word) ON TRUE").show
  }
}

class Split(separator: String) extends TableFunction[String] {
  def eval(str: String): Unit = {
    str.split(separator).foreach(collect)
  }
}


