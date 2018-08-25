package com.financial.api.com.financial.api.controller;

import com.financial.api.com.financial.api.config.properties.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by netof on 23/08/2018.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private ApiProperties apiProperties;

    /*Logout, client delete access token and remove refresh token from cookie*/
    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(apiProperties.getSecurity().isEnableHttpsToProd());//false to DEV, true to PROD
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);//expire NOW

        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
