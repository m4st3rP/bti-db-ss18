DROP TABLE Merkliste_Produkt_besteht;
DROP TABLE Bestellung_Produkt_besteht;
DROP TABLE Telefonnummer;
DROP TABLE Bestellung;
DROP TABLE P1;
DROP TABLE P2;
DROP TABLE Überweisung;
DROP TABLE Kreditkarte;
DROP TABLE Merkliste;
DROP TABLE Produkt;
DROP TABLE Kunde;
DROP TABLE Zahlungsart;


CREATE TABLE Kunde (
    Kundennummer int PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Straße varchar(255) NOT NULL,
	Nummer int NOT NULL,
	Ort varchar(255) NOT NULL
);

CREATE TABLE Telefonnummer (
    Kundennummer int,
    CONSTRAINT kdnr FOREIGN KEY (Kundennummer) REFERENCES Kunde(Kundennummer),
	Nummer int,
    PRIMARY KEY (Kundennummer, Nummer)
);

CREATE TABLE Zahlungsart (
    Nummer int PRIMARY KEY
);

CREATE TABLE Bestellung (
    Bestellnummer int PRIMARY KEY,
	Kundennummer int,
    CONSTRAINT kdnr2 FOREIGN KEY (Kundennummer) REFERENCES Kunde(Kundennummer),
	Kontonummer int,
    CONSTRAINT ktnr FOREIGN KEY (Kontonummer) REFERENCES Zahlungsart(Nummer),
	Bestelldatum DATE NOT NULL,
	Bestellstatus VARCHAR(255) NOT NULL
);

CREATE TABLE Produkt (
    Artikelnummer int PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Lagerort varchar(255) NOT NULL,
	Preis NUMBER(6,2) NOT NULL,
	CONSTRAINT valider_preis CHECK (Preis >= 0.00 and Preis < 1000.00),
	Bestand int NOT NULL
);

CREATE TABLE P1 (
    Artikelnummer int PRIMARY KEY,
    CONSTRAINT artnr FOREIGN KEY (Artikelnummer) REFERENCES Produkt(Artikelnummer),
	Attribut_P1 varchar(255) NOT NULL
);

CREATE TABLE P2 (
    Artikelnummer int PRIMARY KEY,
    CONSTRAINT artnr2 FOREIGN KEY (Artikelnummer) REFERENCES Produkt(Artikelnummer),
	Attribut_P2 varchar(255) NOT NULL
);

CREATE TABLE Überweisung (
    Nummer int PRIMARY KEY,
    CONSTRAINT ktnr2 FOREIGN KEY (Nummer) REFERENCES Zahlungsart(Nummer),
	Name varchar(255) NOT NULL,
	BIC int NOT NULL
);

CREATE TABLE Kreditkarte (
    Nummer int PRIMARY KEY,
    CONSTRAINT ktnr3 FOREIGN KEY (Nummer) REFERENCES Zahlungsart(Nummer),
	CVV int NOT NULL,
	Gültigkeitsdatum DATE NOT NULL
);

CREATE TABLE Bestellung_Produkt_besteht (
    Artikelnummer int,
    CONSTRAINT artnr3 FOREIGN KEY (Artikelnummer) REFERENCES Produkt(Artikelnummer),
	Bestellnummer int,
    CONSTRAINT bsnr FOREIGN KEY (Bestellnummer) REFERENCES Bestellung(Bestellnummer),
	Menge int NOT NULL,
    PRIMARY KEY (Artikelnummer, Bestellnummer)
);

CREATE TABLE Merkliste (
    Name varchar(255),
	Kundennummer int,
    CONSTRAINT kdnr3 FOREIGN KEY (Kundennummer) REFERENCES Kunde(Kundennummer),
    PRIMARY KEY (Name, Kundennummer)
);

CREATE TABLE Merkliste_Produkt_besteht (
    Artikelnummer int,
    CONSTRAINT artnr4 FOREIGN KEY (Artikelnummer) REFERENCES Produkt(Artikelnummer),
	Kundennummer int,
	Merklistenname varchar(255),
    CONSTRAINT mname FOREIGN KEY (Kundennummer, Merklistenname) REFERENCES Merkliste(Kundennummer, Name),
    PRIMARY KEY (Artikelnummer, Kundennummer, Merklistenname)
);