package com.jrsofty.web.feeder.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jrsofty.web.feeder.models.domain.WebFeed;
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
    @Autowired
    private RestTemplate restClient;

    @Transactional
    @Override
    public String getRequestFeedData(long id) {
        final WebFeed webFeed = this.webFeedDAO.findById(id);
        final String url = webFeed.getFeedUrl();
        final ResponseEntity<String> response = this.restClient.getForEntity(url, String.class);
        if (response.getStatusCodeValue() != 200) {
            // TODO Log error and make comment in the webFeed object
            return "";
        }

        return response.getBody();
    }

    @Transactional
    @Override
    public void processFeedData(String data, long feedId) {
        final WebFeed feed = this.webFeedDAO.findById(feedId);

    }

}
