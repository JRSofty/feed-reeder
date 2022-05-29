package com.jrsofty.web.feeder.commons.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    public static Logger getLogger(Class<?> clzz) {
        return LogManager.getLogger(clzz);
    }

}
