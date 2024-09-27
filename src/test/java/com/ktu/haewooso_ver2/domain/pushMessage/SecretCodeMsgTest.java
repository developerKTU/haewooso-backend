package com.ktu.haewooso_ver2.domain.pushMessage;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.repository.MemberRepository;
import com.ktu.haewooso_ver2.repository.SecretPushRepository;
import com.ktu.haewooso_ver2.service.secretPushService.SecretPushService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SecretCodeMsgTest {

    private final SecretPushService secretPushService;
    private final MemberRepository memberRepository;
    private final SecretPushRepository secretPushRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    public SecretCodeMsgTest(SecretPushService secretPushService, EntityManager em, MemberRepository memberRepository, SecretPushRepository secretPushRepository){
        this.secretPushService = secretPushService;
        this.em = em;
        this.memberRepository = memberRepository;
        this.secretPushRepository = secretPushRepository;
    }

    @Test
    public void secretCodeMsgEntityTest(){
        // give
        Member member1 = Member.builder()
                .uuid("uuid1")
                .pushToken("pushToken1")
                .lastConnectDate(LocalDateTime.now())
                .sendMessage(null)
                .useYn("Y")
                .build();

        memberRepository.save(member1);

        Optional<Member> member = memberRepository.findByUuid("uuid1");

        String secretCode = secretPushService.createRandomCode();
        SecretCodeMsg secretCodeMsg = com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg.builder()
                .id(secretCode)
                .uuid(member.get().getUuid())
                .useYn("Y")
                .build();

        // when
        em.persist(secretCodeMsg);
        em.flush();

        // then
        Assertions.assertThat(secretCodeMsg.getSecretUuid()).isEqualTo("uuid1");
    }

}