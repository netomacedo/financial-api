package com.financial.api.com.financial.api.config.token;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by netof on 07/04/2018.
 * Put refresh token after created in cookies HTTPS
 * Intercept the response from controller of type OAuth2AccessToken
 * To avoid the client access the refresh token
 */
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getName().equals("postAccessToken");//return true and call beforeBodyWrite
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body,
                                             MethodParameter methodParameter, MediaType mediaType,
                                             Class<? extends HttpMessageConverter<?>> aClass,
                                             ServerHttpRequest serverHttpRequest,
                                             ServerHttpResponse serverHttpResponse) {

        //convertting ServletServerHttpRequest to HttpServletRequest and  Response
        HttpServletRequest req = ((ServletServerHttpRequest)serverHttpRequest).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse)serverHttpResponse).getServletResponse();

        //casting to DefaultOAuth2AccessToken to remove using setRefreshToken null
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)body;

        String refreshToken = body.getRefreshToken().getValue();
        addRefreshTokenInCookie(refreshToken,req, resp);//add token to Cookie
        removeRefreshTokenBody(token);//remove cookie from body
        return body;
    }

    private void removeRefreshTokenBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);//removing
    }

    private void addRefreshTokenInCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie  = new Cookie("refreshToken", refreshToken);//creating cookie
        //Cookie properties
        refreshTokenCookie.setHttpOnly(true);//only HTTp access, JS cannot get it
        refreshTokenCookie.setSecure(false);//cookie must work only HTTPS? to do change to TRUE in PROD
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");//patch browser send
        refreshTokenCookie.setMaxAge(30);//expire in 30 days
        resp.addCookie(refreshTokenCookie);

    }
}
