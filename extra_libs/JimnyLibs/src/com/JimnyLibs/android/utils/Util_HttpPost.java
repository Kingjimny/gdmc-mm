package com.JimnyLibs.android.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

/**
 * Created by Jimny
 * on 14-7-20.
 */
public class Util_HttpPost {
    private static final int retryTime = 3;

    public static String post(String basciURL, Map<String, String> params) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(basciURL);
            if (params != null) {  //添加参数
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    //构建表单字段内容
                    param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                HttpEntity entity = new UrlEncodedFormEntity(param, HTTP.UTF_8);
                httpPost.setEntity(entity);
            }
            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);//设置超市连接
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            httpClient.getParams().setParameter(CoreConnectionPNames.TCP_NODELAY, false);
            httpClient.getParams().setParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 1024 * 1024);
            //HttpRequestRetryHandler是负责处理请求重试的接口。
            HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount >= retryTime) {
                        // Do not retry if over max retry count
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {
                        // Timeout
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {
                        // Unknown host
                        return false;
                    }
                    if (exception instanceof ConnectException) {
                        // Connection refused
                        return false;
                    }
                    if (exception instanceof SSLException) {
                        // SSL handshake exception
                        return false;
                    }
                    HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
                    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                    if (idempotent) {
                        // Retry if the request is considered idempotent
                        return true;
                    }
                    return false;
                }

            };
            httpClient.setHttpRequestRetryHandler(myRetryHandler);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity, HTTP.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
