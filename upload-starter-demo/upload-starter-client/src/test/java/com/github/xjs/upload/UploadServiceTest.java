package com.github.xjs.upload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;

@SpringBootTest
public class UploadServiceTest {

    @Autowired
    private UploadService uploadService;

    @Test
    public void testUpload() throws Exception{
        FileInputStream in = new FileInputStream("d:\\10.png");
        String url = uploadService.upload(in, "10.png");
        System.out.println(url);
        in.close();
    }

}