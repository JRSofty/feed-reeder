package com.jrsofty.web.feeder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Service
@Scope("singleton")
public class ScheduledJobService {

    @Autowired
    private WebFeedDAO feedDAO;

    public void initService() {
        // final List<WebFeed> feeds = this.feedDAO.findAll();
        System.out.println("ScheduledJobService.initService called");
    }

}
