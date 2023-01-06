package com.jrsofty.web.feeder.server.rest;

import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.business.FeedBusiness;
import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;

import jakarta.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/feed")
public class FeedController {

    private static Logger LOG = LogUtil.getLogger(FeedController.class);

    @Autowired
    FeedBusiness business;

    public FeedController() {
        FeedController.LOG.debug("Feed REST Endpoint Available");
    }

    @GetMapping("/tree")
    public TreeSet<TreeItem> getFeedsAsTree() {
        return this.business.getItemTree();
    }

    @GetMapping("/items/web/{id}")
    public Set<TreeItem> getWebFeedItemsById(@PathVariable final long id) {
        return this.business.findFeedItemsByParentId(id);
    }

}
