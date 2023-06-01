DROP SCHEMA IF EXISTS firma;
CREATE SCHEMA firma DEFAULT CHARACTER SET utf8;
USE firma;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE angestellter (
    angestellter_id INT,
    vorname VARCHAR(20) NOT NULL,
    initiale VARCHAR(1),
    nachname VARCHAR(20) NOT NULL,
    geburtstag DATE,
    adresse VARCHAR(30),
    geschlecht ENUM('m', 'w'),
    gehalt DOUBLE(7,2) UNSIGNED,
    vorgesetzter_id INT,
    angestellter_abteilung_id INT,
    PRIMARY KEY (angestellter_id),
    FOREIGN KEY (vorgesetzter_id) REFERENCES angestellter (angestellter_id),
    FOREIGN KEY (angestellter_abteilung_id) REFERENCES abteilung (abteilung_id)
);

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE abteilung (
    abteilung_id INT,
    abteilungsname VARCHAR(20) NOT NULL,
    abteilungsleiter_id INT,
    anfang_abteilungsleiter DATE,
    PRIMARY KEY (abteilung_id),
    FOREIGN KEY (abteilungsleiter_id) REFERENCES angestellter (angestellter_id)
);

CREATE TABLE ort (
	ort_id INT,
	ortsname VARCHAR(20) NOT NULL,
	straße VARCHAR(20),
	PRIMARY KEY (ort_id)
);

CREATE TABLE abteilungsort (
    abteilungsort_abteilung_id INT,
    abteilungsort_ort_id INT,
    PRIMARY KEY (abteilungsort_abteilung_id , abteilungsort_ort_id),
    FOREIGN KEY (abteilungsort_abteilung_id) REFERENCES abteilung (abteilung_id),
    FOREIGN KEY (abteilungsort_ort_id) REFERENCES ort (ort_id)
);

CREATE TABLE projekt (
    projekt_id INT,
    projektname VARCHAR(20),
    projekt_ort_id INT,
    projekt_abteilung_id INT,
    PRIMARY KEY (projekt_id),
    FOREIGN KEY (projekt_ort_id) REFERENCES ort (ort_id),
    FOREIGN KEY (projekt_abteilung_id) REFERENCES abteilung (abteilung_id)
);

CREATE TABLE arbeitet_an (
    arbeitet_an_angestellter_id INT,
    arbeitet_an_projekt_id INT,
    stunden DOUBLE(3,1),
    PRIMARY KEY (arbeitet_an_angestellter_id , arbeitet_an_projekt_id),
    FOREIGN KEY (arbeitet_an_angestellter_id) REFERENCES angestellter (angestellter_id),
    FOREIGN KEY (arbeitet_an_projekt_id) REFERENCES projekt (projekt_id)
);

CREATE TABLE angehöriger (
    angehöriger_angestellter_id INT,
    vorname VARCHAR(20) NOT NULL,
    initiale VARCHAR(1),
    nachname VARCHAR(20) NOT NULL,
    geschlecht ENUM('m', 'w'),
    geburtstag DATE,
    verwandtschaft ENUM('Tochter', 'Sohn', 'Ehegatte'),
    FOREIGN KEY (angehöriger_angestellter_id) REFERENCES angestellter (angestellter_id)
);

SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO abteilung VALUES
#id, name,      abteilungsleiter, abteilungsleiter seit
(5, 'Research',       333445555, '1988-05-22'),
(4, 'Administration', 987654321, '1995-01-01'),
(1, 'Headquarters',   888665555, '1981-06-19');

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO ort VALUES
#id, ortsname,   adresse
(1, 'Houston',   '12 Jefferson Lane'),
(2, 'Stafford',  '254 Green Court'),
(3, 'Bellaire',  '202 Main Street'),
(4, 'Sugarland', '1 Rotten Teeth Road');

INSERT INTO abteilungsort VALUES
#abteilung, ort
(1, 1),
(4, 2),
(5, 3),
(5, 4),
(5, 1);

INSERT INTO angestellter VALUES
#id,       vorname, initiale, nachname,  geburtstag,   adresse,                   m/w, gehalt, vorgesetzter, abteilung
(888665555, 'James',    'E', 'Borg',    '1937-11-10', '450 Stone, Houston, TX',   'm', 55000, NULL,      1),
(333445555, 'Franklin', 'T', 'Wong',    '1955-12-08', '638 Voss, Houston, TX',    'm', 40000, 888665555, 5),
(987654321, 'Jennifer', 'S', 'Wallace', '1941-06-20', '291 Berry, Bellaire, TX',  'w', 43000, 888665555, 4),
(123456789, 'John',     'B', 'Smith',   '1965-01-09', '731 Fondren, Houston, TX', 'm', 30000, 333445555, 5),
(999887777, 'Alicia',   'J', 'Zelaya',  '1968-07-19', '3321 Castle, Spring, TX',  'w', 25000, 987654321, 4),
(666884444, 'Ramesh',   'K', 'Narayan', '1962-09-15', '975 Fire Oak, Humble, TX', 'm', 38000, 333445555, 5),
(453453453, 'Joyce',    'A', 'English', '1972-07-31', '5631 Rice, Houston, TX',   'w', 25000, 333445555, 5),
(987987987, 'Ahmad',    'V', 'Jabbar',  '1969-03-29', '980 Dallas, Houston, TX',  'm', 25000, 987654321, 4);

INSERT INTO projekt VALUES
#id, projektname,     ort, abteilung
( 1, 'ProductX',        3, 5),
( 2, 'ProductY',        4, 5),
( 3, 'ProductZ',        1, 5),
(10, 'Computerization', 2, 4),
(20, 'Reorganization',  1, 1),
(30, 'Newbenefits',     2, 4);

INSERT INTO arbeitet_an VALUES
#angestellter, projekt, stunden
(123456789,  1, 32.5),
(123456789,  2,  7.5),
(666884444,  3, 40.0),
(453453453,  1, 20.0),
(453453453,  2, 20.0),
(333445555,  2, 10.0),
(333445555,  3, 10.0),
(333445555, 10, 10.0),
(333445555, 20, 10.0),
(999887777, 30, 30.0),
(999887777, 10, 10.0),
(987987987, 10, 35.0),
(987987987, 30,  5.0),
(987654321, 30, 20.0),
(987654321, 20, 15.0),
(888665555, 20, NULL);

INSERT INTO angehöriger VALUES
#angestellter, vorname, initiale, nachname,  m/w,  geburtstag,   beziehung
(333445555, 'Alice',     'J',     'Wong',    'w', '1986-04-05', 'Tochter'),
(333445555, 'Theodore',  'F',     'Wong',    'm', '1983-10-25', 'Sohn'),
(333445555, 'Joy',       'P',     'Wong',    'w', '1958-05-03', 'Ehegatte'),
(987654321, 'Abner',     'M',     'Wallace', 'm', '1942-02-28', 'Ehegatte'),
(123456789, 'Michael',   'J',     'Smith',   'm', '1988-01-04', 'Sohn'),
(123456789, 'Alice',     'E',     'Goldman', 'w', '1988-12-30', 'Tochter'),
(123456789, 'Elizabeth', 'U',     'Smith',   'w', '1967-05-05', 'Ehegatte');
