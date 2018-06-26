CREATE INDEX namen_suche
ON Kunde (Name asc);

SELECT *
FROM Kunde
WHERE NAME LIKE 'P%';



CREATE UNIQUE INDEX artikelnummer_suche
ON Produkt (Artikelnummer asc);

SELECT *
FROM Produkt
WHERE Artikelnummer = 2;



CREATE UNIQUE INDEX desc_bestellnummer_suche
ON Bestellungen (Bestellnummer desc);

SELECT *
FROM Bestellungen
WHERE Bestellnummer > 3;