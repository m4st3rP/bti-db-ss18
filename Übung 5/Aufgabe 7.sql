-- 1. Aufgabe
SELECT DISTINCT mname FROM ach053.MITARBEITER
JOIN ach053.ABTEILUNG USING (abtid)
WHERE abtname = 'IT';

/* OUTPUT:
MNAME               
--------------------
Horst
Krüger
Behrendt
 */

-- 2. Aufgabe
SELECT AVG(ALL anzahlstd) FROM (SELECT anzahlstd FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid));

/* OUTPUT:
31 Zeilen gewählt. 


AVG(ALLANZAHLSTD)
-----------------
13,7096774  
*/ 

-- 3. Aufgabe
SELECT DISTINCT prjname, mname, abtname FROM ach053.MITARBEITER
JOIN ach053.PROJEKT ON mid = prjleiter
JOIN ach053.ABTEILUNG USING (abtid)
WHERE mid = prjleiter

/* OUTPUT:
PRJNAME                   MNAME                ABTNAME                  
------------------------- -------------------- -------------------------
Optimierung Verkauf       Krause               Verkauf                  
ERP Roll Out              Hansen               ReWe                     
Strategieentwicklung      Walter               ReWe                     
Optimierung Einkauf       Meier                Einkauf                  
Optimierung ReWe          Walter               ReWe 
 */
 
-- 4. Aufgabe
SELECT mname, prjname, apbezeichnung, anzahlstd FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid)
JOIN ach053.PROJEKT USING (prjid)
JOIN ach053.ARBEITSPAKETE USING (prjid, apid)
WHERE anzahlstd >= 15
ORDER BY anzahlstd DESC;

/* OUTPUT:
MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Krüger               Optimierung Einkauf       EK Implementierung                50
Krüger               Optimierung Einkauf       VK Implementierung                50
Krüger               Optimierung Einkauf       Design                            50
Krüger               Optimierung Einkauf       ReWe Implementierung              50
Krüger               Optimierung Einkauf       geheim 3                          50
Schulze              Strategieentwicklung      Konzept                           40
Schulze              Strategieentwicklung      VK Konzept                        40
Schulze              Strategieentwicklung      EK Konzept                        40
Schulze              Strategieentwicklung      ReWe Konzept                      40
Schulze              Strategieentwicklung      geheim 2                          40
Behrendt             ERP Roll Out              ReWe Test                         25

MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Behrendt             ERP Roll Out              Implementierung                   25
Krause               Optimierung Verkauf       VK Konzept                        20
Walter               Optimierung Verkauf       VK Konzept                        20
Walter               ERP Roll Out              EK Implementierung                20
Behrendt             Optimierung Einkauf       EK Implementierung                20
Krüger               Optimierung ReWe          EK Implementierung                20
Krause               Optimierung Verkauf       EK Konzept                        20
Walter               Optimierung Verkauf       EK Konzept                        20
Horst                ERP Roll Out              Implementierung                   20
Walter               ERP Roll Out              Design                            20
Behrendt             Optimierung Einkauf       Design                            20

MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Krüger               Optimierung ReWe          Design                            20
Krause               Optimierung Verkauf       Konzept                           20
Walter               Optimierung Verkauf       Konzept                           20
Horst                ERP Roll Out              ReWe Test                         20
Walter               ERP Roll Out              ReWe Implementierung              20
Behrendt             Optimierung Einkauf       ReWe Implementierung              20
Krüger               Optimierung ReWe          ReWe Implementierung              20
Krause               Optimierung Verkauf       ReWe Konzept                      20
Walter               Optimierung Verkauf       ReWe Konzept                      20
Walter               ERP Roll Out              geheim 3                          20
Behrendt             Optimierung Einkauf       geheim 3                          20

MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Krüger               Optimierung ReWe          geheim 3                          20
Krause               Optimierung Verkauf       geheim 2                          20
Krüger               Optimierung ReWe          VK Implementierung                20
Behrendt             Optimierung Einkauf       VK Implementierung                20
Walter               ERP Roll Out              VK Implementierung                20
Walter               Optimierung Verkauf       geheim 2                          20
Hansen               Optimierung ReWe          EK Konzept                        15
Krause               Strategieentwicklung      VK Planung                        15
Hansen               Optimierung ReWe          ReWe Konzept                      15
Krause               Strategieentwicklung      ReWe Planung                      15
Hansen               Optimierung ReWe          VK Konzept                        15

MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Krause               Strategieentwicklung      geheim 1                          15
Krause               Strategieentwicklung      EK Planung                        15
Krause               Strategieentwicklung      Planung                           15
Hansen               Optimierung ReWe          Konzept                           15
Hansen               Optimierung ReWe          geheim 2                          15

49 Zeilen gewählt. 
 */
 
-- 5. Aufgabe
SELECT COUNT(*) FROM ach053.ARBEITET_AN
JOIN ach053.PROJEKT USING (prjid)
WHERE prjname = 'Optimierung Einkauf';

/* OUTPUT:
  COUNT(*)
----------
         5 
 */

-- 6. Aufgabe
SELECT DISTINCT mname FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid)
JOIN ach053.PROJEKT USING (prjid)
WHERE prjname LIKE '%er%';

/* OUTPUT:
MNAME               
--------------------
Müller
Meier
Schulze
Krause
Schmidt
Hansen
Walter
Krüger
Horst
Behrendt

10 Zeilen gewählt. 
*/

-- 7. Aufgabe
SELECT mname FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid)
JOIN ach053.PROJEKT USING (prjid)
WHERE NOT prjname = 'Optimierung Einkauf';

/* OUTPUT:
MNAME               
--------------------
Schulze
Krause
Schmidt
Hansen
Walter
Krüger
Horst
Behrendt

8 Zeilen gewählt. 
 */
 
-- 8. Aufgabe
SELECT prjid, apid, mid FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid)
JOIN ach053.PROJEKT USING (prjid)
ORDER BY anzahlstd ASC
FETCH NEXT 1 ROWS ONLY;

/* OUTPUT:
     PRJID       APID        MID
---------- ---------- ----------
      1104        103       1007


 */
 
-- 9. Aufgabe
SELECT mname FROM ach053.MITARBEITER
WHERE mid NOT IN (SELECT mid FROM ach053.ARBEITET_AN);

/* OUPTUT:
MNAME               
--------------------
Klaus
 */
 
-- 10. Aufgabe
SELECT prjname, apbezeichnung, anzahlstd FROM ach053.ARBEITSPAKETE
JOIN ach053.PROJEKT USING (prjid)
LEFT OUTER JOIN ach053.ARBEITET_AN USING (prjid, apid)
WHERE anzahlstd IS NULL;

/* OUTPUT:
PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
------------------------- ------------------------- ----------
ERP Roll Out              Test                                
Optimierung ReWe          ReWe Test                           
 */