package com.github.xjs.upload.oss;

import com.aliyun.oss.OSS;
import com.github.xjs.upload.AbstractUploadService;

import java.io.InputStream;
import java.util.Date;

public class OssService extends AbstractUploadService {

    private OssProperties ossProperties;
    private OSS ossClient;

    public OssService(OssProperties ossProperties, OSS ossClient) {
        this.ossClient = ossClient;
        this.ossProperties = ossProperties;
    }

    @Override
    protected String doUpload(InputStream in, String filename, String contentType){
        ossClient.putObject(ossProperties.getBucket(), filename, in);
        // 设置URL过期时间为1小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成URL
        return ossClient.generatePresignedUrl(ossProperties.getBucket(), filename, expiration).toString();
    }
}
