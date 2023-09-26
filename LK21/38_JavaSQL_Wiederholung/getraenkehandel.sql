DROP SCHEMA IF EXISTS getraenkehandel;
CREATE SCHEMA getraenkehandel DEFAULT CHARACTER SET utf8;
USE getraenkehandel;

CREATE TABLE lieferant (
  lieferant_id INT AUTO_INCREMENT,
  firma VARCHAR(30) NOT NULL,
  straße VARCHAR(30) NOT NULL,
  ort VARCHAR(30) NOT NULL,
  PRIMARY KEY (lieferant_id)
);

INSERT INTO lieferant VALUES
(1, 'Limonaden-Mix GmbH', 'Karl-Hauser-Str. 33-35', '32011 Quakenbrück'),
(2, 'Brauerei Hansen',    'Buchenweg 244',          '10352 Lummerstadt'),
(4, 'Cola und Co. KG',    'Breitenweg 214-215',     '88123 Heiligenrode');

CREATE TABLE getränk (
  getränk_id INT AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  anzahl INT NOT NULL DEFAULT 0,
  min_anzahl INT NOT NULL DEFAULT 0,
  max_anzahl INT NOT NULL DEFAULT 0,
  getränk_lieferant_id INT,
  PRIMARY KEY (getränk_id),
  FOREIGN KEY (getränk_lieferant_id) REFERENCES lieferant (lieferant_id)
);

INSERT INTO getränk VALUES
(NULL, 'Apfelsaft',        20, 30, 100, 4),
(NULL, 'Orangensaft',      30, 15,  60, 4),
(NULL, 'Cola',             18, 20,  80, 4),
(NULL, 'Bier',             62, 20,  80, 2),
(NULL, 'Wasser',           25, 30, 100, 1),
(NULL, 'Orangenlimonade',  11, 15,  40, 1),
(NULL, 'Zitronenlimonade',  5,  8,  30, 1);