USE schule;

# 1. Susi Sonntag hat ihren Kollegen Donald Dusel geheiratet und seinen Nachnamen
#    angenommen. Ändere ihren Nachnamen entsprechend um.

UPDATE lehrer
SET nachname = 'Dusel'
WHERE vorname = 'Susi'
AND nachname = 'Sonntag';

# 2. Kurs 12 wird von Susi Dusel übernommen. Ändere die LehrerId in der Kurs-Tabelle
#    entsprechend.

UPDATE kurs, lehrer
SET kurs_lehrer_id = (SELECT lehrer_id FROM lehrer WHERE vorname = 'Susi' AND nachname = 'Dusel')
WHERE kurs_id = 12;

# 3. Isabell Steiner verlässt die Schule. Lösche ihren Eintrag in der Schüler-Tabelle
#    und alle zugehörigen Einträge in der Noten-Tabelle.

DELETE FROM note
WHERE note_schueler_id = (SELECT schueler_id FROM schueler WHERE vorname = 'Isabell' AND nachname = 'Steiner');

DELETE FROM schueler
WHERE vorname = 'Isabell' 
AND nachname = 'Steiner';

# 4. Die Schule hat einen neuen Lehrer bekommen. Er heißt Arne Klee. Füge für Herrn
#    Klee einen neuen Eintrag in die Lehrer-Tabelle ein.

INSERT INTO lehrer (nachname, vorname, geschlecht) VALUES
('Klee', 'Arne', 'm');