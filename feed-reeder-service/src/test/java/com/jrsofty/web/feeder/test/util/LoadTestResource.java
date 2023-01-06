package com.jrsofty.web.feeder.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.core.util.IOUtils;

public class LoadTestResource {

    public static String getFeedFileAsString(String filename) throws IOException {
        if (!filename.startsWith("feeds/")) {
            filename = "feeds/" + filename;
        }
        final InputStream in = LoadTestResource.class.getClassLoader().getResourceAsStream(filename);
        final String result = LoadTestResource.convertStreamToString(in);
        return result;

    }

    // Originally Apache had a simple Streams.asString() but for some reason
    // they decided to get rid of it.
    private static String convertStreamToString(final InputStream in) throws IOException {
        final StringWriter writer = new StringWriter();
        final Reader fixForFuckingApacheChangingTheirInterface = new InputStreamReader(in, StandardCharsets.UTF_8);
        IOUtils.copy(fixForFuckingApacheChangingTheirInterface, writer);
        return writer.toString();

    }

}
