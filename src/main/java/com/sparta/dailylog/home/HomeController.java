package com.sparta.dailylog.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  // mapping url
  @GetMapping("/")
  public String goHome() {
    // thymeleaf에만 적용됨, jsp 또는 react는 나도 모름 ㅎㅎ
    // resources/templates/___
    // ___ :  file name
    return "login";
  }
}
