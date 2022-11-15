CREATE TABLE "investiment"
(
    id          UUID        NOT NULL PRIMARY KEY,
    corretora        VARCHAR(50) NOT NULL,
    titulo       DOUBLE PRECISION,
    vencimento_titulo         DATE ,
    data_investimento         DATE,
    investido DOUBLE PRECISION,
    objetivo  VARCHAR(50),
    tipo_titulo VARCHAR(50)
);