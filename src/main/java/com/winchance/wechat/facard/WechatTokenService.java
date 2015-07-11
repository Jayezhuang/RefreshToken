package com.winchance.wechat.facard;

import com.soa.common.model.SignOrder;

public interface WechatTokenService {
    public String getAccessToken(SignOrder signOrder);

    public String getJsapiTicket(SignOrder signOrder);
}
