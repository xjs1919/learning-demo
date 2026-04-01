package com.github.xjs.controller;

import com.github.xjs.service.ReportTypeService;
import com.github.xjs.entity.h2.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportTypeService reportTypeService;

    @GetMapping("/{id}")
    public ReportType getById(@PathVariable("id")Long id){
        return reportTypeService.getById(id);
    }

}
