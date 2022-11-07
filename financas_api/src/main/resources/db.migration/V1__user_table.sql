CREATE TABLE "user"
(
id          UUID        NOT NULL PRIMARY KEY,
name        VARCHAR(50) NOT NULL,
login       varchar(50) NOT NULL,
password    VARCHAR(100) NOT NULL,
email       VARCHAR(50)
);