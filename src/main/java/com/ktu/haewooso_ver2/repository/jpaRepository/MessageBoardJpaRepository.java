package com.ktu.haewooso_ver2.repository.jpaRepository;

import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MessageBoardJpaRepository extends JpaRepository<SendMsg, Long> {

    @Query("select s.sendUuid from SendMsg s where s.sendUuid not in (:myUUID) and s.receiveUuid = :myUUID group by s.sendUuid")
    List<String> getSendMeUuidList(@Param("myUUID") String myUUID);

    @Query("select s.title, s.content, s.secretAt from SendMsg s where s.sendUuid not in (:myUUID) and s.receiveUuid = :myUUID")
    List<String> getMyBoardList(@Param("myUUID") String myUUID);

}
