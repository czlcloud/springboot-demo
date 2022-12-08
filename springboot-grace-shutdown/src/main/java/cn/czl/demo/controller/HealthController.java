package cn.czl.demo.controller;

import cn.czl.demo.common.system.HealthCache;
import cn.czl.demo.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zack chen
 * @Date: 2022/12/8 20:16
 */
@RestController
@RequestMapping("")
public class HealthController {

    @GetMapping("health")
    public Response health(){
        return Response.builder().status(HealthCache.getState()).build();
    }
}
