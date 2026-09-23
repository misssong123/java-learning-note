package com.demo.java_web.chapter001;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 使用httpClient发送get请求
 */
public class Demo0001HttpClient {
    public static void main(String[] args) {
        String url = "https://blog.csdn.net/robinsgl/article/details/131320531";
        sendGet(url,null,null);
    }
    public static String sendGet(String url,String cookie,String token){
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            // 创建客户端连接对象
            client = HttpClients.createDefault();
            // 构建Get请求对象
            HttpGet get = new HttpGet(url);
            if (StringUtils.isNotBlank(cookie)){
                get.setHeader("cookie",cookie);
            }
            if (StringUtils.isNotBlank(token)){
                get.setHeader("token",token);
            }
            RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(3000)
                    .setSocketTimeout(20000).build();
            get.setConfig(config);
            // 获取返回对象
            response = client.execute(get);
            System.out.println(JSONObject.toJSONString(response));
            // 整理返回值
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭连接和流
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
