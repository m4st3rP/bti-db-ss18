SELECT * 
from BESTELLUNG --, BESTELLUNG_PRODUKT_BESTEHT, PRODUKT
join BESTELLUNG_PRODUKT_BESTEHT on BESTELLUNG.BESTELLNUMMER = BESTELLUNG_PRODUKT_BESTEHT.BESTELLNUMMER
join PRODUKT on BESTELLUNG_PRODUKT_BESTEHT.ARTIKELNUMMER = PRODUKT.ARTIKELNUMMER
join KUNDE on BESTELLUNG.KUNDENNUMMER = KUNDE.KUNDENNUMMER
where BESTELLUNG.BESTELLNUMMER = BESTELLUNG_PRODUKT_BESTEHT.BESTELLNUMMER;