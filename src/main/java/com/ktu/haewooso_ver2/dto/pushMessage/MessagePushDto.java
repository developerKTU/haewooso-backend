package com.ktu.haewooso_ver2.dto.pushMessage;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

@Data
public class MessagePushDto {

    @NotNull
    private String sendUuid;

    private String receiveUuid;

    // 시크릿 푸시 알림을 보내기 위한 시크릿코드
    private String secretCode;

    @NotNull
    private String title;

    @NotNull
    private String content;

}
