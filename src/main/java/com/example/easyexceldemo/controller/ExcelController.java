package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExcelController {
    ExcelService excelService;

    @Autowired
    public void setExcelService(ExcelService excelService) {
        this.excelService = excelService;
    }


    @GetMapping("/excel")
    public String excel() throws IOException {
        // excelService.generateExcel();
        return "hello";
    }
}
