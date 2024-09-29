package com.ktu.haewooso_ver2.impl.member;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.dto.member.MemberCreateDto;
import com.ktu.haewooso_ver2.repository.MemberRepository;
import com.ktu.haewooso_ver2.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<String> memberCreateService(MemberCreateDto memberCreateDto) {
        try{
            Member member = Member.builder()
                    .uuid(memberCreateDto.getUuid())
                    .pushToken(memberCreateDto.getPush_token())
                    .lastConnectDate(LocalDateTime.now())
                    .sendMessage(null)
                    .useYn("Y")
                    .build();

            memberRepository.save(member);

            return new ResponseEntity<String>("200", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> lastConnectUpdate(String uuid) {
        try{
            Optional<Member> findMember = memberRepository.findByUuid(uuid);

            findMember.get().updateLastConnectDate();

            return new ResponseEntity<String>("200", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST);
        }
    }
}