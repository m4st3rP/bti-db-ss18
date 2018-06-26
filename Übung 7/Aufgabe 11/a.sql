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
 */
CREATE UNIQUE INDEX artikelnummer_suche
ON Produkt (Artikelnummer asc);

SELECT *
FROM Produkt
WHERE Artikelnummer = 2;



/*
 * Wir sortieren die Bestellungen absteigend über ihre einzigartige Bestellnummer, da uns neuere Bestellungen eher interessieren, damit wir sie schneller über ihre Bestellnummer finden können.
 */
CREATE UNIQUE INDEX desc_bestellnummer_suche
ON Bestellungen (Bestellnummer desc);

SELECT *
FROM Bestellungen
WHERE Bestellnummer > 3;