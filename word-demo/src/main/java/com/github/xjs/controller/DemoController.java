package com.github.xjs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haulmont.yarg.formatters.factory.DefaultFormatterFactory;
import com.haulmont.yarg.loaders.factory.DefaultLoaderFactory;
import com.haulmont.yarg.loaders.impl.JsonDataLoader;
import com.haulmont.yarg.reporting.Reporting;
import com.haulmont.yarg.reporting.RunParams;
import com.haulmont.yarg.structure.Report;
import com.haulmont.yarg.structure.ReportBand;
import com.haulmont.yarg.structure.ReportOutputType;
import com.haulmont.yarg.structure.impl.BandBuilder;
import com.haulmont.yarg.structure.impl.ReportBuilder;
import com.haulmont.yarg.structure.impl.ReportTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/generate/doc")
    public void generateDocument(HttpServletResponse response) throws IOException {
        // 加载模板
        ReportBuilder reportBuilder = new ReportBuilder();
        ReportTemplateBuilder reportTemplateBuilder = new ReportTemplateBuilder()
                .documentPath(new ClassPathResource("template/demo.docx").getURI().getPath()).documentName("demo.docx")
                .outputType(ReportOutputType.docx).readFileFromPath();
        reportBuilder.template(reportTemplateBuilder.build());

        //准备数据
        Map<String, Object> data = Map.of("date", "2024-09-03",
                "p1", 234,
                "p2", 123,
                "p3", 489,
                "p4", 789,
                "p5", 100);
        String jsonStr = new ObjectMapper().writeValueAsString(data);

        //定义数据组，简单理解就是在上一步中定义的数据如何能正确的替换word模板中的占位符。
        // 这里这里的name("R")，也就是在word模板中使用的R；
        // 接着是定义了如何从json中查询数据query，parameter=p这里是定义查询查询参数p，而这个p一会在最后生成报表时你将看到这里的p；
        // $表示json-path的根路径，从根路径查找每一个属性值。
        BandBuilder bandBuilder = new BandBuilder();
        ReportBand reportBand = bandBuilder.name("R").query("R", "parameter=p$", "json").build();
        reportBuilder.band(reportBand);
        // 生成报告
        Report report = reportBuilder.build();
        Reporting reporting = new Reporting();
        reporting.setFormatterFactory(new DefaultFormatterFactory());
        reporting.setLoaderFactory(new DefaultLoaderFactory().setJsonDataLoader(new JsonDataLoader()));
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        // param("p", jsonStr)，分别就是上面我们的查询参数parameter=p，jsonStr就是我们准备的json数据。
        reporting.runReport(new RunParams(report).param("p", jsonStr), response.getOutputStream());
    }
}
