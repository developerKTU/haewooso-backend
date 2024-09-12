package com.ktu.haewooso_ver2.dto.member;

import com.google.firebase.database.annotations.NotNull;
import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class MemberCreateDto {

    @NotNull
    private String uuid;

    @NotNull
    private String push_token;

}
