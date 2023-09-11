DROP SCHEMA IF EXISTS fahrzeuge;
CREATE SCHEMA fahrzeuge DEFAULT CHARACTER SET utf8;
USE fahrzeuge;

CREATE TABLE  fahrzeug (
  kennzeichen VARCHAR(12),
  typ VARCHAR(20) NOT NULL,
  farbe VARCHAR(10) NOT NULL,
  baujahr INT(11) NOT NULL,
  PRIMARY KEY (kennzeichen)
);

INSERT INTO fahrzeug VALUES
("HB - AZ 123", "Fiat Panda",   "grün",   2001),
("HB - AA 111", "VW Golf",      "blau",   1997),
("HH - ZZ 999", "Opel Astra",   "silber", 2003),
("HB - LX 987", "Ford Fiesta",  "silber", 2001),
("HB - OW 173", "VW Polo",      "blau",   1998),
("HB - KN 894", "Audi A3",      "silber", 1998),
("HB - TP 173", "VW Passat",    "grau",   2007),
("HB - VA 917", "Toyota Yaris", "rot",    2010);

CREATE TABLE  fahrzeughalter (
  nachname VARCHAR(20),
  vorname VARCHAR(20),
  kfz_zeichen VARCHAR(12),
  geburtstag DATE NOT NULL,
  angemeldet DATE NOT NULL,
  abgemeldet DATE DEFAULT NULL,
  PRIMARY KEY (nachname, vorname, kfz_zeichen),
  FOREIGN KEY (kfz_zeichen) REFERENCES fahrzeug (kennzeichen)
);

INSERT INTO fahrzeughalter VALUES
("Mühltal",  "Emil",    "HB - AZ 123", "1941-12-29", "2001-06-08", NULL),
("Mühltal",  "Emil",    "HB - AA 111", "1941-12-29", "1997-04-23", "2001-06-08"),
("Mahnken",  "Sabine",  "HB - AA 111", "1969-03-14", "2001-06-08", "2003-04-21"),
("Müller",   "Karsten", "HB - AA 111", "1965-09-01", "2003-04-21", NULL),
("Kaufmann", "Jörn",    "HH - ZZ 999", "1983-02-16", "2003-11-09", NULL),
("Karrmann", "Katja",   "HB - LX 987", "1977-08-28", "2001-05-23", NULL),
("Lamm",     "Andrea",  "HB - OW 173", "1932-01-15", "1998-07-03", "2000-11-06"),
("Barth",    "Achim",   "HB - OW 173", "1966-10-20", "2000-11-06", "2002-10-30"),
("Schaum",   "Marion",  "HB - OW 173", "1980-03-19", "2002-10-30", NULL),
("Meyer",    "Hartmut", "HB - KN 894", "1967-01-09", "2000-12-17", "2012-01-31"),
("Meyer",    "Hartmut", "HB - TP 173", "1967-01-09", "2007-12-20", NULL),
("Meyer",    "Hartmut", "HB - VA 917", "1967-01-09", "2012-01-31", NULL);
