CREATE TABLE "conta"
(
    id          UUID        NOT NULL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    value       DOUBLE PRECISION NOT NULL,
    user_id               UUID         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);