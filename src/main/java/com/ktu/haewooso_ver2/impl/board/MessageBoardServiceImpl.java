package com.ktu.haewooso_ver2.impl.board;

import com.ktu.haewooso_ver2.dto.board.MessageBoardDto;
import com.ktu.haewooso_ver2.repository.jpaRepository.MessageBoardJpaRepository;
import com.ktu.haewooso_ver2.service.board.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageBoardServiceImpl implements MessageBoardService {

    private final MessageBoardJpaRepository messageBoardJpaRepository;

    @Autowired
    public MessageBoardServiceImpl(MessageBoardJpaRepository messageBoardJpaRepository){
        this.messageBoardJpaRepository = messageBoardJpaRepository;
    }

    @Override
    public List<String> getSendMeList(String myUUID) {
        return messageBoardJpaRepository.getSendMeUuidList(myUUID);
    }

    @Override
    public List<MessageBoardDto> getMyBoardList(String myUUID) {
        return messageBoardJpaRepository.getMyBoardList(myUUID);
    }
}
