package com.github.xjs.error.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static void main(String[] args)throws Exception {
        String url = "http://localhost:8080/hello";
        HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
        conn.addRequestProperty("Accept", "application/json");
        int code = conn.getResponseCode();
        System.out.println("responseCode:" + code);
        InputStream inputStream = conn.getErrorStream();
        String response = readInputStream(inputStream);
        System.out.println(response);
    }

    public static String readInputStream(InputStream inputStream)throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buff)) >= 0){
            out.write(buff, 0 , len);
        }
        inputStream.close();
        return new String(out.toByteArray());
    }
}
