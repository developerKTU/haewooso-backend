package com.ktu.haewooso_ver2.repository;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.dto.member.MemberDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    // CREAT
    public Member save(Member member){
        em.persist(member);

        return member;
    }

    // READ
    public Optional<Member> findByUuid(String uuid){
        return Optional.ofNullable(em.find(Member.class, uuid));
    }

    // dto를 활용하여 List형으로 리턴하기 위해 쿼리 select절과 dto 매핑.
    public List<MemberDto> findMemberList(){
        return em.createQuery("select new com.ktu.haewooso_ver2.dto.member.MemberDto(m.uuid, m.pushToken, m.lastConnectDate, m.useYn)" +
                        "   from Member m" +
                        "  where m.useYn = 'Y'", MemberDto.class)
                .getResultList();
    }
}
