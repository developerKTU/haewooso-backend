package com.ktu.haewooso_ver2.service.secretPushService;

public interface SecretPushService {
    public String createRandomCode();
    public void createSecretCodeMsg(String uuid, String result);
}
