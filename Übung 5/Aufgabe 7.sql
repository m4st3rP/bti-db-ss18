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
ORDER BY mname, anzahlstd DESC;

/* OUTPUT:
MNAME                PRJNAME                   APBEZEICHNUNG              ANZAHLSTD
-------------------- ------------------------- ------------------------- ----------
Behrendt             ERP Roll Out              Implementierung                   25
Behrendt             Optimierung Einkauf       EK Implementierung                20
Hansen               Optimierung ReWe          ReWe Konzept                      15
Horst                ERP Roll Out              Implementierung                   20
Krause               Optimierung Verkauf       VK Konzept                        20
Krause               Strategieentwicklung      geheim 1                          15
Krüger               Optimierung Einkauf       EK Implementierung                50
Krüger               Optimierung ReWe          ReWe Implementierung              20
Schulze              Strategieentwicklung      geheim 2                          40
Walter               Optimierung Verkauf       VK Konzept                        20
Walter               ERP Roll Out              Design                            20

11 Zeilen gewählt. 
 */
 
-- 5. Aufgabe
SELECT COUNT(DISTINCT mid) AS "Anzahl Mitarbeiter Opt Einkauf" FROM ach053.ARBEITET_AN
JOIN ach053.PROJEKT USING (prjid)
WHERE prjname = 'Optimierung Einkauf';

/* OUTPUT:
Anzahl Mitarbeiter Opt Einkauf
------------------------------
                             4
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
SELECT DISTINCT mname FROM ach053.ARBEITET_AN
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