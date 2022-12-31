package com.jrsofty.web.feeder.security;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jrsofty.web.feeder.commons.logging.LogUtil;

@Configuration
@PropertySource("file:${feeder.home}/conf/feeder.properties")
public class SecurityConfig {

    private static Logger LOG = LogUtil.getLogger(SecurityConfig.class);

    @Value("${feeder.home}")
    private String feederHome;

}
