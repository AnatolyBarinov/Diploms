package iiot.istok.controllers;

import iiot.istok.request.LoginRequest;
import iiot.istok.responce.LoginResponse;
import iiot.istok.service.LoginService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.security.Principal;

@Validated
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final BearerTokenResolver bearerTokenResolver;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, Principal principal) throws ServletException {
        String token = bearerTokenResolver.resolve(request);
        loginService.logout(principal.getName(), token);
        request.logout();
    }

}
