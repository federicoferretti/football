package com.dux.application.service.impl;

import com.dux.application.exceptionhandler.exception.LoginException;
import com.dux.application.request.AuthRequest;
import com.dux.application.service.AuthenticationService;
import com.dux.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDetailsService usersDetailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String signinLogin(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = usersDetailService.loadUserByUsername(request.getUsername());
            return jwtService.generateToken(user);
        } catch (Exception ex) {
            throw new LoginException("Usuario o Contraseña incorrecta", 401);
        }
    }
}
