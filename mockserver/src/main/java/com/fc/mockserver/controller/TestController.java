package com.fc.mockserver.controller;

import com.fc.mockserver.Utils.ServletParamterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private HttpServletRequest servletRequest;

    @RequestMapping("/test")
    public String test() {
        log.info("test access start -----------------------");
        Map<String, String> paramMap = ServletParamterUtils.getParam(servletRequest.getParameterMap());
        paramMap.forEach((key, val) -> log.info("param key = {}, val = {}", key, val));
        log.info("test access end   -----------------------");
        return "success";
    }
}
