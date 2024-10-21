package com.ktu.haewooso_ver2.service.board;

import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface MessageBoardService {

    public List<String> getSendMeList(String myUUID);
    public Page<MessageBoardDto> getMyBoardList(String myUUID, String sendUUID, Pageable pageable);

}
