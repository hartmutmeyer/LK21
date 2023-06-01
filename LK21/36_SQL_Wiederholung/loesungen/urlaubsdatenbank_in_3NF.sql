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

CREATE TABLE reise (
  reise_id INT AUTO_INCREMENT,
  land VARCHAR(20) NOT NULL,
  beschreibung VARCHAR(100) NOT NULL,
  PRIMARY KEY (reise_id)
);

INSERT INTO reise (land, beschreibung) VALUES
('Türkei',   'Badeurlaub'),
('Türkei',   'Städtereise: Istanbul'),
('Kroatien', 'Badeurlaub'),
('Spanien',  'Wandern in Andalusien');

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

CREATE TABLE  wunsch (
  wunsch_mitarbeiter_id INT NOT NULL,
  wunsch_reise_id INT NOT NULL,
  PRIMARY KEY (wunsch_mitarbeiter_id, wunsch_reise_id),
  FOREIGN KEY (wunsch_mitarbeiter_id) REFERENCES mitarbeiter (mitarbeiter_id),
  FOREIGN KEY (wunsch_reise_id) REFERENCES reise (reise_id)
);

INSERT INTO wunsch VALUES 
(1, 3),
(2, 1),
(2, 2),
(3, 2),
(4, 1),
(4, 3),
(5, 4),
(5, 1),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(7, 2);

# d1 
# Ermittle, wie viele Mitarbeiter in der Datenbank eingetragen sind.


# d2 
# Erstelle eine Liste, die angibt welche Mitarbeiter (Vorname, Nachname) sich 
# für welches Reiseziel (Land) interessieren. Sortiere die Liste aufsteigend nach dem Land.


# d3 
# Zähle die Anzahl der Mitarbeiter, die sich für die einzelnen Reisen interessieren. 
# Das Ergebnis soll eine Tabelle sein mit der Reise (Land und Beschreibung) und einer Spalte,
# die die Anzahl der Interessenten angibt.


# d4 
# Ermittle diejenigen Mitarbeiter mit Vorname und Nachname, die noch keine Urlaubszeiten 
# angegeben haben.


# d5 
# Ermittle alle Mitarbeiter (Vorname und Nachname), die mit Helga Schneider in der selben 
# Abteilung arbeiten. Helga Schneider darf nicht in der Liste erscheinen.


# d6 
# Liste die Namen (Vorname, Nachname) aller Mitarbeiter auf, die gerne einen Badeurlaub machen 
# würden ("Badeurlaub" steht in der Spalte Beschreibung der Tabelle Reiseziel). In der Liste darf 
# kein Name doppelt aufgeführt werden.


# d7
# Zähle die Anzahl der Mitarbeiter, die gerne nach Spanien fahren würden. Das Ergebnis ist eine 
# einzige Zahl.


# d8
# Liste alle Mitarbeiter mit Vor- und Nachname auf, die sich für mehr als zwei Reiseziele 
# interessieren.


# d9 
# Ermittle alle Mitarbeiter, die mit Kai Schneider ein Reiseziel gemeinsam haben, mit Vornamen und
# Nachnamen. Kai Schneider darf auch in der Liste erscheinen. Es dürfen jedoch keine Namen doppelt
# aufgelistet werden.
