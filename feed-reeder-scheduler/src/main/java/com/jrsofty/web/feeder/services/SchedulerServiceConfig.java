package com.jrsofty.web.feeder.services;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerServiceConfig {

    @Bean(initMethod = "initService", destroyMethod = "shutdownScheduler")
    public ScheduledJobService getScheduledJobService() throws SchedulerException {
        return new ScheduledJobService();
    }

}
