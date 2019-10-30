package com.itstyle.controller;

import com.itstyle.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController
{

  @Autowired
  private IUserService userService;

  @GetMapping({"/hi"})
  public String hi()
  {
    return "Hi !!";
  }

  @GetMapping({"/test/sw"})
  public void test() {
    try {
      this.userService.test();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}