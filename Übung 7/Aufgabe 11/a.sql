/*
 * Wir sortieren die Kunden alphabetisch aufsteigend über ihren Namen damit wir sie schneller über ihren Namen finden können.
 */
CREATE INDEX namen_suche
ON Kunde (Name asc);

SELECT *
FROM Kunde
WHERE NAME LIKE 'P%';



/*
 * Wir sortieren die Produkte aufsteigend über ihre einzigartige Artikelnummer damit wir sie schneller über ihre Artikelnummer finden können.
 * Ohne Index dauert das Select Statement 0,467 Sekunden und mit 0,35 Sekunden. 
 * Der Grund dafür ist, dass das DBMS anhand des Indexes alle Einträge mit Index höher als von dem ersten Eintrag mit Preis > 900 fetchen kann.
 * Ohne Index würden erst alle Preise gelesen und dann Einträge mit Preis <= 900 ignoriert werden.
 */
CREATE INDEX preis_suche
ON Produkt (Preis asc);

SELECT *
FROM Produkt
WHERE Preis > 900;



/*
 * Wir sortieren die Bestellungen absteigend über ihre einzigartige Bestellnummer, da uns neuere Bestellungen eher interessieren, damit wir sie schneller über ihre Bestellnummer finden können.
 */
CREATE UNIQUE INDEX desc_bestellnummer_suche
ON Bestellungen (Bestellnummer desc);

SELECT *
FROM Bestellungen
WHERE Bestellnummer > 3;