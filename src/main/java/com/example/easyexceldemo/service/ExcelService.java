package com.example.easyexceldemo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.example.easyexceldemo.domain.Bill;
import com.example.easyexceldemo.utils.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

@Service
public class ExcelService {
    public void generateExcel(Bill bill) throws IOException {
        File directory = new File("src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String templateExcel = reportPath + "/static/templates/bill_template.xlsx";

        String fileName = "bill" + System.currentTimeMillis() + ".xlsx";
        String billExcel = reportPath + "/static/targetExcel/" + fileName;

        System.out.println(billExcel);

        try (ExcelWriter excelWriter = EasyExcel.write(billExcel).withTemplate(templateExcel).build()) {
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个
            // WriteSheet writeSheet = EasyExcel.writerSheet().build();
            WriteSheet writeSheet = EasyExcel.writerSheet().registerWriteHandler(new MyHandler()).build();
            excelWriter.fill(bill, writeSheet);


            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(bill.getBillList(), fillConfig, writeSheet);
        }
    }
}
