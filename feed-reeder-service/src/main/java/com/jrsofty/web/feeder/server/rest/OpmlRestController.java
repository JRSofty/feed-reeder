package com.jrsofty.web.feeder.server.rest;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jrsofty.web.feeder.business.OpmlBusiness;
import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.StandardRestResponse;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;

@Transactional
@RestController
@RequestMapping("/opml")
public class OpmlRestController {

    private static Logger LOGGER = LogUtil.getLogger(OpmlRestController.class);

    @Autowired
    private OpmlBusiness business;

    public OpmlRestController() {
        OpmlRestController.LOGGER.debug("Opml RESTful Endpoint available");
    }

    // @GetMapping("")
    // public StandardRestResponse opmlAvailable() {
    // return new StandardRestResponse("Available", 200);
    // }

    @Transactional
    @PostMapping("/upload")
    public StandardRestResponse uploadOPML(@RequestParam("file") MultipartFile file) {

        try {
            if (file == null) {
                return new StandardRestResponse("Failed upload", 400);
            }
            this.business.uploadOpml(file.getBytes());
        } catch (JRSEngineException | IOException e) {
            OpmlRestController.LOGGER.error("Failure to process upload", e);
            return new StandardRestResponse("Failed upload", 500);
        }

        return new StandardRestResponse("Upload Successful", 200);

    }

}
