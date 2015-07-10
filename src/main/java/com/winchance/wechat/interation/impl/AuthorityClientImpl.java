package com.winchance.wechat.interation.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.winchance.wechat.biz.mmap.TokenMap;
import com.winchance.wechat.common.Constant;
import com.winchance.wechat.common.convertor.GsonConvertor;
import com.winchance.wechat.common.http.HttpClientSender;
import com.winchance.wechat.interation.AuthorityClient;
import com.winchance.wechat.task.vo.AccessTokenVo;
import com.winchance.wechat.task.vo.JsapiTicketVo;

public class AuthorityClientImpl implements AuthorityClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityClientImpl.class);

    @Autowired
    private TokenMap tokenMap;

    @Override
    public String getJsapiTicket() {
        String accessToken = tokenMap.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            accessToken = getAccessToken();
        }

        Map<String, String> tokenParams = new HashMap<String, String>();
        tokenParams.put("access_token", accessToken);
        tokenParams.put("type", "jsapi");

        try {
            String result = HttpClientSender.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket", tokenParams);
            JsapiTicketVo jsapiTicketVo = GsonConvertor.fromJson(result, JsapiTicketVo.class);

            String jsapiTicket = jsapiTicketVo.getTicket();
            tokenMap.setJsapiTicket(jsapiTicket);

            logger.info("get jsapi_ticket = {}", jsapiTicket);
            return jsapiTicket;
        } catch (Exception e) {
            logger.info("fail to get jsapi_ticket, error_msg = {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String getAccessToken() {
        Map<String, String> tokenParams = new HashMap<String, String>();
        tokenParams.put("grant_type", "client_credential");
        tokenParams.put("appid", Constant.APP_ID);
        tokenParams.put("secret", Constant.SECRET);

        try {
            String result = HttpClientSender.get("https://api.weixin.qq.com/cgi-bin/token", tokenParams);
            AccessTokenVo accessTokenVo = GsonConvertor.fromJson(result, AccessTokenVo.class);

            String accessToken = accessTokenVo.getAccessToken();
            tokenMap.setAccessToken(accessToken);

            logger.info("get access_token = {}", accessToken);
            return accessToken;
        } catch (Exception e) {
            logger.info("fail to get access_token, error_msg = {}", e.getMessage());
            return null;
        }
    }
}
