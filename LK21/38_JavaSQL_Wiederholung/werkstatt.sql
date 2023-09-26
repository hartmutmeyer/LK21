DROP SCHEMA IF EXISTS werkstatt;
CREATE SCHEMA werkstatt DEFAULT CHARACTER SET utf8;
USE werkstatt;

CREATE TABLE kunde (
  kundennr INT,
  vorname VARCHAR(20) NOT NULL,
  nachname VARCHAR(20) NOT NULL,
  telefonnr VARCHAR(20) NOT NULL,
  adresse VARCHAR(30) NOT NULL,
  PRIMARY KEY (kundennr)
);

INSERT INTO kunde VALUES
(111, 'Maria', 'Sonnenberg', '223222', 'Muschelgasse 17, 28203 Bremen'),
(222, 'Jörn',  'Mustermann', '112233', 'Krümelgasse 56, 28222 Bremen'),
(333, 'Emil',  'Markward',   '234567', 'Amselweg 6, 10112 Berlin');

CREATE TABLE  mechaniker (
  mechaniker_nr INT,
  vorname VARCHAR(20) NOT NULL,
  nachname VARCHAR(20) NOT NULL,
  PRIMARY KEY (mechaniker_nr)
);

INSERT INTO mechaniker VALUES
(1, 'Karl',   'Sonntag'),
(2, 'Sabine', 'Meier'),
(3, 'Egbert', 'Albrecht');

CREATE TABLE  fahrzeug (
  fahrzeug_nr INT,
  typ VARCHAR(20) NOT NULL,
  kundennr INT,
  fertig_bis_uhrzeit INT NOT NULL,
  PRIMARY KEY (fahrzeug_nr),
  FOREIGN KEY (kundennr) REFERENCES kunde (kundennr)
);

INSERT INTO fahrzeug VALUES
(1, 'Mittelklasse Pkw', 222, 17),
(2, 'Motorrad XYZ',     222, 16),
(3, 'Lieferwagen',      111, 14),
(4, 'Mini-Van',         111, 14),
(5, 'Kleinwagen',       333, 18);

CREATE TABLE  auftrag (
  fahrzeug_nr INT,
  tätigkeit VARCHAR(20) NOT NULL,
  PRIMARY KEY  (tätigkeit, fahrzeug_nr),
  FOREIGN KEY (fahrzeug_nr) REFERENCES fahrzeug (fahrzeug_nr)
);

INSERT INTO auftrag VALUES
(1, 'Ölwechsel'),
(2, 'Reifen wechseln'),
(4, 'Bremsen erneuern'),
(5, 'Reifen wechseln'),
(2, 'Sicherheits-Check'),
(3, 'Sicherheits-Check'),
(4, 'Sicherheits-Check'),
(4, 'Ölwechsel');

CREATE TABLE  bearbeitet (
  fahrzeug_nr INT,
  mechaniker_nr INT,
  PRIMARY KEY  (mechaniker_nr, fahrzeug_nr),
  FOREIGN KEY (mechaniker_nr) REFERENCES mechaniker (mechaniker_nr),
  FOREIGN KEY (fahrzeug_nr) REFERENCES fahrzeug (fahrzeug_nr)
);

INSERT INTO bearbeitet VALUES
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(1, 3),
(2, 3);

