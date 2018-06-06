INSERT INTO Produkt
VALUES (
	1,
    'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb',
	'1A',
	8.00,
	45
);
	
INSERT INTO Produkt
VALUES (
	2,
    'Metro 2033',
	'1B',
	12.00,
	33
);
	
INSERT INTO Produkt
VALUES (
	3,
    'Creedence Clearwater Revival - Cosmos Factory',
	'1C',
	7.00,
	5
);
	
INSERT INTO Produkt
VALUES (
	4,
    'Girls Last Tour',
	'1A',
	35.00,
	6
);
	
INSERT INTO Produkt
VALUES (
    5,
    'SQL for Beginners',
	'1B',
	40.00,
	110
);
	
INSERT INTO Kunde
VALUES (
    1,
	'Philipp Schwarz',
	'ABC-Straße',
	3,
	'Hamburg'
);

INSERT INTO Kunde
VALUES (
    2,
	'Finn-Frederik Jannsen',
	'CDE-Straße',
	2,
	'Hamburg'
);

INSERT INTO Kunde
VALUES (
    3,
	'Martin Schultz',
	'Am Grünweg',
	74,
	'Hamburg'
);

INSERT INTO Kunde
VALUES (
    4,
	'Otto Wels',
	'Weimarer Straße',
	18,
	'Berlin'
);

INSERT INTO Telefonnummer
VALUES (1, 12345);

INSERT INTO Telefonnummer
VALUES (2, 23456);

INSERT INTO Telefonnummer
VALUES (3, 34567);

INSERT INTO Telefonnummer
VALUES (4, 45678);

INSERT INTO Zahlungsart
VALUES (1234);

INSERT INTO Zahlungsart
VALUES (2345);

INSERT INTO Zahlungsart
VALUES (3456);

INSERT INTO Bestellung
VALUES (
    1,
	1,
	1234,
	TO_DATE ('15.05.2018 18:31','DD.MM.YYYY HH24:MI'),
	'Versand'
);

INSERT INTO Bestellung
VALUES (
    2,
	1,
	1234,
	TO_DATE ('15.05.2018 20:31','DD.MM.YYYY HH24:MI'),
	'Versand'
);

INSERT INTO Bestellung
VALUES (
    3,
	2,
	2345,
	TO_DATE ('16.05.2018 02:12','DD.MM.YYYY HH24:MI'),
	'Versand'
);

INSERT INTO Bestellung
VALUES (
    4,
	2,
	2345,
	TO_DATE ('16.05.2018 12:31','DD.MM.YYYY HH24:MI'),
	'Bearbeitung'
);

INSERT INTO Bestellung
VALUES (
    5,
	3,
	3456,
	TO_DATE ('16.05.2018 14:00','DD.MM.YYYY HH24:MI'),
	'Bearbeitung'
);

INSERT INTO Bestellung
VALUES (
    6,
	3,
	3456,
	TO_DATE ('16.05.2018 14:31','DD.MM.YYYY HH24:MI'),
	'Bearbeitung'
);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (1, 1, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (2, 1, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (3, 2, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (4, 2, 2);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (5, 3, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (1, 3, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (2, 4, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (3, 4, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (4, 5, 2);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (5, 5, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (1, 6, 1);

INSERT INTO Bestellung_Produkt_Besteht
VALUES (2, 6, 3);

INSERT INTO Überweisung
VALUES (1234, 'Philipp Schwarz', 778899);

INSERT INTO Überweisung
VALUES (2345, 'Finn-Frederik Jannsen', 667788);

INSERT INTO Kreditkarte
VALUES (3456, 123, '05.12.2020');