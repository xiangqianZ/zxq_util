package com.zxq.httpclient;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author aihui075
 * @Date 2018/4/2.
 * @Description
 */
@Slf4j
public class HttpClient {
    private List<NameValuePair> list = new ArrayList<NameValuePair>();
    private String url;

    public HttpClient(String url) {
        this.url = url;
    }

    public HttpClient add(String name, String value) {
        list.add(new BasicNameValuePair(name, value));
        return this;
    }

    public JSONObject post() {
        try {
            HttpPost hp = new HttpPost(url);

            hp.setHeader(
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

            //设置期望服务端返回的编码
            hp.setHeader(new BasicHeader("Accept", "application/json"));

            hp.setEntity(new UrlEncodedFormEntity(list,"utf-8"));

            log.info("发送请求：{}，参数：{}",url,list);
            CloseableHttpClient chc = HttpClients.createDefault();
            CloseableHttpResponse chr = chc.execute(hp);
            HttpEntity he = chr.getEntity();
            String result = EntityUtils.toString(he,"utf-8");
            log.info("响应请求：{}，结果：{}",url,result);
            return JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject get() {
        try {
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            for(NameValuePair li : list) {
                sb.append(li.getName()).append("=").append(li.getValue()).append("&");
            }
            HttpGet hp = new HttpGet(sb.deleteCharAt(sb.length()-1).toString());

            hp.setHeader(
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

            log.info("发送get请求：{}",url);
            //设置期望服务端返回的编码
            hp.setHeader(new BasicHeader("Accept", "application/json"));
            CloseableHttpClient chc = HttpClients.createDefault();
            CloseableHttpResponse chr = chc.execute(hp);
            HttpEntity he = chr.getEntity();
            String result = EntityUtils.toString(he,"utf-8");
            log.info("响应请求：{}，结果：{}",url,result);
            return JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject postJson(JSONObject json) {
        try {
            CloseableHttpClient chc = HttpClients.createDefault();
            HttpPost hp = new HttpPost(url);
            StringEntity s = new StringEntity(json.toString(),"utf-8");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            hp.setEntity(s);
            log.info("发送请求：{}，参数：{}",url,json);
            CloseableHttpResponse chr = chc.execute(hp);
            HttpEntity he = chr.getEntity();
            String result = EntityUtils.toString(he);
            log.info("响应请求：{}，结果：{}",url,result);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        for(int i=26; i<28;i++) {
//            try {
//                new HttpClient("http://data.aihuizhongyi.com/sendmail?time=2018-04-"+ String.format("%02d",i)).post();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body","a:b");
        System.out.println(new HttpClient("http://cz.lihua.com/index.php?s=/AihuiApi.html?a=b").post());
//        System.out.println(new HttpClient("http://192.168.10.175:9091/getData").postJson(jsonObject));

    }
}
