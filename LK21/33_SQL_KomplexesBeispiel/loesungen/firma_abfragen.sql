USE firma;

# 2a
# Erstelle eine Liste aller Abteilungen (Abteilungsname und Abteilungsnummer) und der Namen der zugehörigen
# Abteilungsleiter (Vor- und Nachname). Sortiere die Liste absteigend nach dem Abteilungsnamen.

SELECT abteilung.abteilungsname, abteilung.abteilung_id, angestellter.vorname, angestellter.nachname
FROM abteilung, angestellter
WHERE abteilung.abteilungsleiter_id = angestellter.angestellter_id
ORDER BY abteilung.abteilungsname DESC;

# 2b
# Erstelle eine Liste aller Angestellten und ihrer Angehörigen. Es sollen der Vor- und Nachname der Angestellten
# sowie der Name, das Geschlecht und der Verwandtschaftsgrad der Angehörigen aufgelistet werden. Auch
# Angestellte, die keine Angehörigen besitzen, sollen in der Tabelle aufgelistet werden. Sortiere die Liste
# alphabetisch zuerst nach dem Nachnamen und anschließend nach dem Vornamen der Angestellten. Danach
# soll die Liste nach dem Namen der Angehörigen sortiert werden.

SELECT angestellter.vorname, angestellter.nachname, angehöriger.vorname, angehöriger.nachname, angehöriger.geschlecht, angehöriger.verwandtschaft
FROM angestellter LEFT JOIN angehöriger
ON angestellter.angestellter_id = angehöriger.angehöriger_angestellter_id
ORDER BY angestellter.vorname, angestellter.nachname, angehöriger.vorname;

# 2c
# Ermittle den Namen (Vor- und Nachname) des Vorgesetzten von Jennifer Wallace.

SELECT chef.vorname, chef.nachname
FROM angestellter mitarbeiter, angestellter chef
WHERE mitarbeiter.vorgesetzter_id = chef.angestellter_id 
AND mitarbeiter.vorname = 'Jennifer' 
AND mitarbeiter.nachname = 'Wallace';

# 2d
# Zähle die Anzahl der Projekte, die momentan in der Firma durchgeführt werden.

SELECT COUNT(*) "Anzahl Projekte"
FROM projekt;

# 2e
# Erstelle eine Liste mit allen Angestellten (Vor- und Nachname) und den Projekten (Projekt-Name und
# Projekt-Nummer), an denen sie arbeiten.

SELECT angestellter.nachname, angestellter.vorname, projekt.projektname, projekt.projekt_id
FROM angestellter, arbeitet_an, projekt
WHERE angestellter.angestellter_id = arbeitet_an.arbeitet_an_angestellter_id 
AND arbeitet_an.arbeitet_an_projekt_id = projekt.projekt_id
ORDER BY angestellter.nachname, angestellter.vorname;

# 2f
# Erstelle eine Liste aller Mitarbeiter (Vorname, Nachname, Geburtstag, Geschlecht), die am Projekt 
# "Newbenefits" arbeiten.

SELECT angestellter.vorname, angestellter.nachname, angestellter.geburtstag, angestellter.geschlecht
FROM angestellter, arbeitet_an, projekt
WHERE angestellter.angestellter_id = arbeitet_an.arbeitet_an_angestellter_id 
AND arbeitet_an.arbeitet_an_projekt_id = projekt.projekt_id 
AND projekt.projektname = 'Newbenefits';

# 2g
# Ermittle den Abteilungsleiter (Vor- und Nachname), der für das Projekt "Reorganization" zuständig ist.

SELECT angestellter.vorname, angestellter.nachname
FROM angestellter, abteilung, projekt
WHERE angestellter.angestellter_id = abteilung.abteilungsleiter_id 
AND abteilung.abteilung_id = projekt.projekt_abteilung_id 
AND projekt.projektname = 'Reorganization';

# 2h
# Zähle die Anzahl der Mitarbeiter im Projekt "Reorganization".

SELECT COUNT(*) "Anzahl Projektmitarbeiter"
FROM arbeitet_an, projekt
WHERE arbeitet_an.arbeitet_an_projekt_id = projekt.projekt_id 
AND projekt.projektname = 'Reorganization';

# 2i
# Liste alle Orte auf, in denen die Firma eine Niederlassung besitzt. Jeder Ort soll nur einmal aufgelistet
# werden.

SELECT DISTINCT ortsname
FROM ort
ORDER BY ortsname;

# 2j
# Zähle die Anzahl der Projekte, an denen jeder einzelne Mitarbeiter arbeitet. Die Mitarbeiter sollen mit Vor-
# und Nachnamen aufgelistet werden.

SELECT angestellter.vorname, angestellter.nachname, COUNT(*) "Anzahl Projekte"
FROM angestellter, arbeitet_an
WHERE angestellter.angestellter_id = arbeitet_an.arbeitet_an_angestellter_id
GROUP BY angestellter.angestellter_id
ORDER BY angestellter.nachname;

# 2k
# Liste alle Projekte mit Namen auf, die am selben Ort wie das Projekt „Newbenefits“ stattfinden. Das Projekt
# "Newbenefits" selbst soll nicht in der Liste erscheinen.

SELECT anderesprojekt.projektname
FROM projekt anderesprojekt, projekt newbenefits
WHERE anderesprojekt.projekt_ort_id = newbenefits.projekt_ort_id 
AND newbenefits.projektname = 'Newbenefits' 
AND anderesprojekt.projektname != newbenefits.projektname;

# 2l
# Liste alle Mitarbeiter mit Vornamen, Nachnamen und Angestellten-Nummer auf, die mindestens drei
# Angehörige besitzen.

SELECT angestellter.vorname, angestellter.nachname, angestellter.angestellter_id
FROM angestellter, angehöriger
WHERE angestellter.angestellter_id = angehöriger.angehöriger_angestellter_id
GROUP BY angestellter.angestellter_id
HAVING COUNT(*) >= 3;
