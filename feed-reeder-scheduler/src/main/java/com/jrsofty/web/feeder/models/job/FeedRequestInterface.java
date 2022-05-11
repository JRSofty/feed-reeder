package com.jrsofty.web.feeder.models.job;

public interface FeedRequestInterface {

    String getRequestFeedData(long id);

    void processFeedData(String data);

}
