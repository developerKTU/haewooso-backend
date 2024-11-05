package com.ktu.haewooso_ver2.service.member;

import com.ktu.haewooso_ver2.dto.member.MemberCreateDto;
import com.ktu.haewooso_ver2.dto.member.MemberDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    // 회원등록 v1
    public ResponseEntity<String> memberCreateService(MemberCreateDto memberCreateDto);
    // 접속일자 업데이트 v1
    public ResponseEntity<String> lastConnectUpdate(String uuid);
    // 멤버리스트 조회 v1
    public List<MemberDto> getMemberList();
}
