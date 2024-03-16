package iiot.istok.service.impl;

import iiot.istok.request.LoginRequest;
import iiot.istok.responce.LoginResponse;
import iiot.istok.service.LoginService;
import iiot.istok.service.TokenRegistrar;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRegistrar tokenRegistrar;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password.");
        }
        if (!userDetails.isEnabled()) {
            throw new BadCredentialsException("User is not enabled.");
        }
        String token = tokenRegistrar.register(userDetails);
        return new LoginResponse(token);
    }

    @Override
    public void logout(String login, String token) {
        tokenRegistrar.revoke(login, token);
    }

}