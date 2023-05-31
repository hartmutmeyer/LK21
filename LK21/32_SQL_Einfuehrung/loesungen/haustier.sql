DROP SCHEMA IF EXISTS haustier;

# Übung 1, Aufgabe 1

CREATE SCHEMA haustier DEFAULT CHARACTER SET utf8;
USE haustier;
CREATE TABLE tier
(
	name VARCHAR(20) NOT NULL,
	tierart VARCHAR(20) NOT NULL,
	lebendig ENUM('ja','nein') DEFAULT 'ja',
	geschlecht ENUM('weiblich','männlich') DEFAULT 'weiblich',
	geburtstag DATE,
	todestag DATE
);
#DESCRIBE tier;

# Übung 1, Aufgabe 2
# 2A
INSERT INTO tier VALUES
('Bello', 'Hund',          'ja',   'männlich', '2003-05-01', NULL),
('Daisy', 'Kanarienvogel', 'nein', 'weiblich', '1996-12-06', '2004-08-17'),
('Mausi', 'Katze',         'ja',   'weiblich', '2002-11-17', NULL);
# 2B
INSERT INTO tier (name, tierart) VALUES
('Daisy',  'Schildkröte'),
('Lassie', 'Hund'),
('Maja',   'Hund');
# 2C
INSERT INTO tier (name, tierart, geschlecht) VALUES
('Hasso',  'Hund',    'männlich'),
('Blacky', 'Katze',   'männlich'),
('Harald', 'Hamster', 'männlich');
# 2D
UPDATE tier
SET geburtstag = '2003-12-06'
WHERE tierart = 'Schildkröte';

UPDATE tier
SET geburtstag = '2004-04-23'
WHERE geburtstag IS NULL;

UPDATE tier
SET geburtstag = '2001-07-29', todestag = '2003-09-15', lebendig = 'nein'
WHERE name = 'Harald';

# Übung 1, Aufgabe 3
# 3A) Zeige die gesamte Tabelle tier an.
#SELECT *
#FROM tier;

# 3B) Zeige nur die Spalten name und tierart an.
#SELECT name, tierart
#FROM tier;

# 3C) Liste die Spalten name und tierart auf. Sortiere dabei in aufsteigender alphabetischer
#     Reihenfolge nach dem Namen. An zweiter Stelle soll nach der Tierart sortiert werden
#     (auch aufsteigend).
#SELECT name, tierart
#FROM tier
#ORDER BY name, tierart ASC;

# 3D) Zeige nur die Spalte geburtstag an. Sorge dafür, dass jeder Wert nur einmal angezeigt wird.
#SELECT geburtstag
#FROM tier
#GROUP BY geburtstag;

# 3E) Zeige die Spalten name und tierart für die Tiere an, die noch leben.
#SELECT name, tierart
#FROM tier
#WHERE lebendig = 'ja';

# 3F) Liste die Namen aller Tiere auf, die vor dem Jahr 2004 geboren wurden und noch nicht
#     tot sind. Sortiere die Namen in absteigender alphabetischer Reihenfolge.
#SELECT name
#FROM tier
#WHERE geburtstag < '2004-01-01'
#AND todestag IS NULL
#ORDER BY name DESC;

# 3G) Liste die gesamten Spalten aller Tiere auf, die weder Hund noch Katze sind.
#SELECT *
#FROM tier
#WHERE tierart <> 'Hund'
#AND tierart <> 'Katze';

# 3H) Zeige die Namen und die Geburtstage aller Tiere an, für die kein Todestag angegeben wurde.
#SELECT name, geburtstag
#FROM tier
#WHERE todestag IS NULL;

# 3I) Zähle wie viele Tiere nach dem 01.01.2003 geboren wurden.
#SELECT COUNT(*)
#FROM tier
#WHERE geburtstag > '2003-01-01';

# 3J) Liste auf, wie viele Tiere es von jeder Tierart gibt. Das Ergebnis soll eine Tabelle
#     mit einer Spalte tierart sein und einer Spalte, die zu jeder Tierart die Anzahl angibt.
#SELECT COUNT(*), tierart
#FROM tier
#GROUP BY tierart;

# 3K) Liste alle Tierarten auf, von denen es zwei oder mehr Tiere gibt.
#SELECT tierart
#FROM tier
#GROUP BY tierart
#HAVING COUNT(*)>1;

# 3L) Zähle die Anzahl der unterschiedlichen Tierarten, die es gibt.
#SELECT COUNT(DISTINCT tierart)
#FROM tier;

# Übung 2, Aufgabe 1

CREATE TABLE besitzer
(
	besitzer_id INT AUTO_INCREMENT,
	anrede ENUM('Herr', 'Frau', 'Firma') DEFAULT 'Herr',
	vorname VARCHAR(20),
	nachname VARCHAR(20) NOT NULL,
	straße VARCHAR(30),
	plz INT,
	ort VARCHAR(30),
	telefonnr VARCHAR(20),
	PRIMARY KEY (besitzer_id)
);

ALTER TABLE tier 
	ADD COLUMN (tier_besitzer_id INT),
	ADD FOREIGN KEY (tier_besitzer_id) REFERENCES besitzer (besitzer_id)
;

# Übung 3, Aufgabe 1

INSERT INTO besitzer VALUES
(NULL, 'Firma', NULL,    'Zoo Lilliput', 'Obernstraße 54', 20012, 'Hamburg', '0721/343412'),
(NULL, 'Frau', 'Sandra', 'Sandelmann', 'Kullerweg 12',     28205, 'Bremen', NULL),
(NULL, 'Herr', 'Mirco',  'Sandelmann', 'Unterstraße 17',   28232, 'Bremen', '0421/123456');

INSERT INTO besitzer (vorname, nachname) VALUES
('Tobias', 'Winkelmann');

INSERT INTO besitzer (anrede, vorname, nachname) VALUES
('Frau', 'Sandra', 'Anderson');

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname IS NULL
    AND nachname = 'Zoo Lilliput'
)
WHERE name = 'Bello';

UPDATE tier
SET tier_besitzer_id = ( 
    SELECT besitzer_id FROM besitzer
    WHERE vorname IS NULL
    AND nachname = 'Zoo Lilliput'
)
WHERE name = 'Lassie';

UPDATE tier 
SET tier_besitzer_id  = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Sandra'
    AND nachname = 'Sandelmann'
)
WHERE name = 'Daisy' 
AND tierart = 'Kanarienvogel';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Mirco'
    AND nachname = 'Sandelmann'
)
WHERE name = 'Mausi';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Mirco'
    AND nachname = 'Sandelmann'
)
WHERE name = 'Blacky';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Mirco'
    AND nachname = 'Sandelmann'
)
WHERE name = 'Harald';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Tobias'
    AND nachname = 'Winkelmann'
)
WHERE name = 'Daisy'
AND tierart = 'Schildkröte';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Tobias'
    AND nachname = 'Winkelmann'
)
WHERE name = 'Hasso';

UPDATE tier
SET tier_besitzer_id = (
    SELECT besitzer_id FROM besitzer
    WHERE vorname = 'Sandra'
    AND nachname = 'Anderson'
)
WHERE name = 'Maja';

# Übung 3, Aufgabe 2

UPDATE besitzer
SET straße = 'Wilhelminenweg 42', plz = 28315, ort = 'Bremen'
WHERE nachname = 'Anderson';

# Übung 3, Aufgabe 3

# 3A) Zeige ohne Verwendung der WHERE-Klausel eine Tabelle mit allen Besitzern (Nachname 
#     und Vorname) und Tieren (Name und Tierart) an. Was für eine Tabelle wird erstellt?
#SELECT besitzer.nachname, besitzer.vorname, tier.name, tier.tierart
#FROM besitzer, tier;

# 3B) Zeige eine Liste mit allen Besitzern und den zu ihnen gehörenden Tieren an. Es sollen
#     alle Spalten angezeigt werden.
#SELECT *
#FROM besitzer, tier
#WHERE besitzer_id = tier_besitzer_id;

# 3C) Zeige eine Tabelle mit allen Besitzern (Nachname und Vorname) und ihren Tieren 
#     (Name und Tierart) an. Sortiere die Liste in aufsteigender alphabetischer Reihenfolge
#     zunächst nach dem Nachnamen und dann nach dem Vornamen.
#SELECT besitzer.nachname, besitzer.vorname, tier.name, tier.tierart 
#FROM besitzer, tier 
#WHERE besitzer_id = tier_besitzer_id 
#ORDER BY besitzer.nachname, besitzer.vorname ASC;

# 3D) Zeige eine Tabelle mit allen Besitzern (Vor- und Nachname) und ihren lebendigen Tieren
#     an (Name und Tierart). Sortiere die Liste nach der Tierart.
#SELECT besitzer.nachname, besitzer.vorname, tier.name, tier.tierart 
#FROM besitzer, tier 
#WHERE besitzer_id = tier_besitzer_id AND tier.lebendig = 'ja' 
#ORDER BY tier.tierart ASC;

# 3E) Zeige alle Tiere von Mirco Sandelmann an (Name, Tierart und „lebendig“).
#SELECT name, tierart, lebendig 
#FROM tier, besitzer 
#WHERE tier_besitzer_id = besitzer_id
#AND besitzer.vorname = 'Mirco'
#AND besitzer.nachname = 'Sandelmann';

# 3F) Wähle alle Besitzer von Hunden aus. Zeige Vor- und Nachname der Besitzer sowie
#     Name, Geburtstag und Todestag des Hundes an.
#SELECT besitzer.vorname, besitzer.nachname, tier.name, tier.geburtstag, tier.todestag 
#FROM besitzer, tier 
#WHERE besitzer_id = tier_besitzer_id
#AND tier.tierart = 'Hund';

# 3G) Zeige eine Liste der Besitzer (Vor- und Nachname) und der Anzahl der Tiere an, die
#     sie besitzen. Sortiere die Liste in umgekehrter alphabetischer Reihenfolge nach
#     Nachnamen.
#SELECT besitzer.vorname, besitzer.nachname, COUNT(*) 
#FROM besitzer, tier 
#WHERE tier_besitzer_id = besitzer_id 
#GROUP BY besitzer_id 
#ORDER BY besitzer.nachname DESC;

# 3H) Zeige alle Besitzer mit Vor- und Nachnamen an, die zwei oder mehr Tiere besitzen.
#SELECT besitzer.vorname, besitzer.nachname 
#FROM besitzer, tier 
#WHERE tier_besitzer_id = besitzer_id 
#GROUP BY besitzer_id 
#HAVING COUNT(*) >= 2;

# 3I) Zähle die Anzahl der Besitzer, die einen Hund oder eine Katze besitzen.
#SELECT COUNT(DISTINCT besitzer_id) 
#FROM besitzer, tier 
#WHERE tier_besitzer_id = besitzer_id 
#AND (
#    tier.tierart = 'Hund' 
#    OR tier.tierart = 'Katze'
#);

# Übung 4, Aufgabe 1

ALTER TABLE tier 
	ADD COLUMN (tier_id INT AUTO_INCREMENT),
	DROP FOREIGN KEY tier_ibfk_1,
	DROP COLUMN tier_besitzer_id,
	ADD PRIMARY KEY (tier_id)
;

CREATE TABLE beziehung
(
	beziehung_besitzer_id INT,
	beziehung_tier_id INT,
	PRIMARY KEY (beziehung_besitzer_id, beziehung_tier_id),
	FOREIGN KEY (beziehung_besitzer_id) REFERENCES besitzer (besitzer_id) ON DELETE CASCADE,
	FOREIGN KEY (beziehung_tier_id) REFERENCES tier (tier_id) ON DELETE CASCADE
);

UPDATE besitzer
SET straße='Unterstraße 17', plz=28232, ort='Bremen', telefonnr='0421/123456'
WHERE nachname = 'Sandelmann'
AND vorname = 'Sandra';

INSERT INTO besitzer VALUES
(NULL, 'Frau', 'Anka', 'Anderson', 'Wilhelminenweg 42', 28315, 'Bremen', NULL),
(NULL, 'Herr', 'Max',  'Anderson', 'Wilhelminenweg 42', 28315, 'Bremen', NULL),
(NULL, 'Herr', 'Kai',  'Anderson', 'Unsinnstraße 65',   28245, 'Bremen', NULL);

#SELECT * FROM besitzer;
#SELECT * FROM tier;

INSERT INTO beziehung VALUES
(
    (SELECT besitzer_id FROM besitzer WHERE nachname = 'Zoo Lilliput'),
    (SELECT tier_id FROM tier WHERE name = 'Bello')
),
(
    (SELECT besitzer_id FROM besitzer WHERE nachname = 'Zoo Lilliput'),
    (SELECT tier_id FROM tier WHERE name = 'Lassie')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Daisy' AND tierart = 'Kanarienvogel')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Mausi')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Blacky')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Mirco' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Mausi')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Mirco' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Blacky')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Mirco' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Harald')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Tobias' AND nachname = 'Winkelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Daisy' AND tierart = 'Schildkröte')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Tobias' AND nachname = 'Winkelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Hasso')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Anka' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Max' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Kai' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
);

# Übung 4, Aufgabe 2

# 2A) Zeige eine Liste aller Besitzer (Vor- und Nachname) und ihrer Tiere (Name und Tierart)
#     an. Sortiere die Liste absteigend nach dem Namen der Tiere.
#SELECT besitzer.vorname, besitzer.nachname, tier.name, tier.tierart 
#FROM besitzer, tier, beziehung 
#WHERE besitzer_id = beziehung_besitzer_id
#AND tier_id = beziehung_tier_id 
#ORDER BY tier.name DESC;

# 2B) Liste alle Besitzer von Maja auf (Vor- und Nachname).
#SELECT besitzer.vorname, besitzer.nachname
#FROM besitzer, tier, beziehung 
#WHERE besitzer_id = beziehung_besitzer_id
#AND tier_id = beziehung_tier_id
#AND tier.name = 'Maja';

# 2C) Zähle die Anzahl der Besitzer von Blacky.
#SELECT COUNT(*)
#FROM besitzer, tier, beziehung 
#WHERE besitzer_id = beziehung_besitzer_id
#AND tier_id = beziehung_tier_id
#AND tier.name = 'Blacky';

# 2D) Erstelle eine Liste, die angibt, wie viele Tiere jede einzelne Person besitzt
#     (Ausgabe: Anzahl, Vor- und Nachname).
#SELECT COUNT(*), besitzer.vorname, besitzer.nachname
#FROM besitzer, tier, beziehung 
#WHERE besitzer_id = beziehung_besitzer_id
#AND tier_id = beziehung_tier_id
#GROUP BY besitzer_id;

# 2E) Liste die vollständigen Daten aller Besitzer auf, deren Telefonnummer nicht bekannt ist.
#SELECT * 
#FROM besitzer
#WHERE telefonnr IS NULL;

# 2F) Liste Straße, PLZ und Ort aller Besitzer auf. Dabei soll jede Adresse nur einmal
#     ausgegeben werden.
#SELECT straße, plz, ort
#FROM besitzer
#GROUP BY straße;

# oder alternativ:

#SELECT DISTINCT straße, plz, ort
#FROM besitzer;

# Übung 4, Aufgabe 3

# 3A
DELETE FROM beziehung
WHERE beziehung_tier_id = (
    SELECT tier_id
    FROM tier
    WHERE name = 'Mausi'
);

# 3B
INSERT INTO besitzer VALUES
(NULL, 'Frau', 'Johanna', 'Sonntag', 'Glücksweg 13', '28333', 'Bremen', NULL);
#SELECT * FROM besitzer;

# Übung 4, Aufgabe 4

# 4A) Liste die Personen, die momentan kein Tier besitzen, mit Ihrem Vor- und Nachnamen auf.
SELECT besitzer.vorname, besitzer.nachname
FROM besitzer LEFT JOIN beziehung
ON beziehung_besitzer_id = besitzer_id
WHERE beziehung_tier_id IS NULL;

# 4B) Liste die Tiere mit Namen auf, die keinen Besitzer haben.
SELECT tier.name
FROM tier LEFT JOIN beziehung
ON beziehung_tier_id = tier_id
WHERE beziehung_besitzer_id IS NULL;

# 4C) Liste alle Personen, die mit Anka Anderson zusammen wohnen (d.h. alle, die dieselbe
#     Strasse haben) mit ihren vollständigen Daten auf. Anka selbst darf auch in der
#     Liste erscheinen.
SELECT mitwohner.*
FROM besitzer anka, besitzer mitwohner
WHERE anka.vorname = 'Anka'
AND anka.nachname = 'Anderson'
AND anka.straße = mitwohner.straße;

# 4D) Liste alle Tiere mit Namen auf, die am selben Tag geboren wurden wie Maja. Maja
#     selbst soll nicht in der Liste erscheinen.
SELECT anderestier.name, anderestier.geburtstag
FROM tier maja, tier anderestier
WHERE maja.name = 'Maja'
AND maja.geburtstag = anderestier.geburtstag
AND anderestier.name != 'Maja';

# 4E) Liste die Namen der Tiere auf, die am selben Tag geboren wurden wie Maja, und den
#     Vor- und Nachnamen ihrer Besitzer. Maja selbst soll nicht in der Liste erscheinen.
SELECT anderestier.name, besitzer.vorname, besitzer.nachname
FROM tier maja, tier anderestier, besitzer, beziehung
WHERE maja.name = 'Maja'
AND maja.geburtstag = anderestier.geburtstag
AND anderestier.name != 'Maja' 
AND anderestier.tier_id = beziehung_tier_id
AND besitzer_id = beziehung_besitzer_id;

# 4F) Liste alle Tiere mit Namen auf, die denselben Besitzer haben wie Hasso. Hasso darf
#     ebenfalls in der Liste erscheinen.
SELECT anderestier.name
FROM tier hasso, tier anderestier, beziehung hassobeziehung, beziehung anderebeziehung
WHERE hasso.name = 'Hasso'
AND hasso.tier_id = hassobeziehung.beziehung_tier_id
AND anderestier.tier_id = anderebeziehung.beziehung_tier_id 
AND hassobeziehung.beziehung_besitzer_id = anderebeziehung.beziehung_besitzer_id;
