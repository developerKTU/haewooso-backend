package com.ktu.haewooso_ver2.repository.jpaRepository;

import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MessageBoardJpaRepository extends JpaRepository<SendMsg, Long> {

    @Query("select s.sendUuid from SendMsg s where s.sendUuid not in (:myUUID) and s.receiveUuid = :myUUID group by s.sendUuid")
    List<String> getSendMeUuidList(@Param("myUUID") String myUUID);

    @Query(value = "select title as title, content as content, secret_at as secretAt from haewooso.send_msg where send_uuid not in (:myUUID) and receive_uuid = :myUUID order by secret_at"
            , nativeQuery = true)
    List<MessageBoardDto> getMyBoardList(@Param("myUUID") String myUUID);

}
