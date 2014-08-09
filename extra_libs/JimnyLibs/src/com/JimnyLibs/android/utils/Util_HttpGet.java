package com.JimnyLibs.android.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by Jimny
 * on 14-7-20.
 */
public class Util_HttpGet {

    public static String get(String uri, Map<String, String> params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet;
        BufferedReader br = null;
        try {
            if (params != null) {  //添加参数
                uri = uri + "?";
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    //构建表单字段内容
                    uri = uri + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
            httpGet = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpGet);
            InputStream in = response.getEntity().getContent();
            InputStreamReader isr = new InputStreamReader(in, "utf-8");
            br = new BufferedReader(isr);
            String line;
            StringBuilder sBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sBuilder.append(line);
            }
            return sBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
