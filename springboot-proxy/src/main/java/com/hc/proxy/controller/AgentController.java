package com.hc.proxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
=======
>>>>>>> 15d0fae8e09d877a1922d4993bf8a57d8b0af22a
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
