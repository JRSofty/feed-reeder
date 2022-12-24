package com.jrsofty.web.feeder.security;

import java.io.File;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jrsofty.web.feeder.commons.logging.LogUtil;

@Configuration
public class SecurityConfig {

    private static Logger LOG = LogUtil.getLogger(SecurityConfig.class);

    @Value("${feeder.home}")
    private String feederHome;

    @Bean
    public void checkInitUser() {
        final String initPropertiesPath = new StringBuilder().append(this.feederHome).append("/init/init.properties").toString();
        final File propertiesFile = new File(initPropertiesPath);
        if (propertiesFile.isFile() && propertiesFile.exists()) {
            SecurityConfig.LOG.info("File exists");
        } else {
            SecurityConfig.LOG.info("File Not Found");
        }
    }

}
