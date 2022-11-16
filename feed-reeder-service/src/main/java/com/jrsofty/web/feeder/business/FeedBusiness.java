package com.jrsofty.web.feeder.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.FeedItem;
import com.jrsofty.web.feeder.models.domain.GroupFeed;
import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;
import com.jrsofty.web.feeder.persistence.dao.impl.FeedItemDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.GroupFeedDAO;
import com.jrsofty.web.feeder.persistence.dao.impl.WebFeedDAO;

@Component
@Transactional
public class FeedBusiness {

    @Autowired
    private GroupFeedDAO groupDAO;
    @Autowired
    private WebFeedDAO webfeedDAO;
    @Autowired
    private FeedItemDAO feedItemDAO;

    @Transactional
    public TreeSet<TreeItem> getItemTree() {
        final List<GroupFeed> groups = this.groupDAO.findAll();
        final TreeSet<TreeItem> results = new TreeSet<>((o1, o2) -> {
            if ((null != o1.getParentId()) && (null != o2.getParentId())) {
                return o1.getParentId().compareTo(o2.getParentId());
            } else {
                return Long.compare(o1.getId(), o2.getId()) + o1.getItemType().compareTo(o2.getItemType());
            }
        });

        final List<WebFeed> feedsUnderRoot = this.webfeedDAO.getFeedsUnderRoot();
        for (final WebFeed feed : feedsUnderRoot) {
            final TreeItem item = new TreeItem(feed);
            results.add(item);
        }
        this.findAndAddChildren(groups, results);

        return results;
    }

    @Transactional
    public Set<TreeItem> findFeedItemsByParentId(long id) {
        final List<FeedItem> feedItems = this.feedItemDAO.findItemsByParentId(id);
        final HashSet<TreeItem> results = new HashSet<>();
        for (final FeedItem item : feedItems) {
            final TreeItem ti = new TreeItem(item);
            results.add(ti);
        }

        return results;

    }

    private void findAndAddChildren(final List<GroupFeed> groups, final TreeSet<TreeItem> results) {
        for (final GroupFeed group : groups) {
            final List<WebFeed> children = this.webfeedDAO.findByParentId(group.getId());
            final List<GroupFeed> childGroups = this.groupDAO.findByParentId(group.getId());
            final TreeItem item = new TreeItem(group);
            results.add(item);
            for (final WebFeed child : children) {
                final TreeItem childItem = new TreeItem(child);
                item.getChildren().add(childItem);
                results.add(childItem);
            }
            this.findAndAddChildren(childGroups, results);

        }
    }

}
