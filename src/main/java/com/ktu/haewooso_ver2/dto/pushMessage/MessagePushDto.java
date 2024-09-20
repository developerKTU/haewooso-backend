package com.ktu.haewooso_ver2.dto.pushMessage;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

@Data
public class MessagePushDto {

    @NotNull
    private String sendUuid;

    private String receiveUuid;

    @NotNull
    private String title;

    @NotNull
    private String content;

}
