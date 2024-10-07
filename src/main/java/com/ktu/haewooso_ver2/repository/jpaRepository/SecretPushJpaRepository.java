package com.ktu.haewooso_ver2.repository.jpaRepository;

import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SecretPushJpaRepository extends JpaRepository<SecretCodeMsg, String> {

    // 시크릿코드를 새로 발급 받으면 upsert 기능으로, ROW가 새로 추가되는 것이 아닌, 기존 시크릿코드 UPDATE
    @Modifying
    @Query(value = "INSERT INTO haewooso.secret_code_msg (secret_code, secret_uuid, use_yn, created_date, last_modified_date) VALUES (:id, :secretUuid, :useYn, :createdDate, :lastModifiedDate)" +
            "ON DUPLICATE KEY UPDATE secret_uuid = :secretUuid, secret_code = :id, last_modified_date = :lastModifiedDate", nativeQuery = true)
    void upsertSecretCode(@Param("id") String id, @Param("secretUuid") String secretUuid, @Param("useYn") String useYn,
                          @Param("createdDate")LocalDateTime createdDate, @Param("lastModifiedDate")LocalDateTime lastModifiedDate);


}
