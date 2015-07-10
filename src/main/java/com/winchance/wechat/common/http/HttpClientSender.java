package com.winchance.wechat.common.http;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

public class HttpClientSender {
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String get(String url)
            throws ParseException, ClientProtocolException, IOException {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> params)
            throws ParseException, ClientProtocolException, IOException {
        return get(url, params, null);
    }

    public static String get(String url, Map<String, String> params, String charset)
            throws ParseException, ClientProtocolException, IOException {
        if (StringUtils.isBlank(url))
            return null;

        if (StringUtils.isBlank(charset))
            charset = DEFAULT_CHARSET;

        HttpGet httpGet = null;
        if (!CollectionUtils.isEmpty(params)) {
            StringBuilder builder = new StringBuilder("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

            httpGet = new HttpGet(url + StringUtils.substring(builder.toString(), 0, builder.length() - 1));
        } else {
            httpGet = new HttpGet(url);
        }
        httpGet.addHeader("Content-Type", "text/html;charset=" + charset);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        return EntityUtils.toString(httpClient.execute(httpGet).getEntity(), charset);
    }
}
