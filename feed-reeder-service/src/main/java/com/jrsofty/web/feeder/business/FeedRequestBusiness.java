package com.jrsofty.web.feeder.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jrsofty.web.feeder.models.job.FeedRequestInterface;
import com.jrsofty.web.feeder.persistence.dao.impl.FeedItemDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Component
@Transactional
public class FeedRequestBusiness implements FeedRequestInterface {

    @Autowired
    private FeedItemDAO feedItemDAO;
    @Autowired
    private WebFeedDAO webFeedDAO;

    @Override
    public String getRequestFeedData(long id) {

        return "Getting data for " + id;
    }

    @Transactional
    @Override
    public void processFeedData(String data) {
        System.out.println(data);
    }

}
