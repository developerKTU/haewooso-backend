package com.ktu.haewooso_ver2.service.board;

import java.util.*;

public interface MessageBoardService {

    public List<String> getSendMeList(String myUUID);
    public List<String> getMyBoardList(String myUUID);

}
