package com.ktu.haewooso_ver2.domain.member;

import com.ktu.haewooso_ver2.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberTest(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Test
    public void persistMember(){
        // given
        Member member1 = Member.builder()
                .uuid("uuid1")
                .pushToken("pushToken1")
                .lastConnectDate(LocalDateTime.now())
                .messages(null)
                .build();

        memberRepository.save(member1);

        // when
        Optional<Member> findMember1 = memberRepository.findByUuid("uuid1");

        // then
        assertThat(findMember1.get().getId()).isEqualTo("uuid1");
    }

}