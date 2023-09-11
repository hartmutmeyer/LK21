USE fahrzeuge;

# Aufgabe 5c
#
# 1.) Liste alle Fahrzeuge mit ihren (aktuellen und ehemaligen) Fahrzeughaltern auf. Für die Fahrzeuge sollen
#     das Kennzeichen und der Typ angegeben werden. Für die Fahrzeughalter werden der Vor- und Nachnamen
#     sowie das Anmelde- und Abmeldedatum angezeigt. Sortiere die Liste aufsteigend zuerst nach dem
#     Fahrzeug-Kennzeichen und dann nach dem Anmeldedatum.

SELECT kennzeichen, typ, vorname, nachname, angemeldet, abgemeldet
FROM fahrzeug, fahrzeughalter
WHERE kennzeichen = kfz_zeichen
ORDER BY kennzeichen, angemeldet;

# 2.) Erstelle dieselbe Abfrage wie unter 1., aber liste diesmal nur die Fahrzeughalter auf, die das Fahrzeug
#     aktuell besitzen (d.h. die das Fahrzeug noch nicht abgemeldet haben).

SELECT kennzeichen, typ, vorname, nachname, angemeldet, abgemeldet
FROM fahrzeug, fahrzeughalter
WHERE kennzeichen = kfz_zeichen
AND abgemeldet IS NULL
ORDER BY kennzeichen, angemeldet;

# 3.) Erstelle eine Liste aller Personen aus der Fahrzeughalter-Tabelle mit Vor- und Nachnamen. In der Liste
#     sollen keine Namen doppelt aufgelistet werden. Sortiere die Liste absteigend nach dem Nachnamen.

SELECT DISTINCT vorname, nachname
FROM fahrzeughalter
ORDER BY nachname DESC;

# 4.) Liste alle aktuellen oder ehemaligen Fahrzeuge von Emil Mühltal mit ihren vollständigen Fahrzeug-Daten
#     auf.

SELECT kennzeichen, typ, farbe, baujahr
FROM fahrzeughalter, fahrzeug
WHERE kennzeichen = kfz_zeichen
AND vorname = 'Emil'
AND nachname = 'Mühltal';

# 5.) Liste alle Fahrzeuge mit Kennzeichen und Typ auf, die im selben Jahr gebaut wurden, wie das Fahrzeug
#     HB - AZ 123. Das Fahrzeug HB - AZ 123 soll nicht in der Liste erscheinen.

SELECT f1.kennzeichen, f1.typ
FROM  fahrzeug f1, fahrzeug f2
WHERE f1.baujahr = f2.baujahr
AND f2.kennzeichen = 'HB - AZ 123' 
AND f1.kennzeichen != f2.kennzeichen;

# 6.) Erstelle eine Liste aller Fahrzeuge (mit vollständigen Fahrzeug-Daten), die schon mindestens drei Halter
#     hatten.

SELECT kennzeichen, typ, farbe, baujahr
FROM fahrzeug, fahrzeughalter
WHERE kennzeichen = kfz_zeichen
GROUP BY kennzeichen
HAVING COUNT(*)>=3;