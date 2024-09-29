package com.ktu.haewooso_ver2.domain.pushMessage;

import com.ktu.haewooso_ver2.domain.auditing.BaseTimeEntity;
import com.ktu.haewooso_ver2.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SecretCodeMsg extends BaseTimeEntity {

    @Id @Column(name = "secret_code")
    private String id;
    private String secretUuid;
    private String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secretUuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private Member member;

    @Builder
    public SecretCodeMsg(String id, String uuid, String useYn){
        this.id = id;
        this.secretUuid = uuid;
        this.useYn = useYn;
    }

}
