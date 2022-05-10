package com.jrsofty.web.feeder.services;

import java.text.ParseException;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.job.FeedRequestJob;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Service
@Scope("singleton")
public class ScheduledJobService {

    @Autowired
    public static ScheduledJobService INSTANCE;

    @Autowired
    private WebFeedDAO feedDAO;

    private final Scheduler scheduler;

    public ScheduledJobService() throws SchedulerException {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        this.scheduler.start();
    }

    public void initService() throws ParseException, SchedulerException {
        final List<WebFeed> feeds = this.feedDAO.findAll();
        for (final WebFeed feed : feeds) {
            this.scheduleFeed(feed);
        }
    }

    public void shutdownScheduler() throws SchedulerException {
        this.scheduler.shutdown();
    }

    private void scheduleFeed(WebFeed feed) throws ParseException, SchedulerException {
        System.out.println("Attempting to schedule " + feed.getTitle());
        final String cronExpression = feed.getCronExpression();
        final JobDetail jobDetail = JobBuilder.newJob(FeedRequestJob.class).withIdentity(feed.getTitle(), "feeds").usingJobData("id", feed.getId()).build();
        final Trigger trigger = TriggerBuilder.newTrigger().withIdentity(feed.getTitle() + "-trg", "triggers").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).forJob(jobDetail).build();
        this.scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("Scheduled " + feed.getTitle());

    }
}
