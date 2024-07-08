package com.dux.application.service.impl;

import com.dux.application.exceptionhandler.exception.LoginException;
import com.dux.application.request.AuthRequest;
import com.dux.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private UserDetailsService userDetailsServiceMock;
    @Mock
    private JwtService jwtServiceMock;
    @Mock
    private AuthenticationManager authenticationManagerMock;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;


    @Test
    public void testLoginSuccessfully() {
        String username = "testuser";
        String password = "testpassword";
        String token = "mocked-jwt-token";

        AuthRequest request = new AuthRequest();
        request.setUsername(username);
        request.setPassword(password);

        UserDetails userDetails = new User(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        when(authenticationManagerMock.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsServiceMock.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtServiceMock.generateToken(userDetails)).thenReturn(token);

        String generatedToken = authenticationService.signinLogin(request);

        assertEquals(token, generatedToken);
    }

    @Test
    void shouldThrowLoginExceptionWhenAuthenticationFails() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("wrongpassword");

        when(authenticationManagerMock.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(LoginException.class, () -> authenticationService.signinLogin(authRequest));

        verify(authenticationManagerMock, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsServiceMock, never()).loadUserByUsername(anyString());
        verify(jwtServiceMock, never()).generateToken(any(UserDetails.class));
    }
}
