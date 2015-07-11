package com.winchance.wechat.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soa.common.model.SignOrder;
import com.soa.common.utils.cipher.TripleDES;

public abstract class ServiceBase {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String key = "WINCHANCE-oms@#193g%(^$}";

    private TripleDES tripleDES;

    protected boolean validator(SignOrder signOrder) {
        if (null != signOrder
                && StringUtils.isNotBlank(signOrder.getKey())
                && StringUtils.isNotBlank(signOrder.getSign())) {
            String encryptValue = encrypt(signOrder.getKey());
            if (StringUtils.equals(encryptValue, signOrder.getSign())) {
                return true;
            }
        }
        return false;
    }

    private String encrypt(String src) {
        try {
            tripleDES = new TripleDES(key);
            return tripleDES.encrypt(src);
        } catch (Exception ignore) {
        }
        return "";
    }
}
