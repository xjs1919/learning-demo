package com.github.xjs.upload.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnClass(OSS.class)
@ConditionalOnProperty(name = "upload.service", havingValue = "oss")
public class OssAutoConfiguration {

    @Autowired
    private  OssProperties ossProperties;

    @Bean
    public OSS ossClient(){
        return new OSSClientBuilder().build(ossProperties.getEndpoint(),
                ossProperties.getAccessKey(),
                ossProperties.getSecretKey());
    }

    @Bean
    public OssService ossService(){
        return new OssService(ossProperties, ossClient());
    }
}
