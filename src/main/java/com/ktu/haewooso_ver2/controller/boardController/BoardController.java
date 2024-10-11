package com.ktu.haewooso_ver2.controller.boardController;

import com.ktu.haewooso_ver2.service.board.MessageBoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("get_send_me_uuid/api/v1")
    public List<String> getMyBoardList(HttpSession session){
        String myUUID = (String) session.getAttribute("myUUID");
        System.out.println("myUUID = " + myUUID);

        return messageBoardService.getSendMeList(myUUID);
    }

    @GetMapping("get_receive_message_info/api/v1/{uuid}")
    public List<String> getSendMeBoardList(@PathVariable String uuid){
        System.out.println("UUID = " + uuid);

        return messageBoardService.getMyBoardList(uuid);
    }
}
