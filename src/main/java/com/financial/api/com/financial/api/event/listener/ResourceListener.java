package com.financial.api.com.financial.api.event.listener;

import com.financial.api.com.financial.api.event.ResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Created by netof on 07/03/2018.
 */
@Component
public class ResourceListener implements ApplicationListener<ResourceEvent>{
    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {
        HttpServletResponse httpServletResponse = resourceEvent.getResponse();
        Long code = resourceEvent.getCode();

        addHeaderLocation(httpServletResponse, code);
    }

    private void addHeaderLocation(HttpServletResponse httpServletResponse, Long code) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(code).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());
    }
}
