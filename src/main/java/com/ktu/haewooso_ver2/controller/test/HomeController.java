package com.ktu.haewooso_ver2.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    @GetMapping("home")
    public String homeContoller(){
        String s = "테스트 페이지 > Spring Boot 세팅 완료!";

        return s;
    }
}
