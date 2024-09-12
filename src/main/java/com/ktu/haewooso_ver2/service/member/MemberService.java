package com.ktu.haewooso_ver2.service.member;

import com.ktu.haewooso_ver2.dto.member.MemberCreateDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    // 회원등록 v1
    public ResponseEntity<String> memberCreateService(MemberCreateDto memberCreateDto);

}
