package com.ktu.haewooso_ver2.service.pushMessage;

import com.ktu.haewooso_ver2.dto.pushMessage.MessagePushDto;
import com.ktu.haewooso_ver2.enums.board.SecretAt;
import org.springframework.http.ResponseEntity;

public interface FCMNotificationService {

    // 랜덤토큰 조회
    public String getRandomReceiverToken(String uuid);
    public String getReceiverUuid(String pushToken);
    public ResponseEntity<String> sendNotificationByToken(MessagePushDto messagePushDto, String randomReceiverToken);
    public ResponseEntity<String> insertMessageInfomation(MessagePushDto messagePushDto, SecretAt secretAt);

}
