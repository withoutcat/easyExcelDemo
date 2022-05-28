package com.example.easyexceldemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BillItem {
    // @ExcelProperty("日　付")
    Date uriageYMD;

    // @ExcelProperty("マニNo.")
    String maniNO;

    // @ExcelProperty("品　目")
    String uriageItem;

    // @ExcelProperty("車　種")
    String carName;

    // @ExcelProperty("数 量")
    Integer carCount;

    // @ExcelProperty("単 位")
    String unit;

    // @ExcelProperty("単　価")
    Double uriagePrice;

    // @ExcelProperty("金　額")
    Double uriageAmount;

    // @ExcelProperty("備　考")
    String biko;
}
