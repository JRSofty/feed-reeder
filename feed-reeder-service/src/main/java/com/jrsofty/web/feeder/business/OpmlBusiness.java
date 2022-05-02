package com.jrsofty.web.feeder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.models.domain.FeedType;
import com.jrsofty.web.feeder.models.domain.GroupFeed;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.xml.NodeNameFilter;
import com.jrsofty.web.feeder.persistence.dao.impl.GroupFeedDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;
import com.jrsofty.web.feeder.xml.engine.Engine;

@Component
@Transactional
public class OpmlBusiness {

    @Autowired
    private Engine engine;
    @Autowired
    GroupFeedDAO groupsDAO;
    @Autowired
    WebFeedDAO feedsDAO;

    @Transactional
    public void uploadOpml(byte[] contents) throws JRSEngineException {
        final Document opmlDoc = this.engine.generateDocumentFromByte(contents);
        final Node bodyNode = this.getOpmlBodyNode(opmlDoc);
        this.processNodeIterator(bodyNode, null);
    }

    private void processNodeIterator(Node parentNode, GroupFeed parent) {
        Node node = parentNode.getFirstChild();
        do {
            final Element ele = (Element) node;
            if (ele.hasAttribute("xmlUrl")) {
                WebFeed feed = new WebFeed();
                feed.setTitle(ele.getAttribute("title"));
                feed.setDescription(ele.getAttribute("text"));
                feed.setFeedUrl(ele.getAttribute("xmlUrl"));
                feed.setHtmlUrl(ele.getAttribute("htmlUrl"));
                feed.setFeedType(FeedType.valueOf(ele.getAttribute("type").toUpperCase()));
                if (parent != null) {
                    parent.addChildFeed(feed);
                    parent = this.groupsDAO.store(parent);
                } else {
                    feed = this.feedsDAO.store(feed);
                }
            } else {
                GroupFeed groupItem = new GroupFeed();
                groupItem.setTitle(ele.getAttribute("text"));
                groupItem.setDescription(ele.getAttribute("text"));
                if (parent != null) {
                    parent.addChildGroup(groupItem);
                    parent = this.groupsDAO.store(parent);
                    groupItem = this.groupsDAO.findByNameAndParent(parent,
                            groupItem.getTitle());
                } else {
                    groupItem = this.groupsDAO.store(groupItem);
                }
                this.processNodeIterator(node, groupItem);
            }

        }
        while ((node = node.getNextSibling()) != null);

    }

    private Node getOpmlBodyNode(Document document) {
        final NodeIterator itr = this.engine.filterDocument(document.getDocumentElement(), new NodeNameFilter("body"));
        final Node node = itr.nextNode();
        return node;
    }

}
