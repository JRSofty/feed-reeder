package com.jrsofty.web.feeder.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.models.domain.StandardRestResponse;

@RestController
@RequestMapping("/ping")
public class PingRestController {

    @GetMapping("")
    public StandardRestResponse getPing() {
        return new StandardRestResponse("ok", 200);
    }

}
