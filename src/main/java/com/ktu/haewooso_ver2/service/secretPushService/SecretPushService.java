package com.ktu.haewooso_ver2.service.secretPushService;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface SecretPushService {
    public String createRandomCode();
    public ResponseEntity<String> createSecretCodeMsg(String uuid, String result);
    public String findPushTokenBySecretCode(String receiveSecretCode);
    public Optional<String> validSecretCode(String secretCode);
}
