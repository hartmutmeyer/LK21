DROP SCHEMA IF EXISTS schule;
CREATE SCHEMA schule DEFAULT CHARACTER SET utf8;
USE schule;

CREATE TABLE lehrer (
  lehrer_id INT AUTO_INCREMENT,
  nachname VARCHAR(20) NOT NULL,
  vorname VARCHAR(20) NOT NULL,
  geschlecht ENUM('m','w') NOT NULL,
  PRIMARY KEY (lehrer_id)
);

INSERT INTO lehrer VALUES
(1, "Bachmann", "Pia",      "w"),
(2, "Streng",   "Manfred",  "m"),
(3, "Dusel",    "Donald",   "m"),
(4, "Sonntag",  "Susi",     "w"),
(5, "Albrecht", "Kai",      "m"),
(6, "Schuster", "Ingeborg", "w");

CREATE TABLE kurs (
  kurs_id INT AUTO_INCREMENT,
  fach VARCHAR(20) NOT NULL,
  kurs_lehrer_id INT NOT NULL,
  PRIMARY KEY (kurs_id),
  FOREIGN KEY (kurs_lehrer_id) REFERENCES lehrer (lehrer_id)
);

CREATE TABLE  schueler (
  schueler_id INT AUTO_INCREMENT,
  nachname VARCHAR(20) NOT NULL,
  vorname VARCHAR(20) NOT NULL,
  geschlecht ENUM('m','w') NOT NULL,
  geburtstag DATE NOT NULL,
  PRIMARY KEY (schueler_id)
);

INSERT INTO schueler VALUES
(1, "Weber",       "Daniel",    "m", "1987-12-23"),
(2, "Glückstein",  "Andrea",    "w", "1988-01-01"),
(3, "Steiner",     "Isabell",   "w", "1986-04-25"),
(4, "Plötzlich",   "Michael",   "m", "1988-09-03"),
(5, "Zeiger",      "Arne",      "m", "1987-06-13"),
(6, "Mandelbrot",  "Miriam",    "w", "1987-11-28"),
(7, "Übermut",     "Stephanie", "m", "1988-10-05");

INSERT INTO kurs VALUES
( 1, "Mathematik", 1),
( 2, "Mathematik", 3),
( 3, "Biologie",   1),
( 4, "Englisch",   2),
( 5, "Sport",      6),
( 6, "Spanisch",   2),
( 7, "Deutsch",    4),
( 8, "Wirtschaft", 5),
( 9, "Geographie", 5),
(10, "Physik",     3),
(11, "Geschichte", 6),
(12, "Englisch",   2),
(13, "Deutsch",    6);

CREATE TABLE  note (
  note_kurs_id INT,
  note_schueler_id INT,
  note INT NOT NULL,
  PRIMARY KEY  (note_schueler_id, note_kurs_id),
  FOREIGN KEY (note_kurs_id) REFERENCES kurs (kurs_id),
  FOREIGN KEY (note_schueler_id) REFERENCES schueler (schueler_id)
);

INSERT INTO note VALUES
( 1, 1,  8),
( 1, 6,  4),
( 2, 3,  6),
( 2, 5, 14),
( 4, 3, 10),
( 4, 5,  7),
( 4, 6,  6),
( 5, 1,  6),
( 5, 2,  8),
( 5, 3, 10),
( 5, 5, 10),
( 5, 6, 12),
( 6, 1,  7),
( 6, 2,  1),
( 6, 3,  7),
( 8, 2,  9),
( 8, 3,  9),
( 8, 6,  3),
( 9, 1,  9),
( 9, 5,  7),
(11, 1, 11),
(11, 2,  4),
(11, 3,  8),
(11, 5,  9),
(12, 1, 13),
(12, 2,  3),
(12, 3, 12),
(12, 5,  6),
(12, 6,  7);
