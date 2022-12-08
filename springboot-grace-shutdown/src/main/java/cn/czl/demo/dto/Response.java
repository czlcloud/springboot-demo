package cn.czl.demo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: zack chen
 * @Date: 2022/12/8 20:50
 */
@Data
@Builder
public class Response {

    private String status;
    private Object data;

    public static Response ok(){
        return Response.builder().status("OK").build();
    }
}
