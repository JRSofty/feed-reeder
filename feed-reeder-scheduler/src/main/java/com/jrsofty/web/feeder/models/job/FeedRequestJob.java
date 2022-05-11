package com.jrsofty.web.feeder.models.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedRequestJob implements Job {

    @Autowired
    FeedRequestInterface feedRequestBusiness;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final long id = context.getMergedJobDataMap().getLong("id");
        this.feedRequestBusiness.processFeedData(this.feedRequestBusiness.getRequestFeedData(id));
    }

}
