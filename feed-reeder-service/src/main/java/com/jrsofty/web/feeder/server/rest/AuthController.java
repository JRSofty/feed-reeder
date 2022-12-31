package com.jrsofty.web.feeder.server.rest;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.commons.logging.LogUtil;
import com.jrsofty.web.feeder.models.domain.rest.StandardRestResponse;
import com.jrsofty.web.feeder.persistence.dao.impl.UserDAO;

@Transactional
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static Logger LOG = LogUtil.getLogger(AuthController.class);

    @Autowired
    private UserDAO users;

    public AuthController() {
        AuthController.LOG.debug("Authentication REST Endpoint Available");
    }

    @GetMapping("/isReady")
    @Transactional(readOnly = true)
    public ResponseEntity<StandardRestResponse> systemIsReady() {
        StandardRestResponse response = null;
        final long users = this.users.countUsers();
        if (users < 1L) {
            response = new StandardRestResponse("No users found", 403);
        } else {
            response = new StandardRestResponse("OK", 200);
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
