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

    // 241031 자동응답 기능을 위해 랜덤 토큰 조회가 아닌, uuid를 조건으로한 해당 토큰 조회
    public String getPushToken(String uuid);

}
