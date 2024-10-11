package com.ktu.haewooso_ver2.service.board;

import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;

import java.util.*;

public interface MessageBoardService {

    public List<String> getSendMeList(String myUUID);
    public List<MessageBoardDto> getMyBoardList(String myUUID);

}
