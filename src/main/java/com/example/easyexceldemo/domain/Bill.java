package com.example.easyexceldemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Bill {
    String seikyuPostNo;
    String seikyuAddress;
    String seikyuName;
    String userCompanyName;
    String companyPostNo;
    String companyAddress;
    List<BillItem> billList;
    Double itemTotalAmount;
    Double taxRate;
    Double taxTotalAmount;
}
