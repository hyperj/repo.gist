package net.hyperj.gist.flink.helper

import org.apache.flink.api.scala._
import org.apache.flink.table.api.Table
import org.apache.flink.types.Row
import org.apache.flink.table.api.scala._

object TableHelper {

  implicit class printTable(t: Table) {
    def show: Unit = {
      t.toDataSet[Row].print()
    }
  }

}