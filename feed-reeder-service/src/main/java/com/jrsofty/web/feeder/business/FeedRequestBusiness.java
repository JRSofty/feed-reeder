package com.jrsofty.web.feeder.business;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.models.domain.FeedItem;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.job.FeedRequestInterface;
import com.jrsofty.web.feeder.models.xml.NodeNameFilter;
import com.jrsofty.web.feeder.persistence.dao.impl.FeedItemDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;
import com.jrsofty.web.feeder.xml.engine.Engine;

@Component
@Transactional
public class FeedRequestBusiness implements FeedRequestInterface {

    @Autowired
    private FeedItemDAO feedItemDAO;
    @Autowired
    private WebFeedDAO webFeedDAO;
    @Autowired
    private RestTemplate restClient;
    @Autowired
    private Engine engine;

    @Transactional
    @Override
    public String getRequestFeedData(long id) {
        final WebFeed webFeed = this.webFeedDAO.findById(id);
        final String url = webFeed.getFeedUrl();
        final ResponseEntity<String> response = this.restClient.getForEntity(url, String.class);
        webFeed.setLastUpdateAttempt(new Date());
        if (response.getStatusCodeValue() != 200) {
            // TODO Log error and make comment in the webFeed object
            return "";
        }
        this.webFeedDAO.store(webFeed);

        return response.getBody();
    }

    @Transactional
    @Override
    public void processFeedData(String data, long feedId) {
        final WebFeed feed = this.webFeedDAO.findById(feedId);
        try {

            final Document feedDoc = this.engine.generateDocumentFromByte(data.getBytes());
            final NodeIterator itemNodes = this.engine.filterDocument(feedDoc, new NodeNameFilter("item"));
            Element feedItem = null;
            while ((feedItem = (Element) itemNodes.nextNode()) != null) {
                final Element title = (Element) feedItem.getElementsByTagName("title").item(0);
                final Element pubDate = (Element) feedItem.getElementsByTagName("pubDate").item(0);
                final Element link = (Element) feedItem.getElementsByTagName("link").item(0);
                final Element descript = (Element) feedItem.getElementsByTagName("description").item(0);

                final FeedItem dbItem = new FeedItem();
                dbItem.setTitle(title.getTextContent());
                dbItem.setPubDate(pubDate.getTextContent());
                dbItem.setDescription(descript.getTextContent());
                dbItem.setLinkUrl(link.getTextContent());
                dbItem.setReceived(new Date());
                if (!feed.getFeeditems().contains(dbItem)) {
                    feed.addFeeditem(dbItem);
                }
            }
            feed.setLastUpdateSuccess(new Date());
        } catch (final JRSEngineException e) {
            // Logger
            feed.setLastFailureReason(e.getMessage());
            feed.setLastUpdateFailure(new Date());
        } finally {
            this.webFeedDAO.store(feed);
        }
    }

}
