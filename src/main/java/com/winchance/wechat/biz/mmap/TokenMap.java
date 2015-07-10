package com.winchance.wechat.biz.mmap;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class TokenMap {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final WriteLock writeLock = lock.writeLock();

    private final ReadLock readLock = lock.readLock();

    private String accessToken = null;

    private String jsapiTicket = null;

    public String getAccessToken() {
        readLock.lock();
        try {
            return accessToken;
        } finally {
            readLock.unlock();
        }
    }

    public void setAccessToken(String accessToken) {
        writeLock.lock();
        try {
            this.accessToken = accessToken;
        } finally {
            writeLock.unlock();
        }
    }

    public String getJsapiTicket() {
        readLock.lock();
        try {
            return jsapiTicket;
        } finally {
            readLock.unlock();
        }
    }

    public void setJsapiTicket(String jsapiTicket) {
        writeLock.lock();
        try {
            this.jsapiTicket = jsapiTicket;
        } finally {
            writeLock.unlock();
        }
    }
}
