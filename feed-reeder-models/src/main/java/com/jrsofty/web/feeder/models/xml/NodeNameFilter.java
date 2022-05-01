package com.jrsofty.web.feeder.models.xml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class NodeNameFilter implements NodeFilter {

    private final String nodeName;
    private String attribute = null;

    public NodeNameFilter(String nodeName) {
        this(nodeName, null);
    }

    public NodeNameFilter(String nodeName, String attribute) {
        this.nodeName = nodeName;
        this.attribute = attribute;
    }

    @Override
    public short acceptNode(Node n) {
        if (this.attribute != null) {
            final NamedNodeMap attr = n.getAttributes();
            if ((n.getNodeName().equals(this.nodeName) && (attr != null)) && (attr.getNamedItem(this.attribute) != null)) {
                return NodeFilter.FILTER_ACCEPT;
            }
        } else if (n.getNodeName().equals(this.nodeName)) {
            return NodeFilter.FILTER_ACCEPT;
        }
        return NodeFilter.FILTER_REJECT;

    }

}
