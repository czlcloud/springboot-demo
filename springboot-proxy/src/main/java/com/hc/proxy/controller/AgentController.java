package com.hc.proxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zack chen
 * @Date: 2023/3/22 16:48
 */
@RestController
@RequestMapping("agents")
public class AgentController {

    @GetMapping("agent1/welcome")
    public String agent1(){
        return "我是agent1";
    }

    @GetMapping("agent2/welcome")
    public String agent2(){
        return "我是agent2";
    }
}
