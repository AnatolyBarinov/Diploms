package iiot.istok.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenRegistrar {
    String register(UserDetails userDetails);

    boolean isRegistered(String token);

    void revoke(String clientId, String token);
}