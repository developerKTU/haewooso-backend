package com.ktu.haewooso_ver2.domain.pushMessage;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.enums.board.SecretAt;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendMsg {

    @Id @SequenceGenerator(name = "send_msg_SEQ_GENERATOR", sequenceName = "send_msg_seq", allocationSize = 0)
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
    // 241029 랜덤, 시크릿 구분코드 ENUM으로 변경
    @Enumerated(EnumType.STRING)
    private SecretAt secretAt;

    // 연관관계 메서드
    // 메시지를 보낸 회원에게 해당 Message set
    public void setSendMessage(Member member){
        member.getSendMessages().add(this);
    }

    @Builder
    public SendMsg(String sendUuid, String receiveUuid, String title, String content, SecretAt secretAt){
        this.sendUuid = sendUuid;
        this.receiveUuid = receiveUuid;
        this.title = title;
        this.content = content;
        this.secretAt = secretAt;
    }

}
