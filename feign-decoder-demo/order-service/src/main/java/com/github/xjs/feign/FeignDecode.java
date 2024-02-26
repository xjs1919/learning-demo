package com.github.xjs.feign;

import com.github.xjs.pojo.R;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class FeignDecode implements Decoder, InitializingBean {
 
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
 
    private ResponseEntityDecoder responseEntityDecoder;
 
    @Override
    public Object decode(final Response response, Type type) throws IOException, FeignException {
        Object result = responseEntityDecoder.decode(response, type);
        R baseResponse = (R) result;
        int code = baseResponse.getCode();
        if (code == 0) {
            return result;
        }
        log.error("远程调用失败:{}", baseResponse);
        throw new RuntimeException("远程调用失败");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化spring提供的解析器
        responseEntityDecoder = new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }
}