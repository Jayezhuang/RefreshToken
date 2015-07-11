package com.winchance.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.soa.common.model.SignOrder;
import com.winchance.wechat.biz.mmap.TokenMap;
import com.winchance.wechat.facard.WechatTokenService;

public class WechatTokenServiceImpl extends ServiceBase implements WechatTokenService {
    @Autowired
    private TokenMap tokenMap;

    @Override
    public String getAccessToken(SignOrder signOrder) {
        if (!validator(signOrder))
            return null;

        return tokenMap.getAccessToken();
    }

    @Override
    public String getJsapiTicket(SignOrder signOrder) {
        if (!validator(signOrder))
            return null;

        return tokenMap.getJsapiTicket();
    }
}
