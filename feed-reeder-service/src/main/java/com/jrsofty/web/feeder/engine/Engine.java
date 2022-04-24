package com.jrsofty.web.feeder.engine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;

@Component
public class Engine {

    public Document generateDocumentFromByte(byte[] data) throws JRSEngineException {

        try {
            final ByteArrayInputStream bais = new ByteArrayInputStream(data);
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(bais);
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            throw new JRSEngineException(e);
        }

    }

    public Document generateDocumentFromInputStream(InputStream is) throws JRSEngineException {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance("net.sf.saxon.om.DocumentBuilderFactoryImpl", this.getClass().getClassLoader());
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(is);
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            throw new JRSEngineException(e);
        }
    }

    public NodeIterator filterDocument(Document document, NodeFilter filter) {
        final DocumentTraversal trav = (DocumentTraversal) document;
        final NodeIterator itr = trav.createNodeIterator(document.getDocumentElement(), NodeFilter.SHOW_ALL, filter, true);
        return itr;
    }

}