package com.ktu.haewooso_ver2.repository;

import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SecretPushRepository {

    @PersistenceContext
    EntityManager em;

    // CREATE
    public SecretCodeMsg save(SecretCodeMsg secretCodeMsg){
        em.persist(secretCodeMsg);
        return secretCodeMsg;
    }

    // 토큰 조회
    // @Query -> WHERE : uuid, SELECT : push_token

}
