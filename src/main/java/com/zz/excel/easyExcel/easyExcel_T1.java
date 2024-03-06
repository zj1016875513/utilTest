package com.zz.excel.easyExcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class easyExcel_T1 {
    @Test
    public void test() throws IOException {
        // 文件输出位置
        String path = "D:/CodeProject/JavaProject/myProject/utilTest/src/main/java/com/zz/excel/excel_test_data/test.xlsx";
        OutputStream out = new FileOutputStream(path);
        ExcelWriter writer = EasyExcelFactory.write(out).build();

        // 动态添加表头，适用一些表头动态变化的场景
        WriteSheet sheet1 = new WriteSheet();
        sheet1.setSheetName("商品明细");
        sheet1.setSheetNo(0);
        // 创建一个表格，用于 Sheet 中使用
        WriteTable table = new WriteTable( );
        table.setTableNo(1);
        table.setHead(head());
        // 写数据
        writer.write(contentData(), sheet1, table);
        writer.finish();
        out.close();
    }

    private static List <List<String>> head(){
        List<List<String>> headTitles = Lists.newArrayList();
        String basicInfo = "基础资料",skuInfo = "商品扩展",orderInfo = "经营情况",empty = " ";
        //第一列，1/2/3行
        headTitles.add( Lists.newArrayList(basicInfo ,basicInfo,"类别") );
        //第二列，1/2/3行
        headTitles.add( Lists.newArrayList(basicInfo,basicInfo,"名称" ) );
        List<String>  skuTitles = Lists.newArrayList("组合商品", "上一次优惠时间", "销售次数", "库存", "价格");
        skuTitles.forEach(title->{
            headTitles.add( Lists.newArrayList(skuInfo ,skuInfo,title) );
        });
        List<Integer> monthList = Lists.newArrayList(5,6);
        //动态根据月份生成
        List<String> orderSpeaces = Lists.newArrayList("销售额", "客流", "利润");
        monthList.forEach(month->{
            orderSpeaces.forEach(title->{
                headTitles.add( Lists.newArrayList(orderInfo ,  month+"月" ,title ) );
            });
        });
        //无一、二行标题
        List<String> lastList = Lists.newArrayList("日均销售金额(元)", "月均销售金额(元)" );
        lastList.forEach(title->{
            headTitles.add( Lists.newArrayList(empty , empty ,title ) );
        });
        return headTitles;
    }

    private static List <List<Object>> contentData(){
        List<List<Object>> contentList = Lists.newArrayList();
        //这里一个List<Object>才代表一行数据，需要映射成每行数据填充，横向填充（把实体数据的字段设置成一个List<Object>）
        contentList.add( Lists.newArrayList("测试", "商品A","苹果🍎") );
        contentList.add( Lists.newArrayList("测试", "商品B","橙子🍊") );
        return contentList;
    }

}
