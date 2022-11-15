package com.jrsofty.web.feeder.server.rest;

import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.business.FeedBusiness;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;

@Transactional
@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    FeedBusiness business;

    @GetMapping("/tree")
    public TreeSet<TreeItem> getFeedsAsTree() {
        return this.business.getItemTree();
    }

    @GetMapping("/items/web/{id}")
    public Set<TreeItem> getWebFeedItemsById(@PathVariable long id) {
        return this.business.findFeedItemsByParentId(id);
    }

}
