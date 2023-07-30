package com.github.xjs.upload;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

public abstract class AbstractUploadService implements UploadService{

    @Override
    public String upload(byte[] bytes, String filename) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try{
            return upload(inputStream, filename);
        }finally {
            try{
                inputStream.close();
            }catch (Exception e){}
        }
    }

    @Override
    public String upload(File file) {
        String filename = file.getName();
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            return upload(inputStream, filename);
        }catch (Exception e){
            throw  new RuntimeException(e);
        } finally {
            try{
                inputStream.close();
            }catch (Exception e){}
        }
    }

    @Override
    public String upload(InputStream in, String filename) {
        // 获取文件类型
        String contentType = getContentType(filename);
        // 合法性校验
        boolean check = check(in, filename, contentType);
        if(!check){
            throw new RuntimeException("上传不合法");
        }
        // 上传,返回url
        return doUpload(in, filename, contentType);
    }

    protected abstract String doUpload(InputStream in, String filename, String contentType);

    private String getContentType(String filename){
        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(filename);
        if(mediaType.isPresent()){
            return mediaType.get().toString();
        }
        throw new RuntimeException("无法获取文件类型");
    }
    
    private  boolean check(InputStream in, String filename, String contentType){
        return true;
    }



}
