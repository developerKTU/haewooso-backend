package com.ktu.haewooso_ver2.domain.pushMessage;

import com.ktu.haewooso_ver2.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendMsg {

    @Id @SequenceGenerator(name = "send_msg_SEQ_GENERATOR", sequenceName = "send_msg_seq", allocationSize = 1)
    @Column(name = "send_board_sno")
    private int id;
    private String sendUuid;
    private String receiveUuid;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sendUuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private Member member;

    // 240930 랜덤푸시, 시크릿푸시 구분코드 추가
    private String secretAt;

    // 연관관계 메서드
    // 메시지를 보낸 회원에게 해당 Message set
    public void setSendMessage(Member member){
        member.getSendMessages().add(this);
    }

    @Builder
    public SendMsg(String sendUuid, String receiveUuid, String title, String content, String secretAt){
        this.sendUuid = sendUuid;
        this.receiveUuid = receiveUuid;
        this.title = title;
        this.content = content;
        this.secretAt = secretAt;
    }

}
