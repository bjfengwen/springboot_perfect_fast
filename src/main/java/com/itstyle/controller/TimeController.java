package com.itstyle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController
{
  @GetMapping({"/time"})
  public long getNow()
  {
    return System.currentTimeMillis();
  }
}