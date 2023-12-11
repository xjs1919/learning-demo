package com.github.xjs.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.github.xjs.pojos.DetailDTO;
import com.github.xjs.pojos.User;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, User.class, new PageReadListener<User>(dataList -> {
            for (User user : dataList) {
                System.out.println(user);
                //TODO 把数据写入到数据库
            }
        })).sheet().doRead();
        return "上传成功";
    }

    @GetMapping("/download/template")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        // 下载excel
        String fileName = "用户信息.xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("用户信息.xlsx");
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        //TODO 数据可以从数据库读
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("张三");
        user1.setAge(20);
        user1.setEmail("zhangsan@qq.com");
        userList.add(user1);
        User user2 = new User();
        user2.setName("李四");
        user2.setAge(25);
        user2.setEmail("lisi@qq.com");
        userList.add(user2);
        // 下载excel
        String fileName = "用户信息.xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户信息").doWrite(userList);
    }

    @GetMapping("/export/use/template")
    public void downloadUseTemplate(HttpServletResponse response) throws Exception {
        // 下载excel
        String fileName = "使用模板导出.xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 加载模板
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        // 输出到response
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .withTemplate(inputStream)
                .excelType(ExcelTypeEnum.XLSX).build();

        // 得到sheet
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // 单个字段填充
        Map<String, Object> map = new HashMap<>();
        map.put("weekAdd",100);
        map.put("monthAdd",200);
        map.put("total",300);
        excelWriter.fill(map, writeSheet);

        // 列表竖向填充
        List<DetailDTO> details = new ArrayList<>();
        details.add(new DetailDTO(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-10"), 100));
        details.add(new DetailDTO(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11"), 80));
        details.add(new DetailDTO(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-12"), 200));
        excelWriter.fill(new FillWrapper("details", details), writeSheet);

        // 列表横向填充
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        excelWriter.fill(new FillWrapper("week", details),fillConfig, writeSheet);

        excelWriter.finish();

    }


}
