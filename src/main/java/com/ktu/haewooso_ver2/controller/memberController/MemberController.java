package com.ktu.haewooso_ver2.controller.memberController;

import com.ktu.haewooso_ver2.dto.member.MemberCreateDto;
import com.ktu.haewooso_ver2.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


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
    @PostMapping("v1/user")
    public String createUserV1(@RequestBody @Valid MemberCreateDto memberCreateDto){

        return memberService.memberCreateService(memberCreateDto).getBody();
    }

    @Operation(summary = "회원 접속일자 업데이트 API v1", description="**어플에 접속한 회원의 접속일자를 UPDATE하는 API**\n\n_<<uuid : 접속일자 업데이트 대상 uuid>>_\n\n")
    @PatchMapping("v1/last-connect-date")
    public String updateConnectDate(@RequestBody HashMap<String, String> requestUuid, HttpSession session){
        String uuid = requestUuid.get("uuid");
        String result = memberService.lastConnectUpdate(uuid).getBody();

        session.setAttribute("myUUID", uuid);
        session.setMaxInactiveInterval(24 * 60 * 60);   // 세션 만료 시간을 24시간으로 설정

        return result;
    }
}
