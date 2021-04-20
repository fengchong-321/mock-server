package com.fc.mockserver.observer;

import com.fc.mockserver.Utils.ServletParamterUtils;
import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.model.RequestHeader;
import com.fc.mockserver.model.RequestParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 做url的请求解析，匹配相关参数，存到MockContext里
 */
@Slf4j
public class ParamterResolveObserver implements MockObserver {

    @Override
    public void update(MockContext context) {
        HttpServletRequest servletRequest = context.getServletRequest();

        RequestParameter parameter = new RequestParameter();
        //  记录请求uri，并去掉/
        parameter.setReqUri(StringUtils.substringAfter(servletRequest.getRequestURI(), "/"));
        //  记录发起请求的IP
        parameter.setReqIp(servletRequest.getRemoteAddr());
        //  记录请求的请求参数
        parameter.setParamMap(ServletParamterUtils.getParam(servletRequest.getParameterMap()));
//        log.info("parameter = {}", parameter.getParamMap().toString());
//        log.info("uri = {}", parameter.getReqUri());
        context.setRequestParameter(parameter);

        //  记录发起请求的请求头
        RequestHeader header = new RequestHeader();
        header.setContentType(servletRequest.getContentType());
        header.setMethodType(servletRequest.getMethod());
        context.setHeader(header);
    }
}
