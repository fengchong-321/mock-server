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

@RestController
@Slf4j
public class MockController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockController.class);

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
            LOGGER.error("do mock for uri={} failed.", servletRequest.getRequestURI(), e);
        }
        return "mock failed";
    }

    @RequestMapping("favicon.ico")
    public String icon() {
        return "https://www.baidu.com/favicon.ico";
    }
}
