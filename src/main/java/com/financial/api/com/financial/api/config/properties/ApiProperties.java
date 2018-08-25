package com.financial.api.com.financial.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by netof on 25/08/2018.
 * Class to change config in this project automatically
 */
@ConfigurationProperties("apiproperties")
public class ApiProperties {

    private String originAllowed = "http://localhost:4200";
    private final Security security = new Security();

    public String getOriginAllowed() {
        return originAllowed;
    }

    public void setOriginAllowed(String originAllowed) {
        this.originAllowed = originAllowed;
    }

    public Security getSecurity() {
        return security;
    }

    public static class Security{
        private boolean enableHttpsToProd;

        public boolean isEnableHttpsToProd() {
            return enableHttpsToProd;
        }

        public void setEnableHttpsToProd(boolean enableHttpsToProd) {
            this.enableHttpsToProd = enableHttpsToProd;
        }
    }
}
