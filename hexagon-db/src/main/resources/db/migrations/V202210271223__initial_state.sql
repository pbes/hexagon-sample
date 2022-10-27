DROP TABLE IF EXISTS place;

CREATE TABLE place
(
    id   BIGINT auto_increment NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT place_PK PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;