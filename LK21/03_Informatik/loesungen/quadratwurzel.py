radikand = float(raw_input('Gib eine positive Zahl ein: '))
akzeptierterFehler = 0.0001
anzahlSchritte = 0
untereGrenze = 0.0
obereGrenze = max(1.0, radikand)
gerateneLoesung = (untereGrenze + obereGrenze) / 2.0
quadrat = gerateneLoesung * gerateneLoesung
abweichung = abs(quadrat - radikand)

while abweichung > akzeptierterFehler:
    anzahlSchritte += 1
    if quadrat < radikand:
        untereGrenze = gerateneLoesung
    else:
        obereGrenze = gerateneLoesung
    gerateneLoesung = (obereGrenze + untereGrenze) / 2.0
    quadrat = gerateneLoesung * gerateneLoesung
    abweichung = abs(quadrat - radikand)

print 'Anzahl Schritte =', anzahlSchritte
print gerateneLoesung, 'ist die Quadratwurzel von', radikand    