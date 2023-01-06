package com.jrsofty.web.feeder.server.rest;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jrsofty.web.feeder.business.OpmlBusiness;
import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.exceptions.JRSEngineException;
import com.jrsofty.web.feeder.models.domain.rest.StandardRestResponse;

import jakarta.transaction.Transactional;

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
    public ResponseEntity<StandardRestResponse> uploadOPML(@RequestParam("file") final MultipartFile file) {
        StandardRestResponse res = null;
        try {
            if (file == null) {
                res = new StandardRestResponse("Failed upload", 400);
            }
            this.business.uploadOpml(file.getBytes());
        } catch (JRSEngineException | IOException e) {
            OpmlRestController.LOGGER.error("Failure to process upload", e);
            res = new StandardRestResponse("Failed upload", 500);
        }

        if (res == null) {
            res = new StandardRestResponse("Upload Successful", 200);
        }

        return ResponseEntity.status(res.getStatus()).body(res);

    }

}
