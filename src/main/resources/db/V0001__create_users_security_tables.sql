CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 50;

CREATE TABLE users(
                      id BIGSERIAL PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities(
                            user_id BIGINT NOT NULL REFERENCES users(id),
                            authority VARCHAR(255) NOT NULL,
                            UNIQUE(user_id, authority)
);

CREATE TABLE tokens(
                       token VARCHAR(4096) PRIMARY KEY,
                       login varchar(255) NOT NULL,
                       issued_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       expires_at TIMESTAMP NOT NULL
);