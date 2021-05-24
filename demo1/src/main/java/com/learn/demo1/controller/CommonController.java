package com.learn.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * CommonController.java
 *
 * @author Ponder Yao
 * @version 1.0.0
 * @description 普通与公共接口
 * @date 2021/2/14 17:33
 */
@RestController
public class CommonController {

    @GetMapping("/start")
    public Map<String, Object> startTestDemo(@RequestParam(value = "target", required = true) String target) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("success", true);
        returnMap.put("message", "访问成功！");
        returnMap.put("data", "Hello " + target);
        return returnMap;
    }
}
