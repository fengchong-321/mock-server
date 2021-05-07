package com.fc.mockserver.controller;

import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.service.ObserverService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * controller层
 */
@RestController
@Slf4j
public class MockController {

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private ObserverService observerService;

    @RequestMapping("/**")
    public String domock() {
        try {
            MockContext context = new MockContext(this.servletRequest);
            observerService.execute(context);
            return context.getTargerMockData();
        } catch (Exception e) {
            log.error("uri={} mock失败.", servletRequest.getRequestURI(), e);
        }
        return "mock failed";
    }

    //  百度搜索的图标处理，不然会产生干扰
    @RequestMapping("favicon.ico")
    public String icon() {
        return "https://www.baidu.com/favicon.ico";
    }
}
