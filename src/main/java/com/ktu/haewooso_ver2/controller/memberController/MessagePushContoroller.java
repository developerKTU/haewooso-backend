package com.ktu.haewooso_ver2.controller.memberController;

import com.ktu.haewooso_ver2.dto.pushMessage.MessagePushDto;
import com.ktu.haewooso_ver2.firebase.FCMConfig;
import com.ktu.haewooso_ver2.service.pushMessage.FCMNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Haewooso Message Push API", description = "해우소 앱의 푸시 메시지 알람을 보내는 API")
@RestController
@RequestMapping("push/")
public class MessagePushContoroller {

    private final FCMNotificationService FCMNotificationService;

    @Autowired
    public MessagePushContoroller(FCMNotificationService FCMNotificationService){
        this.FCMNotificationService = FCMNotificationService;
    }

    @Operation(summary = "랜덤유저에게 메시지 PUSH하는 API", description = "**랜덤 토큰 값을 조회하여 랜덤 사용자에게 푸시알림을 보내는 API**" +
            "\n\n**_<<필요 파라미터>>_**" +
            "\n\n _sendUuid_\n\n_receiveUuid_\n\n_title_\n\ncontent")
    @PostMapping("api/v1")
    public String pushMessageRandomUser(@RequestBody MessagePushDto messagePushDto){

        // 랜덤토큰 조회
        String randomReceiverToken = FCMNotificationService.getRandomReceiverToken(messagePushDto.getSendUuid());

        // 푸시알림 send
        String result = FCMNotificationService.sendNotificationByToken(messagePushDto, randomReceiverToken).getBody();
        String ranDomReceiverUuid = FCMNotificationService.getRandomReceiverUuid(randomReceiverToken);
        messagePushDto.setReceiveUuid(ranDomReceiverUuid);

        // 푸시알림 메시지 전송 성공 시, 보낸 푸시 알림 메시지 정보 insert
        if("200".equals(result)){
            result = FCMNotificationService.insertMessageInfomation(messagePushDto).getBody();
        }

        return result;
    }
}
