package com.github.xjs.simple.apollo.util;

import com.google.common.base.Function;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 13:44
 */
public class HttpUtil {

    private static HttpUtil INSTANCE = new HttpUtil();

    private HttpUtil(){
        gson = new Gson();
    }

    public static HttpUtil getInstance(){
        return INSTANCE;
    }

    private Gson gson;

    /**
     * Do get operation for the http request.
     *
     * @param httpRequest  the request
     * @param responseType the response type
     * @return the response
     */
    public <T> HttpResponse<T> doGet(HttpRequest httpRequest, final Class<T> responseType) {
        Function<String, T> convertResponse = new Function<String, T>() {
            @Override
            public T apply(String input) {
                return gson.fromJson(input, responseType);
            }
        };

        return doGetWithSerializeFunction(httpRequest, convertResponse);
    }

    /**
     * Do get operation for the http request.
     *
     * @param httpRequest  the request
     * @param responseType the response type
     * @return the response
     */
    public <T> HttpResponse<T> doGet(HttpRequest httpRequest, final Type responseType) {
        Function<String, T> convertResponse = new Function<String, T>() {
            @Override
            public T apply(String input) {
                return gson.fromJson(input, responseType);
            }
        };

        return doGetWithSerializeFunction(httpRequest, convertResponse);
    }

    private <T> HttpResponse<T> doGetWithSerializeFunction(HttpRequest httpRequest,
                                                           Function<String, T> serializeFunction) {
        InputStreamReader isr = null;
        InputStreamReader esr = null;
        int statusCode;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(httpRequest.getUrl()).openConnection();

            conn.setRequestMethod("GET");

            Map<String, String> headers = httpRequest.getHeaders();
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            int connectTimeout = 3000;

            int readTimeout = 5000;

            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            conn.connect();

            statusCode = conn.getResponseCode();
            String response;

            try {
                isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                response = CharStreams.toString(isr);
            } catch (IOException ex) {
                InputStream errorStream = conn.getErrorStream();
                if (errorStream != null) {
                    esr = new InputStreamReader(errorStream, StandardCharsets.UTF_8);
                    try {
                        CharStreams.toString(esr);
                    } catch (IOException ioe) {
                        //ignore
                    }
                }

                // 200 and 304 should not trigger IOException, thus we must throw the original exception out
                if (statusCode == 200 || statusCode == 304) {
                    throw ex;
                }
                // for status codes like 404, IOException is expected when calling conn.getInputStream()
                throw new RuntimeException(ex);
            }

            if (statusCode == 200) {
                return new HttpResponse<>(statusCode, serializeFunction.apply(response));
            }

            if (statusCode == 304) {
                return new HttpResponse<>(statusCode, null);
            }
        } catch (Throwable ex) {
            throw new RuntimeException("Could not complete get operation", ex);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ex) {
                    // ignore
                }
            }

            if (esr != null) {
                try {
                    esr.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }
        throw new RuntimeException(String.format("Get operation failed for %s", httpRequest.getUrl()));
    }
}
