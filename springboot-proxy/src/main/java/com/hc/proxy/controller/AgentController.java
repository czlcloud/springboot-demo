package com.hc.proxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zack chen
 * @Date: 2023/3/22 16:48
 */
@RestController
@RequestMapping("agents")
public class AgentController {

    @GetMapping("{agentId}/welcome")
    public String agent(@PathVariable("agentId") String agentId){
        return "我是" + agentId;
    }

}
