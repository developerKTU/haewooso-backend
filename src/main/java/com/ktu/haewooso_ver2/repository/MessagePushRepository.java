package com.ktu.haewooso_ver2.repository;

import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MessagePushRepository {

    @PersistenceContext
    EntityManager em;

    // CREATE
    public SendMsg save(SendMsg sendMsg){
        em.persist(sendMsg);
        return sendMsg;
    }

    // DELETE
    public void delete(SendMsg sendMsg){
        em.remove(sendMsg);
    }

    // 랜덤토큰 조회
    // @Query -> WHERE : uuid, SELECT : push_token
    public String findByRandomReceiverToken(String uuid){
        return em.createQuery("SELECT m.pushToken FROM Member m WHERE m.uuid not in :uuid order by random() LIMIT 1", String.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    // 조회한 랜덤토큰의 uuid 조회
    // WHERE : uuid, SELECT : pushToken
    public String findByUuidOfRandomPushToken(String pushToken){
        return em.createQuery("select m.uuid from Member m where m.pushToken = :pushToken"
                        , String.class)
                .setParameter("pushToken", pushToken)
                .getSingleResult();
    }
}
