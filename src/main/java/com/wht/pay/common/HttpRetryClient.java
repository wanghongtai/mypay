package com.wht.pay.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用HttpClient发送请求、接收响应很简单，一般需要如下几步即可：
 *
 * 1.创建CloseableHttpClient对象。
 * 2.创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
 * 3.如果需要发送请求参数，可可调用setEntity(HttpEntity entity)方法来设置请求参数。setParams方法已过时（4.4.1版本）。
 * 4.调用HttpGet、HttpPost对象的setHeader(String name, String value)方法设置header信息，或者调用setHeaders(Header[] headers)设置一组header信息。
 * 5.调用CloseableHttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个CloseableHttpResponse。
 * 6.调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容；调用CloseableHttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头。
 * 7.释放连接。无论执行方法是否成功，都必须释放连接
 */
public class HttpRetryClient {
    private int m_numRetries = 2;
    private int m_socketTimeout = 10000;
    private int m_connectTimeout = 10000;
    private final boolean ENABLE_RETRY = true;
    CloseableHttpClient httpClient = createHttpClient();

    private CloseableHttpClient createHttpClient()
    {
        DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(m_numRetries, ENABLE_RETRY);
        RequestConfig requestConfig =
                RequestConfig.custom().setSocketTimeout(m_socketTimeout)
                        .setConnectTimeout(m_connectTimeout).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(1000);
        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultRequestConfig(requestConfig)
                        .setConnectionManager(connectionManager)
                        .setRetryHandler(retryHandler).build();
        return httpClient;
    }

    public String sendPost(String url, Map<String,String> map, String encoding) throws IOException {
        String body = "";
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息, 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }



    public static void main(String[] args) throws ParseException, IOException {
        String url="http://php.weather.sina.com.cn/iframe/index/w_cl.php";
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "static/js");
        map.put("day", "1");
        map.put("city", "上海");
        map.put("dfc", "1");
        map.put("charset", "utf-8");

        HttpRetryClient httpRetryClient = new HttpRetryClient();
        String body = httpRetryClient.sendPost(url, map,"utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);

        System.out.println("-----------------------------------");

        map.put("city", "北京");
        body = httpRetryClient.sendPost(url, map, "utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);
    }
}
