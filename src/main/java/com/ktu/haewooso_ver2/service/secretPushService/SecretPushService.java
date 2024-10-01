package com.ktu.haewooso_ver2.service.secretPushService;

import org.springframework.http.ResponseEntity;

public interface SecretPushService {
    public String createRandomCode();
    public ResponseEntity<String> createSecretCodeMsg(String uuid, String result);
    public String findPushTokenBySecretCode(String receiveSecretCode);
}
