package com.ktu.haewooso_ver2.repository;

import com.ktu.haewooso_ver2.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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

}
