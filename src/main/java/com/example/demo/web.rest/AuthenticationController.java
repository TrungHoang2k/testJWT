package com.example.demo.web.rest;

import com.example.demo.requests.AuthenticationRequest;
import com.example.demo.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticateService.login(request));
    }
}
