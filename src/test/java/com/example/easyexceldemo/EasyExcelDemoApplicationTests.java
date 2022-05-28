package com.example.easyexceldemo;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.example.easyexceldemo.domain.Bill;
import com.example.easyexceldemo.domain.BillItem;
import com.example.easyexceldemo.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class EasyExcelDemoApplicationTests {
	ExcelService excelService;

	@Autowired
	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}

	@Test
	void contextLoads() throws IOException {
		Bill bill = new Bill();
		bill.setSeikyuPostNo("265500");
		bill.setSeikyuAddress("钻石湾");
		bill.setSeikyuName("老公");
		bill.setUserCompanyName("东软");
		bill.setCompanyPostNo("99999");
		bill.setCompanyAddress("河口软件园");
		bill.setTaxRate(0.175);
		List<BillItem> billList = new ArrayList<>();
		double itemTotalAmount = 0;
		double taxTotalAmount = 0;
		for (int i = 0; i < 20; i++) {
			BillItem billItem = new BillItem();
			billItem.setUriageYMD(new Date());
			billItem.setManiNO("100" + i);
			billItem.setUriageItem("发票" + i);
			billItem.setCarName("特斯拉" + i);
			billItem.setCarCount(2 + i);
			billItem.setUnit("私家车" + i);
			billItem.setUriagePrice(147.56 + i);
			double amount = 5600.03 + i;
			billItem.setUriageAmount(amount);
			billItem.setBiko("备注" + i);
			billList.add(billItem);
			synchronized (this){
				itemTotalAmount += amount;
				taxTotalAmount += amount * bill.getTaxRate();
			}
		}
		bill.setBillList(billList);
		bill.setItemTotalAmount(itemTotalAmount);
		bill.setTaxTotalAmount(taxTotalAmount);
		excelService.generateExcel(bill);
	}

}
