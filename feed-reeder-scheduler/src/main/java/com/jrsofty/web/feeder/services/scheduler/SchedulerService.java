package com.jrsofty.web.feeder.services.scheduler;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.job.FeedRequestInterface;
import com.jrsofty.web.feeder.models.job.FeedRequestJob;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;
import com.jrsofty.web.feeder.services.spring.SchedulerServiceConfig;

@Service("SchedulerService")
@Scope("singleton")
public class SchedulerService {

    public static int instanceCount = 0;
    private static Logger LOG = LogUtil.getLogger(SchedulerService.class);

    @Autowired
    SchedulerFactoryBean schedulerFactory;

    @Autowired
    WebFeedDAO feedDao;

    @Autowired
    FeedRequestInterface feedRequest;

    Scheduler scheduler;

    public SchedulerService() {
        SchedulerService.instanceCount++;
        if (SchedulerService.instanceCount > 1) {
            SchedulerService.LOG.error("Attempted to initialize scheduler twice.");
            throw new RuntimeException("There should only be one instance of SchedulerService you are trying to create " + SchedulerService.instanceCount);
        }

    }

    public void initFeeds() {
        SchedulerService.LOG.debug("Initializing Feeds");
        final List<WebFeed> feeds = this.feedDao.findAll();
        for (final WebFeed feed : feeds) {
            try {
                this.scheduleFeed(feed);
                final String data = this.feedRequest.getRequestFeedData(feed.getId());
                this.feedRequest.processFeedData(data, feed.getId());
            } catch (final SchedulerException | ParseException e) {
                SchedulerService.LOG.error(String.format("Failed to initialize feed: %s %s", feed.getId(), feed.getTitle()));
            }
        }
        SchedulerService.LOG.debug("Feeds Initialized");
    }

    public void startScheduler() {
        this.schedulerFactory.start();
    }

    public void shutdownScheduler() {
        this.schedulerFactory.stop();
    }

    public void scheduleFeed(WebFeed feed) throws SchedulerException, ParseException {
        SchedulerService.LOG.debug(String.format("Scheduling Feed id: %s name: %s with expression %s", feed.getId(), feed.getTitle(), feed.getCronExpression()));
        final JobDetail jobDetail = SchedulerServiceConfig.createJobDetail(FeedRequestJob.class, feed).getObject();
        final CronTrigger jobTrigger = SchedulerServiceConfig.createCronTrigger(jobDetail, feed.getCronExpression(), feed.getTitle()).getObject();
        this.schedulerFactory.getScheduler().scheduleJob(jobDetail, jobTrigger);
        SchedulerService.LOG.debug(String.format("Feed id: %s name: %s is scheduled", feed.getId(), feed.getTitle()));
    }

}
