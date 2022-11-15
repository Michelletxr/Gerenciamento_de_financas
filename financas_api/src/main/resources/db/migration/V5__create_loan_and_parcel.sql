CREATE TABLE "loan"
(
    id               UUID NOT NULL PRIMARY KEY,
    valor_pagar      DOUBLE PRECISION,
    data_final       DATE,
    tipo_juros       VARCHAR(50),
    juros            DOUBLE PRECISION,
    value            DOUBLE PRECISION  NOT NULL,
    description      VARCHAR(50),
    data_recebimento DATE NOT NULL ,
    conta_id         UUID NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES "conta" (id)

);

CREATE TABLE "parcel"
(
    id                     UUID        NOT NULL PRIMARY KEY,
    value                  DOUBLE PRECISION  NOT NULL,
    description            VARCHAR(50),
    status                 VARCHAR(50),
    data_vencimento        DATE NOT NULL,
    data_pagamento         DATE NOT NULL,
    conta_id               UUID         NOT NULL,
    emprestimo_id          UUID         NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES "conta" (id),
    FOREIGN KEY (emprestimo_id) REFERENCES "loan" (id)
);