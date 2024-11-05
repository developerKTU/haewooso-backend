package com.ktu.haewooso_ver2.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDto {

    private String uuid;
    private String pushToken;
    private LocalDateTime lastConnectDate;
    private String useYn;
}
