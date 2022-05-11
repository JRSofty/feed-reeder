package com.jrsofty.web.feeder.services.scheduler;

import java.text.ParseException;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.job.FeedRequestJob;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;
import com.jrsofty.web.feeder.services.spring.SchedulerServiceConfig;

@Service("SchedulerService")
@Scope("singleton")
public class SchedulerService {

    public static int instanceCount = 0;

    @Autowired
    SchedulerFactoryBean schedulerFactory;

    @Autowired
    WebFeedDAO feedDao;

    Scheduler scheduler;

    public SchedulerService() {
        SchedulerService.instanceCount++;
        if (SchedulerService.instanceCount > 1) {
            System.out.println("It should only have once instance!!");
        }

    }

    public void initFeeds() {
        final List<WebFeed> feeds = this.feedDao.findAll();
        for (final WebFeed feed : feeds) {
            try {
                this.scheduleFeed(feed);
            } catch (final Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void startScheduler() {
        this.schedulerFactory.start();
    }

    public void shutdownScheduler() {
        this.schedulerFactory.stop();
    }

    public void scheduleFeed(WebFeed feed) throws SchedulerException, ParseException {
        final JobDetail jobDetail = SchedulerServiceConfig.createJobDetail(FeedRequestJob.class, feed).getObject();
        final CronTrigger jobTrigger = SchedulerServiceConfig.createCronTrigger(jobDetail, feed.getCronExpression(), feed.getTitle()).getObject();
        this.schedulerFactory.getScheduler().scheduleJob(jobDetail, jobTrigger);
    }

}
