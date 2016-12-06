package com.kaishengit;

import com.google.gson.Gson;
import com.kaishengit.entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {

        User user = new User(1,"李四","北京");
        User user1 = new User(12,"王明明","上海");
        User user2 = new User(23,"赵丽丽","郑州");

        List<User> userList = Arrays.asList(user,user1,user2);

        Gson gson = new Gson();
        String json = gson.toJson(userList);

        System.out.println(json);


        /*List<String> names = Arrays.asList("jack","rose","alex");
        String json = new Gson().toJson(names);

        System.out.println(json);*/


        /*User user = new User(1,"李四","北京");

        String json = new Gson().toJson(user);
        System.out.println(json);*/




       /* CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/save");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("userName","jack"));
        params.add(new BasicNameValuePair("address","usa"));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        for(int i = 0;i < 5;i++) {
            httpClient.execute(httpPost);
        }*/





       /* CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg");
        HttpResponse response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode() == 200) {
            InputStream inputStream = response.getEntity().getContent();
            OutputStream outputStream = new FileOutputStream("D:/x.jpg");

            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }

        httpClient.close();*/



















       /* //创建了一个可以发出请求的客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个get请求方式
        HttpGet httpGet = new HttpGet("http://www.kaishengit.com");
        //发出请求并接受服务端的响应
        HttpResponse response = httpClient.execute(httpGet);

        //获取HTTP响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode == 200) {
            //获取响应输入流
            InputStream inputStream = response.getEntity().getContent();

            String result = IOUtils.toString(inputStream,"UTF-8");

            inputStream.close();

            System.out.println(result);



        } else {
            System.out.println("服务器异常:" + statusCode);
        }

        httpClient.close();*/

    }
}

