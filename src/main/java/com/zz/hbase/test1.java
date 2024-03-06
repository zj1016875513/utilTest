package com.zz.hbase;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.spark.FamilyHFileWriteOptions;
import org.apache.hadoop.hbase.spark.JavaHBaseContext;
import org.apache.hadoop.hbase.spark.KeyFamilyQualifier;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.hadoop.hbase.util.Pair;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.junit.Test;
import scala.Tuple4;

import java.util.ArrayList;
import java.util.HashMap;

public class test1 {

@Test
public void test1(){

}

public static void bulkLoad(JavaSparkContext jsc, Configuration hbConf, String table, String cf, String input, String output){
    JavaRDD<Tuple4<byte[], byte[], byte[], byte[]>> rdd1 = jsc.textFile(input).mapPartitions(x -> {
        ArrayList<Tuple4<byte[], byte[], byte[], byte[]>> hFileList = new ArrayList<>();
        while (x.hasNext()) {
            String next = x.next();
            JSONObject jsonObject = JSONObject.parseObject(next);
            String docId = jsonObject.getString("docId");
            for (String key : jsonObject.keySet()) {
                if ("docId".equals(key)) {
                    continue;
                }
                String value = jsonObject.getString(key);
                hFileList.add(new Tuple4<>(docId.getBytes(), Bytes.toBytes(cf), Bytes.toBytes(key), Bytes.toBytes(value)));
            }
        }
        return hFileList.iterator();
    });

    JavaHBaseContext hbaseContext = new JavaHBaseContext(jsc, hbConf);

    HashMap<byte[], FamilyHFileWriteOptions> fm = new HashMap<>();

    fm.put(Bytes.toBytes(cf), new FamilyHFileWriteOptions("ZSTD","ROW",262114,"FAST_DIFF"));

    hbaseContext.bulkLoad(rdd1, TableName.valueOf(table),new BulkLoadFunction(),output,fm,false, HConstants.DEFAULT_MAX_FILE_SIZE);

}

static class BulkLoadFunction implements Function<Tuple4<byte[], byte[], byte[], byte[]>, Pair<KeyFamilyQualifier, byte[]>> {
    @Override
    public Pair<KeyFamilyQualifier, byte[]> call(Tuple4<byte[], byte[], byte[], byte[]> tuple4) {
        KeyFamilyQualifier kfq = new KeyFamilyQualifier(tuple4._1(),tuple4._2(),tuple4._3());
        return new Pair(kfq, tuple4._4());
    }
}

}
