package net.hyperj.gist.flink.table

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.{Table, TableEnvironment}

object TableExample {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val tEnv = TableEnvironment.getTableEnvironment(env)
    val ds = env.fromElements((123, "apache", 1), (456, "flink", 1), (789, null, 2))
    tEnv.registerDataSet("table_temp", ds, 'id, 'name, 'type)
    val table_temp: Table = tEnv.scan("table_temp")
    import net.hyperj.gist.flink.helper.TableHelper._
    table_temp
      .filter('id.isNotNull && 'name.isNotNull)
      .groupBy('type)
      .select('id.sum as 'sum, 'name.max as 'name, 'type)
      .show
  }
}