DROP SCHEMA IF EXISTS terminplaner;
CREATE SCHEMA terminplaner DEFAULT CHARACTER SET utf8;
USE terminplaner;

CREATE TABLE termin
(
   termin_id INT AUTO_INCREMENT,
   datum DATE,
   zeit TIME,
   text VARCHAR(50) NOT NULL,
   PRIMARY KEY (termin_id)
);