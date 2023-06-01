DROP SCHEMA IF EXISTS urlaubsdatenbank;
CREATE SCHEMA urlaubsdatenbank DEFAULT CHARACTER SET utf8;
USE urlaubsdatenbank;

CREATE TABLE mitarbeiter (
  mitarbeiter_id INT AUTO_INCREMENT,
  vorname VARCHAR(20) NOT NULL,
  nachname VARCHAR(20) NOT NULL,
  abteilung VARCHAR(20) NOT NULL,
  PRIMARY KEY (mitarbeiter_id)
);

INSERT INTO mitarbeiter (vorname, nachname, abteilung) VALUES
('Maja',    'Winter',    'Buchhaltung'),
('Kai',     'Schneider', 'Controlling'),
('Saskia',  'Kober',     'Einkauf'),
('Manfred', 'Petersen',  'Verkauf'),
('Joachim', 'Uffelmann', 'Controlling'),
('Helga',   'Schneider', 'Einkauf'),
('Kai',     'Sieblich',  'Einkauf');

CREATE TABLE reisewunsch (
  reise_id INT AUTO_INCREMENT,
  land VARCHAR(20) NOT NULL,
  beschreibung VARCHAR(100) NOT NULL,
  reisewunsch_mitarbeiter_id INT NOT NULL,
  PRIMARY KEY (reise_id, reisewunsch_mitarbeiter_id),
  FOREIGN KEY (reisewunsch_mitarbeiter_id) REFERENCES mitarbeiter (mitarbeiter_id)
);

INSERT INTO reisewunsch VALUES
(1, 'Türkei',   'Badeurlaub',            2),
(1, 'Türkei',   'Badeurlaub',            4),
(1, 'Türkei',   'Badeurlaub',            5),
(1, 'Türkei',   'Badeurlaub',            6),
(2, 'Türkei',   'Städtereise: Istanbul', 2),
(2, 'Türkei',   'Städtereise: Istanbul', 3),
(2, 'Türkei',   'Städtereise: Istanbul', 6),
(2, 'Türkei',   'Städtereise: Istanbul', 7),
(3, 'Kroatien', 'Badeurlaub',            1),
(3, 'Kroatien', 'Badeurlaub',            4),
(3, 'Kroatien', 'Badeurlaub',            6),
(4, 'Spanien',  'Wandern in Andalusien', 4),
(4, 'Spanien',  'Wandern in Andalusien', 6);

CREATE TABLE  urlaubszeit (
  von DATE NOT NULL,
  bis DATE NOT NULL,
  urlaubszeit_mitarbeiter_id INT NOT NULL,
  PRIMARY KEY (von, bis, urlaubszeit_mitarbeiter_id),
  FOREIGN KEY (urlaubszeit_mitarbeiter_id) REFERENCES mitarbeiter (mitarbeiter_id)
);

INSERT INTO urlaubszeit VALUES
('2007-06-01', '2007-08-30', 1),
('2007-06-01', '2007-08-15', 2),
('2007-06-01', '2007-06-20', 4),
('2007-07-15', '2007-08-30', 4),
('2007-07-01', '2007-08-30', 5),
('2007-06-01', '2007-06-30', 6),
('2007-07-15', '2007-08-30', 6),
('2007-07-01', '2007-08-30', 7);
