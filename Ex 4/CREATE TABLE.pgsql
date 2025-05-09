CREATE TABLE usuario (
    id serial PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE produto (
    id serial PRIMARY KEY, 
    nome VARCHAR(50), 
    quantidade INTEGER
);

CREATE TABLE perfil_psicologico (
    id serial PRIMARY KEY,
    texto_enviado TEXT NOT NULL,
    avaliacao VARCHAR(100),
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INTEGER,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);