package com.github.xjs.upload.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnClass(MinioClient.class)
@ConditionalOnProperty(name = "upload.service", havingValue = "minio")
public class MinioAutoConfiguration {

    @Autowired
    private  MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.
                builder()
                .credentials(minioProperties.getUsername(), minioProperties.getPassword())
                .endpoint(minioProperties.getEndpoint())
                .build();

    }

    @Bean
    public MinioService minioService(){
        return new MinioService(minioProperties, minioClient());
    }
}
