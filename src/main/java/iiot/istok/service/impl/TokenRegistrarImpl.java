package iiot.istok.service.impl;

import iiot.istok.entity.RegisteredToken;
import iiot.istok.repository.RegisteredTokenRepository;
import iiot.istok.service.TokenProducer;
import iiot.istok.service.TokenRegistrar;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TokenRegistrarImpl implements TokenRegistrar {

    private final TokenProducer tokenProducer;
    private final RegisteredTokenRepository registeredTokenRepository;

    @Override
    @Transactional
    public String register(UserDetails userDetails) {
        OAuth2Token token = tokenProducer.generateToken(userDetails);
        register(userDetails.getUsername(), token);
        return token.getTokenValue();
    }

    private void register(String clientId, OAuth2Token token) {
        registeredTokenRepository.saveAndFlush(
                new RegisteredToken(
                        token.getTokenValue(),
                        clientId,
                        token.getIssuedAt(),
                        token.getExpiresAt()
                )
        );
    }

    @Override
    public boolean isRegistered(String token) {
        return registeredTokenRepository.existsById(token);
    }

    @Override
    @Transactional
    public void revoke(String clientId, String token) {
        registeredTokenRepository.findFirstByLoginAndToken(clientId, token)
                .ifPresent(registeredTokenRepository::delete);
    }
}