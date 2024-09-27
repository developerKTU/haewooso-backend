package com.ktu.haewooso_ver2.controller.secretPushController;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import com.ktu.haewooso_ver2.service.secretPushService.SecretPushService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;

@Tag(name="Haewooso Secret Message Push API", description = "해우소 앱의 특정 상대만 푸시 메시지 알람을 보내는 API")
@RestController
@RequestMapping("/")
public class SecretMessagePushController {

    private final SecretPushService secretPushService;

    @Autowired
    public SecretMessagePushController(SecretPushService secretPushService){
        this.secretPushService = secretPushService;
    }

    @PostMapping("create/secret_code/api/v1")
    public String creteRandomSecretCode(@RequestBody HashMap<String, String> requestUuid){
        System.out.println(requestUuid.get("uuid"));
        String result = secretPushService.createRandomCode();

        secretPushService.createSecretCodeMsg(requestUuid.get("uuid"), result);

        return result;
    }
}
