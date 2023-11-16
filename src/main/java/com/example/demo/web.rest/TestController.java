package com.example.demo.web.rest;

import com.example.demo.responses.TestResponse;
import com.example.demo.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class TestController {

    private final AuthenticateService authenticateService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(
            HttpServletRequest request) {
        if (!authenticateService.validateToken(request)) {
            return ResponseEntity.ok(new TestResponse("400", "failed"));
        }

        return ResponseEntity.ok(new TestResponse("200", "OK"));
    }
}
