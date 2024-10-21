package com.ktu.haewooso_ver2.repository.jpaRepository;

import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MessageBoardJpaRepository extends JpaRepository<SendMsg, Long> {

    @Query("select s.sendUuid from SendMsg s where s.sendUuid not in (:myUUID) and s.receiveUuid = :myUUID group by s.sendUuid")
    List<String> getSendMeUuidList(@Param("myUUID") String myUUID);

    @Query(value = "select a.send_board_sno as id" +
                   "     , b.secret_code as secretCode" +
                   "     , a.title as title" +
                   "     , a.content as content" +
                   "     , a.secret_at as secretAt" +
                   "  from haewooso.send_msg a" +
                   "  left outer join haewooso.secret_code_msg b" +
                   "    on a.send_uuid = b.secret_uuid" +
                   " where a.send_uuid = :sendUUID" +
                   "   and a.receive_uuid = :myUUID" +
                   " order by a.secret_at"
            , countQuery = "select count(*)" +
                           "  from haewooso.send_msg" +
                           " where send_uuid not in (:myUUID)" +
                           "   and receive_uuid = :myUUID"
            , nativeQuery = true)
    Page<MessageBoardDto> getMyBoardList(@Param("myUUID") String myUUID, @Param("sendUUID") String sendUUID, Pageable pageable);

}
