package com.jrsofty.web.feeder.business;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.FeedItem;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.job.FeedRequestInterface;
import com.jrsofty.web.feeder.models.xml.NodeNameFilter;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;
import com.jrsofty.web.feeder.xml.engine.Engine;

@Component
@Transactional
public class FeedRequestBusiness implements FeedRequestInterface {

    private static Logger LOG = LogUtil.getLogger(FeedRequestBusiness.class);
    @Autowired
    private WebFeedDAO webFeedDAO;
    @Autowired
    private RestTemplate restClient;
    @Autowired
    private Engine engine;

    @Transactional
    @Override
    public String getRequestFeedData(long id) {
        FeedRequestBusiness.LOG.debug("Requesting feed data from id " + id);
        final WebFeed webFeed = this.webFeedDAO.findById(id);
        FeedRequestBusiness.LOG.debug("Feed name is: " + webFeed.getTitle());
        final String url = webFeed.getFeedUrl();
        final ResponseEntity<String> response = this.restClient.getForEntity(url, String.class);
        webFeed.setLastUpdateAttempt(new Date());
        if (response.getStatusCodeValue() != 200) {
            FeedRequestBusiness.LOG.error(String.format("Errored Response code %s from url %s. Could not get data returning empty", response.getStatusCodeValue(), url));
            webFeed.setLastUpdateFailure(new Date());
            webFeed.setLastFailureReason(String.format("HTTP STATUS: %s", response.getStatusCodeValue()));
            return "";
        }
        this.webFeedDAO.store(webFeed);
        FeedRequestBusiness.LOG.debug("Request finished");
        return response.getBody();
    }

    @Transactional
    @Override
    public void processFeedData(String data, long feedId) {
        FeedRequestBusiness.LOG.debug("Parsing data from feed response");
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
            FeedRequestBusiness.LOG.error("Failed to extract item data from feed.", e);
            feed.setLastFailureReason(e.getMessage());
            feed.setLastUpdateFailure(new Date());
        } finally {
            this.webFeedDAO.store(feed);
        }
        FeedRequestBusiness.LOG.debug("Completed parsing data from feed response");
    }

}
