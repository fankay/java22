package com.kaishengit.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpUtil {

    public static String sendHttpGetRequestWithString(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);

            InputStream inputStream = null;
            if (response.getStatusLine().getStatusCode() == 200) {
                inputStream = response.getEntity().getContent();

                String result =  IOUtils.toString(inputStream,"UTF-8");
                httpClient.close();
                return result;
            } else {
                throw new RuntimeException("请求" + url + "异常 : " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException ex) {
            throw new RuntimeException("请求" + url + "异常",ex);
        }
    }


}
