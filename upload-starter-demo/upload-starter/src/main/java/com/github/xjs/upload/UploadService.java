package com.github.xjs.upload;

import java.io.File;
import java.io.InputStream;

public interface UploadService {
    /***
     * 上传返回url
     */
    String upload(InputStream in, String filename);

    String upload(byte[] bytes, String filename);

    String upload(File file);

}
