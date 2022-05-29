package com.jrsofty.web.feeder.xml.engine.test;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.xml.NodeNameFilter;
import com.jrsofty.web.feeder.test.util.LoadTestResource;
import com.jrsofty.web.feeder.xml.engine.Engine;

@SpringBootTest(classes = Engine.class)
class EngineTest {

    @Autowired
    Engine engine;

    @Test
    void parseTest() throws IOException, JRSEngineException {
        final String xmlToParse = LoadTestResource.getFeedFileAsString("xkcd.xml");
        final Document doc = this.engine.generateDocumentFromByte(xmlToParse.getBytes());
        final NodeIterator itr = this.engine.filterDocument(doc, new NodeNameFilter("item"));
        Element current = null;
        while ((current = (Element) itr.nextNode()) != null) {
            final Element title = (Element) current.getElementsByTagName("title").item(0);
            if (title != null) {
                System.out.println(title.getTextContent());
            }
        }

        Assertions.assertTrue(true);
    }

}
