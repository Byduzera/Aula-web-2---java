CREATE DATABASE BddExerc4;

-- Criar a tabela user no banco de dados ifitness:

CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;