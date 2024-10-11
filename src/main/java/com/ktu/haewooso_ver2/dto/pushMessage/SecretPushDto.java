package com.ktu.haewooso_ver2.dto.pushMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecretPushDto extends MessagePushDto {
    // 시크릿 푸시 알림을 보내기 위한 시크릿코드
    private String secretCode;

}
