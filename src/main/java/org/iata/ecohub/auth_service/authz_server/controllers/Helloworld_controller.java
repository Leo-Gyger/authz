package org.iata.ecohub.auth_service.authz_server.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helloworld_controller {

    @GetMapping("/")
        public String hello(Authentication authentication)
    {
        return "Hello " + authentication.getName() + "|";
    }
}
