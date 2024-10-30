package com.ktu.haewooso_ver2.repository;

import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public String findSecretReceiveTokenBySecretCode(String secretCode){
        return em.createQuery("select m.pushToken " +
                            "     from Member m " +
                            "     left outer join SecretCodeMsg s" +
                            "       on m.uuid = s.secretUuid" +
                            "    where s.id = :secretCode", String.class)
                .setParameter("secretCode", secretCode)
                .getSingleResult();
    }

    // 시크릿코드 유효성 검증을 위한 DB에 있는 시크릿코드 조회
    // @Query -> WHERE : secretCode, SELECT : secretCode
    public Optional<String> validSecretCode(String secretCode){

        try{
            String selectSecretCode = em.createQuery("select s.id" +
                            "                from SecretCodeMsg s" +
                            "               where s.id = :secretCode", String.class)
                    .setParameter("secretCode", secretCode)
                    .getSingleResult();

            return Optional.of(selectSecretCode);
        }catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
