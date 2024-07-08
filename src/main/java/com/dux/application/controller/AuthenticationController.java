package com.dux.application.controller;

import com.dux.application.request.AuthRequest;
import com.dux.application.response.AuthenticationResponse;
import com.dux.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest request) {
        String token = authenticationService.signinLogin(request);

        return new ResponseEntity<>(AuthenticationResponse.from(token), HttpStatus.OK);
    }
}
