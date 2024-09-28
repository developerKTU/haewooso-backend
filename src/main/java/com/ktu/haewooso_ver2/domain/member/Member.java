package com.ktu.haewooso_ver2.domain.member;

import com.ktu.haewooso_ver2.domain.auditing.BaseTimeEntity;
import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity implements Persistable<String> {

    @Id @Column(name = "uuid")
    private String uuid;
    private String pushToken;
    private LocalDateTime lastConnectDate;

    // 푸시알림 정보 저장 테이블
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<SendMsg> sendMessages = new ArrayList<>();

    // 240926 useYn(멤버탈퇴여부) 컬럼 추가
    private String useYn;

    // 240928 시크릿 푸시 기능 컬럼 추가
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<SecretCodeMsg> secretCodeMsgs = new ArrayList<>();

    @Builder
    public Member(String uuid, String pushToken, LocalDateTime lastConnectDate, SendMsg sendMessage, String useYn, SecretCodeMsg secretCodeMsg){
        this.uuid = uuid;
        this.pushToken = pushToken;
        this.lastConnectDate = lastConnectDate;
        this.sendMessages.add(sendMessage);
        this.useYn = useYn;
        this.secretCodeMsgs.add(secretCodeMsg);
    }

    public void updateLastConnectDate(){
        this.lastConnectDate = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return this.uuid;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
