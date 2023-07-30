package com.github.xjs.upload.minio;

import com.github.xjs.upload.AbstractUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.InputStream;

public class MinioService extends AbstractUploadService {

    private  MinioProperties minioProperties;
    private  MinioClient minioClient;

    public MinioService(MinioProperties minioProperties, MinioClient minioClient){
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    protected String doUpload(InputStream in, String filename, String contentType) {
        try{
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    //文件名, 同名覆盖，重命名uuid
                    .object(filename)
                    //文件类型
                    .contentType(contentType)
                    //桶
                    .bucket(minioProperties.getBucket())
                    //文件流
                    .stream(in, in.available(), -1)
                    .build();
            //上传
            minioClient.putObject(putObjectArgs);
            // 下载地址
           return minioProperties.getEndpoint() + "/" + minioProperties.getBucket() + "/" + filename;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("文件上传异常");
        }
    }

}
