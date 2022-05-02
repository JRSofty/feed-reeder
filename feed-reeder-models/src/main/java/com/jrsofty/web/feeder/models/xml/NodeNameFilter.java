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
        if (n.getNodeName().equals(this.nodeName)) {
            if (this.attribute != null) {
                final NamedNodeMap attr = n.getAttributes();
                if (attr != null) {
                    if (attr.getNamedItem(this.attribute) != null) {
                    }
                } else {
                    // No attributes means the one we want is not there so
                    // reject
                    return NodeFilter.FILTER_REJECT;
                }
            }
            // No attribute search is needed but name matches
            return NodeFilter.FILTER_ACCEPT;
        }
        // Node name does not match
        return NodeFilter.FILTER_REJECT;

    }

}
