package com.zz.hbase

import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants, TableName}
import org.apache.hadoop.hbase.spark.{FamilyHFileWriteOptions, HBaseContext, KeyFamilyQualifier}
import org.apache.hadoop.hbase.spark.HBaseRDDFunctions.GenericHBaseRDDFunctions
import org.apache.hadoop.hbase.spark.HBaseRDDFunctions._
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.hadoop.hbase.util.Bytes

import java.io.FileInputStream
import java.util

object BulkLoad {

  def main(args: Array[String]): Unit = {

  }

  def bulkLoadToHbase(sc: SparkContext, rdd: RDD[(String, String, String)], tableName: String, hbaseColName: String, hfileOutputPath: String) = {

    val familyHBaseWriteOptions = new util.HashMap[Array[Byte], FamilyHFileWriteOptions]()

    val f1Options = new FamilyHFileWriteOptions("ZSTD", "ROW", 262114, "FAST_DIFF")

    familyHBaseWriteOptions.put(Bytes.toBytes("HBASE_COLUMN_FAMILY"), f1Options)

    val outputHBaseConf = HBaseConfiguration.create()

    outputHBaseConf addResource(new FileInputStream("hbase-site.xml"), true)
    outputHBaseConf addResource(new FileInputStream("core-site.xml"), true)
    outputHBaseConf addResource(new FileInputStream("hdfs-site.xml"), true)

    val context = new HBaseContext(sc, outputHBaseConf)

    rdd.hbaseBulkLoad(context, TableName.valueOf(tableName), item => {
      var kvList: Seq[(KeyFamilyQualifier, Array[Byte])] = List()
      val keyFamilyQualifier = new KeyFamilyQualifier(Bytes.toBytes(item._1), Bytes.toBytes("cf1"), Bytes.toBytes(""))
      kvList = kvList :+ (keyFamilyQualifier, Bytes.toBytes(item._2))
      kvList.iterator
    }, hfileOutputPath + "/" + hbaseColName, familyHBaseWriteOptions, false, HConstants.DEFAULT_MAX_FILE_SIZE)
  }


}
