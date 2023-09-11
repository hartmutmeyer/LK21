USE schule;

# 1. Liste alle Lehrer mit ihren vollständigen Attributen auf. Sortiere die Lehrer dabei 
#    aufsteigend zunächst nach dem Vornamen und dann nach dem Nachnamen.

SELECT *
FROM lehrer 
ORDER BY vorname, nachname;

# 2. Liste alle Kurse (mit KursId und Fach) auf, die der Lehrer Donald Dusel unterrichtet.

SELECT kurs_id, fach
FROM kurs, lehrer 
WHERE kurs_lehrer_id = lehrer_id AND vorname = 'Donald' AND nachname = 'Dusel';

# 3. Erstelle eine Liste mit allen Lehrern (Vor- und Nachname) und den Fächern, die sie
#    unterrichten. Achte darauf, dass keine Zeilen mehrfach erscheinen.

SELECT DISTINCT vorname, nachname, fach
FROM kurs, lehrer 
WHERE kurs_lehrer_id = lehrer_id;

# 4. Erstelle für den Schüler Daniel Weber eine Tabelle mit allen seinen Fächern und der 
#    Note, die er jeweils in dem Fach hat. Sortiere die Liste absteigend nach dem Wert
#  der Note.

SELECT fach, note
FROM schueler, note, kurs
WHERE vorname = 'Daniel' AND nachname = 'Weber' AND schueler_id = note_schueler_id 
AND kurs_id = note_kurs_id
ORDER BY note DESC;

# 5. Liste alle Schüler auf (Vor- und Nachname), die bei der Lehrerin Pia Bachmann
#    Unterricht haben.

SELECT schueler.vorname, schueler.nachname
FROM  schueler, note, kurs, lehrer
WHERE lehrer.vorname = 'Pia' 
AND lehrer.nachname = 'Bachmann' 
AND lehrer_id = kurs_lehrer_id 
AND kurs_id = note_kurs_id 
AND note_schueler_id = schueler_id;

# 6. Liste alle Schüler auf, die im Fach Mathematik einen Unterkurs haben 
#   (Vorname, Nachname und Note).

SELECT schueler.vorname, schueler.nachname, note
FROM  schueler, note, kurs
WHERE fach = 'Mathematik' 
AND kurs_id = note_kurs_id 
AND note_schueler_id = schueler_id 
AND note < 5;

# 7. Ermittle die Anzahl der weiblichen und die Anzahl der männlichen Lehrer.

SELECT geschlecht, COUNT(*)
FROM  lehrer
GROUP BY geschlecht;

# 8. Liste alle Schüler auf (SchülerId, Vorname und Nachname), für die noch keine Noten
#    eingetragen wurden.

SELECT schueler_id, vorname, nachname
FROM  schueler LEFT JOIN note
ON note_schueler_id = schueler_id 
WHERE note_kurs_id IS NULL;

# 9. Liste alle Fächer auf, für die mehr als ein Kurs angeboten wird (Fach und Kursanzahl).

SELECT fach, COUNT(*)
FROM  kurs
GROUP BY fach
HAVING COUNT(*) > 1;

# 10. Liste alle Lehrer auf (Vor- und Nachname), die mit der Lehrerin Pia Bachmann
#    ein Unterrichtsfach gemeinsam haben. Frau Bachmann selbst darf auch in der Liste
#    erscheinen. Es soll jedoch kein Lehrer doppelt aufgelistet werden.

SELECT DISTINCT kollege.Vorname, kollege.Nachname
FROM lehrer kollege, kurs kurs_kollege, kurs kurs_bachmann, lehrer bachmann
WHERE bachmann.vorname = 'Pia' 
AND bachmann.nachname = 'Bachmann' 
AND kurs_bachmann.kurs_lehrer_id = bachmann.lehrer_id 
AND kurs_bachmann.fach = kurs_kollege.fach 
AND kurs_kollege.kurs_lehrer_id = kollege.lehrer_id;
