package iiot.istok.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredToken {
    private static final int DEFAULT_MAX_TOKEN_SIZE = 4096;

    @Id
    @Column(nullable = false, unique = true, length = DEFAULT_MAX_TOKEN_SIZE)
    private String token;

    @Column(nullable = false)
    private String login;

    private Instant issuedAt = Instant.now();

    private Instant expiresAt = Instant.now();

}