DROP SCHEMA IF EXISTS exklusiv_reisen;
CREATE SCHEMA exklusiv_reisen DEFAULT CHARACTER SET utf8;
USE exklusiv_reisen;

CREATE TABLE reise (
  reise_id INT AUTO_INCREMENT,
  land VARCHAR(30) NOT NULL,
  von DATE,
  bis DATE,
  ausgebucht ENUM('ja','nein') NOT NULL DEFAULT 'nein',
  PRIMARY KEY (reise_id)
);

INSERT INTO reise VALUES
(1,  'Italien',      '2015-06-12', '2015-06-28', 'nein'),
(2,  'Marokko',      '2015-08-02', '2015-08-16', 'nein'),
(3,  'Griechenland', '2015-06-25', '2015-07-02', 'nein'),
(4,  'Griechenland', '2015-07-13', '2015-07-20', 'ja'),
(5,  'Griechenland', '2015-08-20', '2015-08-27', 'nein'),
(6,  'Spanien',      '2015-07-03', '2015-07-17', 'ja'),
(7,  'Thailand',     '2015-09-01', '2015-09-15', 'nein'),
(8,  'Türkei',       '2015-06-24', '2015-07-01', 'nein'),
(9,  'Türkei',       '2015-09-02', '2015-09-16', 'nein'),
(10, 'Frankreich',   '2015-07-04', '2015-07-22', 'nein');

CREATE TABLE buchung (
  buchung_id INT AUTO_INCREMENT,
  vorname VARCHAR(20) NOT NULL,
  nachname VARCHAR(20) NOT NULL,
  anzahl INT NOT NULL,
  buchung_reise_id INT NOT NULL,
  PRIMARY KEY (buchung_id),
  FOREIGN KEY (buchung_reise_id) REFERENCES reise (reise_id)
);

INSERT INTO buchung VALUES
(1,  'Karl',    'Sorglos',  2,  1),
(2,  'Anne',    'Marx',     4,  4),
(3,  'Joe',     'Lennings', 3,  6),
(4,  'Joe',     'Lennings', 1,  2),
(5,  'Marie',   'Buchmann', 4,  6),
(6,  'Marie',   'Buchmann', 2,  9),
(7,  'Lara',    'Simons',   6,  4),
(8,  'Lara',    'Simons',   2,  1),
(9,  'Lara',    'Simons',   2,  9),
(10, 'Mark',    'Meier',    5,  6),
(11, 'Ron',     'Duck',     2,  4),
(12, 'Ron',     'Duck',     1,  8),
(13, 'Louise',  'Bart',     6, 10),
(14, 'Dana',    'Novak',    3, 10),
(15, 'Andreas', 'Simons',   3,  1),
(16, 'Lara',    'Schmidt',  4,  7);
