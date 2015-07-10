package com.winchance.wechat.task;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.winchance.wechat.interation.AuthorityClient;

public class AccessTokenTask {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

    @Autowired
    private AuthorityClient  authorityClient;

    @PostConstruct
    public void run() {
        String accessToken = authorityClient.getAccessToken();
        logger.info("get access_token = {}", accessToken);

        String jsapiTicket = authorityClient.getJsapiTicket();
        logger.info("get jsapi_ticket = {}", jsapiTicket);
    }
}
