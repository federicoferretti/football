package com.dux.application.service;

import com.dux.application.request.AuthRequest;

public interface AuthenticationService {

    String signinLogin(AuthRequest request);
}
