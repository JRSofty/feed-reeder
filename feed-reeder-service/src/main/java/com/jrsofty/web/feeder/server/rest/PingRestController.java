package com.jrsofty.web.feeder.server.rest;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.StandardRestResponse;

@RestController
@RequestMapping("/ping")
public class PingRestController {

    private static Logger LOGGER = LogUtil.getLogger(PingRestController.class);

    public PingRestController() {
        PingRestController.LOGGER.debug("Ping REST Endpoint Available");
    }

    @GetMapping("")
    public StandardRestResponse getPing() {
        PingRestController.LOGGER.debug("Ping requested");
        return new StandardRestResponse("ok", 200);
    }

}
