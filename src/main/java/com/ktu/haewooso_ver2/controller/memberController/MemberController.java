package com.ktu.haewooso_ver2.controller.memberController;

import com.ktu.haewooso_ver2.dto.member.MemberCreateDto;
import com.ktu.haewooso_ver2.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="Haewooso Member API", description = "해우소 앱의 회원 관련 API 요청을 처리하는 Controller")
@RestController
@RequestMapping("member/")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @Operation(summary = "회원 등록 API v1", description="**신규 유저 등록 API (기능 구현을 위해 v1은 인증/인가 X)**\n\n_<<uuid : 회원ID>>_\n\n" +
                                            "_<<push_token : push 알림을 받을 고유 토큰값>>_\n\n")
    @PostMapping("v1/createuser")
    public String createUserV1(@RequestBody @Valid MemberCreateDto memberCreateDto){

        return memberService.memberCreateService(memberCreateDto).getBody();
    }
}
