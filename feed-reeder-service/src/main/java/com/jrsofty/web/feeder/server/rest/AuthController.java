package com.jrsofty.web.feeder.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrsofty.web.feeder.models.domain.rest.StandardRestResponse;
import com.jrsofty.web.feeder.persistence.dao.impl.UserDAO;

@Transactional
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDAO users;

    @GetMapping("/isReady")
    @Transactional(readOnly = true)
    public StandardRestResponse systemIsReady() {
        StandardRestResponse response = null;
        final long users = this.users.countUsers();
        if (users < 1L) {
            response = new StandardRestResponse("No users found", 403);
        } else {
            response = new StandardRestResponse("OK", 200);
        }

        return response;
    }
}
