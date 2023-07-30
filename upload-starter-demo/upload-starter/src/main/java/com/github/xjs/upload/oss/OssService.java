package com.github.xjs.upload.oss;

import com.github.xjs.upload.AbstractUploadService;

import java.io.InputStream;

public class OssService extends AbstractUploadService {

    @Override
    protected String doUpload(InputStream in, String filename, String contentType){
        return null;
    }
}
