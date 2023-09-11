DROP SCHEMA IF EXISTS buchladen;
CREATE SCHEMA buchladen DEFAULT CHARACTER SET utf8;
USE buchladen;

CREATE TABLE  buch (
  isbn VARCHAR(20),
  titel VARCHAR(50) NOT NULL,
  autor VARCHAR(30) NOT NULL,
  bestand INT NOT NULL,
  PRIMARY KEY (isbn)
);

INSERT INTO buch VALUES
("3-125-3456", "Java Programmierung - leicht gemacht",           "Günter Meier",           10),
("2-657-9821", "Abitur - schaff ich es oder schafft es mich?",   "Mariella Drachensberg", 500),
("5-991-4348", "Kleines Tauch-ABC",                              "Tobias Freitag",          3),
("6-114-5412", "Taschenrechner-Bedienung fuer Fortgeschrittene", "Daniel Superschlau",      1),
("9-547-1295", "Wo finde ich den Frosch meiner Träume?",         "Sabine Kramer",           5),
("2-723-2345", "Abnehmen ohne Hungern",                          "Prof. Anabella Sorbei",  20),
("4-345-6578", "Angelköder selber finden",                       "Thomas Neumann",         12),
("6-234-6512", "Selbstfindung durch Meditation",                 "Jörn Mascher",            7);

CREATE TABLE kunde (
  kundennr INT,
  nachname VARCHAR(20) NOT NULL,
  vorname VARCHAR(20) NOT NULL,
  PRIMARY KEY (kundennr)
);

INSERT INTO kunde VALUES
(256, "Meyer",  "Hartmut"),
(512, "Lüthje", "Vera"),
(777, "Lihnig", "Mathias");

CREATE TABLE  bestellung (
  bestellnr INT AUTO_INCREMENT,
  kundennr INT,
  isbn VARCHAR(20),
  anzahl INT NOT NULL,
  PRIMARY KEY (bestellnr),
  FOREIGN KEY (kundennr) REFERENCES kunde (kundennr),
  FOREIGN KEY (isbn) REFERENCES buch (isbn)
);

INSERT INTO bestellung VALUES
(NULL, 256, "5-991-4348", 1),
(NULL, 512, "3-125-3456", 1);
