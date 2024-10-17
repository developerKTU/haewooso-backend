package com.ktu.haewooso_ver2.controller.boardController;

import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;
import com.ktu.haewooso_ver2.service.board.MessageBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name="Haewooso Board API", description = "**나에게 메시지를 보낸 내역을 조회하는 API**")
@RestController
@RequestMapping("board/")
public class BoardController {

    private final MessageBoardService messageBoardService;

    @Autowired
    public BoardController(MessageBoardService messageBoardService){
        this.messageBoardService = messageBoardService;
    }

    @Operation(summary = "나에게 메시지를 보낸 사용자의 UUID 조회 API v1", description="**나에게 메시지를 보낸 사용자의 UUID 조회하는 API**"
            +"\n\n**_<<필요 파라미터>>_**"
            +"\n\n_<<세션으로 가져온 본인의 UUID 사용 (파라미터 X)>>_\n\n"
            +"\n\n**_※ 참고_**"
            +"\n\n_마지막 접속일 업데이트 API가 먼저 호출되어야 이 API도 호출됨_")
    @GetMapping("get_send_me_uuid/api/v1")
    public List<String> getMyBoardList(HttpSession session){
        String myUUID = (String) session.getAttribute("myUUID");
        System.out.println("myUUID = " + myUUID);

        return messageBoardService.getSendMeList(myUUID);
    }

    @Operation(summary = "해당 사용자에게 받은 메시지 내역 조회 API v1", description="**해당 사용자에게 받은 메시지 내역을 조회하는 API**"
            +"\n\n**_<<필요 파라미터 (pathParameter)>>_**"
            +"\n\n_<<uuid : 자신에게 메시지를 보낸 사용자 중 한 명의 UUID 선택>>_\n\n"
            +"\n\n**_※ 참고_**"
            +"\n\n_get_send_me_uuid API가 정상적으로 호출 되어야 이 API도 정상 호출됨_"
            +"\n\n**_<<API 호출 가이드>>_**"
            +"\n\n_BASEURL/board/get_receive_message_info/api/v1/uuid1?page=0&size=3_"
            +"\n\n_page : 현재 페이지 번호_"
            +"\n\n_size : 한 페이지 당 보여질 컨텐츠의 수_")
    @GetMapping("get_receive_message_info/api/v1/{uuid}")
    public Page<MessageBoardDto> getSendMeBoardList(Pageable pageable, @PathVariable String uuid){
        return messageBoardService.getMyBoardList(uuid, pageable);
    }
}
