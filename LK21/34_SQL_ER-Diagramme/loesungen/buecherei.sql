DROP SCHEMA IF EXISTS buecherei;
CREATE SCHEMA buecherei DEFAULT CHARACTER SET utf8;
USE buecherei;

CREATE TABLE autor (
  autor_id INT,
  name VARCHAR(20) NOT NULL,
  vorname VARCHAR(20),
  geburtsjahr YEAR,
  todesjahr YEAR,
  land VARCHAR(20),
  PRIMARY KEY (autor_id) 
);

CREATE TABLE verlag (
  verlag_id INT,
  name VARCHAR(20) NOT NULL,
  ort VARCHAR(20) NOT NULL,
  spezialgebiet VARCHAR(45),
  PRIMARY KEY (verlag_id) 
);

CREATE TABLE buch (
  isbn INT,
  titel VARCHAR(45) NOT NULL,
  untertitel VARCHAR(45),
  sprache VARCHAR(45) NOT NULL,
  erscheinungsjahr YEAR NOT NULL,
  buch_verlag_id INT,
  PRIMARY KEY (isbn),
  FOREIGN KEY (buch_verlag_id) REFERENCES verlag (verlag_id)
);

CREATE TABLE ist_autor_von (
  autor_id INT,
  buch_isbn INT,
  PRIMARY KEY (autor_id, buch_isbn),
  FOREIGN KEY (autor_id) REFERENCES autor (autor_id),
  FOREIGN KEY (buch_isbn) REFERENCES buch (isbn)
);