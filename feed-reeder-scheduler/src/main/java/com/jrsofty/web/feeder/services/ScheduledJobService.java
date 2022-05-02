package com.jrsofty.web.feeder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Service
public class ScheduledJobService {

    @Autowired
    private WebFeedDAO feedDAO;

    public void initService() {
        final List<WebFeed> feeds = this.feedDAO.findAll();

    }

}
