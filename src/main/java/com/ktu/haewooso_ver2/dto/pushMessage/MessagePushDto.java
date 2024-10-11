package com.ktu.haewooso_ver2.dto.pushMessage;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagePushDto {

    @NotNull
    private String sendUuid;

    private String receiveUuid;

    @NotNull
    private String title;

    @NotNull
    private String content;

    // 랜덤 및 시크릿 푸시 여부
    private String secretAt;

}
