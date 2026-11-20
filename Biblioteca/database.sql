CREATE DATABASE IF NOT EXISTS `bibliotecapoo_db`;
USE `bibliotecapoo_db`;

CREATE TABLE IF NOT EXISTS `livros` (
                                        `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `autor` VARCHAR(255) NOT NULL,
    `codigo_isbn` VARCHAR(255) NOT NULL,
    `preco` DECIMAL(10,2) NOT NULL,
    `ano_publicacao` DATE NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `livros` (`titulo`, `autor`, `codigo_isbn`, `preco`, `ano_publicacao`) VALUES
                                                                                       ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', '9788532530783', 44.90, '1997-06-26'),
                                                                                       ('Harry Potter e a Câmara Secreta', 'J.K. Rowling', '9788532530790', 49.90, '1998-07-02'),
                                                                                       ('Harry Potter e o Prisioneiro de Azkaban', 'J.K. Rowling', '9788532530806', 54.90, '1999-07-08'),
                                                                                       ('Harry Potter e o Cálice de Fogo', 'J.K. Rowling', '9788532530813', 69.90, '2000-07-08'),
                                                                                       ('Harry Potter e a Ordem da Fênix', 'J.K. Rowling', '9788532530820', 79.90, '2003-06-21'),
                                                                                       ('Harry Potter e o Enigma do Príncipe', 'J.K. Rowling', '9788532530837', 64.90, '2005-07-16'),
                                                                                       ('Harry Potter e as Relíquias da Morte', 'J.K. Rowling', '9788532530844', 74.90, '2007-07-21');
