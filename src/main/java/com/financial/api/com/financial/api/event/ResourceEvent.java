package com.financial.api.com.financial.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by netof on 06/03/2018.
 */
public class ResourceEvent extends ApplicationEvent{

    private HttpServletResponse response;
    private Long code;

    public ResourceEvent(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getCode() {
        return code;
    }
}
