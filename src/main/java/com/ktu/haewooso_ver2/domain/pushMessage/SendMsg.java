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

    @Id @GeneratedValue
    @Column(name = "send_board_sno")
    private int id;
    private String sendUuid;
    private String receiveUuid;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sendUuid", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member member;

    // 연관관계 메서드
    // 메시지를 보낸 회원에게 해당 Message set
    public void setSendMessage(Member member){
        member.getSendMessages().add(this);
    }

    @Builder
    public SendMsg(String sendUuid, String receiveUuid, String title, String content){
        this.sendUuid = sendUuid;
        this.receiveUuid = receiveUuid;
        this.title = title;
        this.content = content;
    }

}
