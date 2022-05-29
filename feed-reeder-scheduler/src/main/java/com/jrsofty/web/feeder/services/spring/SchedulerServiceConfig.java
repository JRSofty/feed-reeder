package com.jrsofty.web.feeder.services.spring;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.services.scheduler.SchedulerService;

@Configuration
@EnableScheduling
public class SchedulerServiceConfig {

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        final AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(this.ctx);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler() {
        final SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        final Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceName", "feed-reeder-scheduler");
        properties.setProperty("org.quartz.threadPool.threadCount", "5");
        properties.setProperty("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");

        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setQuartzProperties(properties);
        schedulerFactory.setJobFactory(this.springBeanJobFactory());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.start();
        return schedulerFactory;
    }

    @Bean(initMethod = "initFeeds")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SchedulerService getSchedulerService() {
        return (SchedulerService) this.ctx.getBean("SchedulerService");
    }

    public static CronTriggerFactoryBean createCronTrigger(JobDetail job, String cronExpression, String triggerName) throws ParseException {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        final CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setStartTime(calendar.getTime());
        factory.setCronExpression(cronExpression);
        factory.setName(triggerName);
        factory.setJobDetail(job);
        factory.setStartDelay(0L);
        factory.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        factory.afterPropertiesSet();
        return factory;

    }

    public static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass, WebFeed feed) {
        final JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setName(feed.getTitle());
        final HashMap<String, Long> dataMap = new HashMap<>();
        dataMap.put("id", feed.getId());
        factoryBean.setDurability(false);
        factoryBean.setJobDataAsMap(dataMap);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

}
