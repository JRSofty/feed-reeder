package com.jrsofty.web.feeder.models.domain.xml;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class NodeNameFilter implements NodeFilter {

    private final String nodeName;

    public NodeNameFilter(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public short acceptNode(Node n) {
        if (n.getNodeName().equals(this.nodeName)) {
            return NodeFilter.FILTER_ACCEPT;
        }
        return NodeFilter.FILTER_REJECT;

    }

}
