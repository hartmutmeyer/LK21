DROP SCHEMA IF EXISTS onlinebanking;

# Übung 1, Aufgabe 1

CREATE SCHEMA onlinebanking DEFAULT CHARACTER SET utf8;
USE onlinebanking;
CREATE TABLE konto
(
    konto_id INT AUTO_INCREMENT,
    kontonummer INT NOT NULL,
    passwort VARCHAR(20) NOT NULL,
	name VARCHAR(20) NOT NULL,
	vorname VARCHAR(20) NOT NULL,
	anrede ENUM('Herr','Frau') DEFAULT 'Frau',
	kontostand REAL(8,2) NOT NULL  DEFAULT 0.0,
	PRIMARY KEY (konto_id)
);

INSERT INTO konto VALUES
(NULL, 12345678, '1234', 'Mustermann', 'Monika', 'Frau', -703.28);

SELECT * FROM konto;