package com.jrsofty.web.feeder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.engine.Engine;
import com.jrsofty.web.feeder.models.domain.FeedType;
import com.jrsofty.web.feeder.models.domain.GroupFeed;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.domain.xml.NodeNameFilter;
import com.jrsofty.web.feeder.persistence.dao.impl.GroupFeedDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Component
public class OpmlBusiness {

    @Autowired
    private Engine engine;
    @Autowired
    GroupFeedDAO groupsDAO;
    @Autowired
    WebFeedDAO feedsDAO;

    public void uploadOpml(byte[] contents) throws JRSEngineException {
        final Document opmlDoc = this.engine.generateDocumentFromByte(contents);
        final NodeIterator itr = this.engine.filterDocument(opmlDoc, new NodeNameFilter("outline"));
        Node node = null;
        GroupFeed group = null;
        while ((node = itr.nextNode()) != null) {
            final Element ele = (Element) node;
            if (ele.hasAttribute("xmlUrl")) {
                final WebFeed feed = new WebFeed();
                feed.setTitle(ele.getAttribute("title"));
                feed.setDescription(ele.getAttribute("text"));
                feed.setFeedUrl(ele.getAttribute("xmlUrl"));
                feed.setHtmlUrl(ele.getAttribute("htmlUrl"));
                feed.setFeedType(FeedType.valueOf(ele.getAttribute("type").toUpperCase()));
            } else {
                group = new GroupFeed();
                group.setTitle(ele.getAttribute("text"));
            }
        }
    }
}
