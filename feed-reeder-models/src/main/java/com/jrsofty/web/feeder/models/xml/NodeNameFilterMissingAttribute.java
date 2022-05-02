package com.jrsofty.web.feeder.models.xml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class NodeNameFilterMissingAttribute implements NodeFilter {

    private final String nodeName;
    private final String attribute;

    public NodeNameFilterMissingAttribute(String nodeName, String attribute) {
        this.nodeName = nodeName;
        this.attribute = attribute;
    }

    @Override
    public short acceptNode(Node n) {
        if (n.getNodeName().equals(this.nodeName)) {
            final NamedNodeMap attr = n.getAttributes();
            if ((attr == null) || (attr.getNamedItem(this.attribute) == null)) {
                return NodeFilter.FILTER_ACCEPT;
            }
        }
        return NodeFilter.FILTER_REJECT;
    }

}
