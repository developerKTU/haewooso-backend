package com.ktu.haewooso_ver2.controller.messageController;

import com.ktu.haewooso_ver2.dto.pushMessage.MessagePushDto;
import com.ktu.haewooso_ver2.enums.board.SecretAt;
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
public class MessagePushController {

    private final FCMNotificationService fcmNotificationService;

    @Autowired
    public MessagePushController(FCMNotificationService fcmNotificationService){
        this.fcmNotificationService = fcmNotificationService;
    }

    @Operation(summary = "랜덤유저에게 메시지 PUSH하는 API", description = "**랜덤 토큰 값을 조회하여 랜덤 사용자에게 푸시알림을 보내는 API**" +
            "\n\n**_<<필요 파라미터>>_**" +
            "\n\n _sendUuid_\n\n_receiveUuid(null 또는 보낼 필요 없음)_\n\n_title_\n\ncontent")
    @PostMapping("v1/send")
    public String pushMessageRandomUser(@RequestBody MessagePushDto messagePushDto){

        boolean reviewAt = true;

        // 랜덤토큰 조회
        String randomReceiverToken = fcmNotificationService.getRandomReceiverToken(messagePushDto.getSendUuid());
        System.out.println("=== randomReceiverToken === : " + randomReceiverToken);

        // 푸시알림 send
        String result = fcmNotificationService.sendNotificationByToken(messagePushDto, randomReceiverToken).getBody();
        // 수신자 uuid 조회
        String randomReceiverUuid = fcmNotificationService.getReceiverUuid(randomReceiverToken);
        messagePushDto.setReceiveUuid(randomReceiverUuid);

        // 시크릿 푸시 구분코드 생성 (랜덤 푸시 : R)
        SecretAt secretAt = SecretAt.R;

        // 푸시알림 메시지 전송 성공 시, 보낸 푸시 알림 메시지 정보 insert
        if("200".equals(result)){
            result = fcmNotificationService.insertMessageInfomation(messagePushDto, secretAt).getBody();
        }

        // 241031 자동응답 기능 구현
        if("200".equals(result) && reviewAt){
            // 241031 앱 리뷰중이면 심사자에게 자동 답장이 가도록 자동응답기능 설정
            // 자동응답을 받는 사람은 랜덤 푸시를 보낸 사용자
            String receiverToken = fcmNotificationService.getPushToken(messagePushDto.getSendUuid());

            // 자동응답을 보내는 사람은 앱 개발자 또는 운영자 (DB에 등록되어 있는 실 사용자)
            String autoSendUUID = "9b14d421-0299-433c-95d9-bcc8e12737e1";
            // 자동응답을 받는 사용자의 UUID
            String autoReceiveUUID = fcmNotificationService.getReceiverUuid(receiverToken);;
            String autoTitle = "메시지 잘 받았습니다! 좋은 하루 보내세요!";
            String autoContent = "보내주신 메시지 잘 받았습니다. 오늘도 좋은 하루 보내세요!";

            messagePushDto.setSendUuid(autoSendUUID);
            messagePushDto.setReceiveUuid(autoReceiveUUID);
            messagePushDto.setTitle(autoTitle);
            messagePushDto.setContent(autoContent);

            // 자동응답 푸시알림 send
            result = fcmNotificationService.sendNotificationByToken(messagePushDto, receiverToken).getBody();
        }

        // 241031 자동응답 푸시 성공 시, 보낸 푸시 알림 메시지 정보 insert
        if("200".equals(result)){
            result = fcmNotificationService.insertMessageInfomation(messagePushDto, secretAt).getBody();
        }

        return result;
    }
}
