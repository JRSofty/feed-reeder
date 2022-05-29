package com.jrsofty.web.feeder.xml.engine.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.models.domain.FeedItem;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.xml.NodeNameFilter;
import com.jrsofty.web.feeder.test.util.LoadTestResource;
import com.jrsofty.web.feeder.xml.engine.Engine;

@SpringBootTest(classes = Engine.class)
class EngineTest {

    @Autowired
    Engine engine;

    @Test
    void parseTestXKCD() throws IOException, JRSEngineException, DOMException, ParseException {
        final String xmlToParse = LoadTestResource.getFeedFileAsString("xkcd.xml");
        final Document doc = this.engine.generateDocumentFromByte(xmlToParse.getBytes());
        final NodeIterator itr = this.engine.filterDocument(doc, new NodeNameFilter("item"));
        Element current = null;
        final SimpleDateFormat sdf = new SimpleDateFormat();
        int cnt = 0;
        final HashSet<FeedItem> items = new HashSet<>();
        while ((current = (Element) itr.nextNode()) != null) {

            final Element title = (Element) current.getElementsByTagName("title").item(0);
            final Element pubDate = (Element) current.getElementsByTagName("pubDate").item(0);
            final Element link = (Element) current.getElementsByTagName("link").item(0);
            final Element descript = (Element) current.getElementsByTagName("description").item(0);

            final FeedItem item = new FeedItem();
            item.setTitle(title.getTextContent());
            item.setPubDate(pubDate.getTextContent());
            item.setDescription(descript.getTextContent());
            item.setLinkUrl(link.getTextContent());
            item.setReceived(new Date());
            items.add(item);
            cnt++;

        }

        Assertions.assertTrue(items.size() == cnt);
    }

}
