package com.jrsofty.web.feeder.test.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.util.Streams;

public class LoadTestResource {

    public static String getFeedFileAsString(String filename) throws IOException {
        if (!filename.startsWith("feeds/")) {
            filename = "feeds/" + filename;
        }
        final InputStream in = LoadTestResource.class.getClassLoader().getResourceAsStream(filename);
        final String result = Streams.asString(in);
        return result;

    }

}
