-- 1
SELECT prjid, prjname
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
WHERE mname = 'Horst'
INTERSECT
SELECT prjid, prjname
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
WHERE mname = 'Schmidt';

/* Output:
1105    Optimierung ReWe
*/

-- 2
SELECT mid, mname, prjname, SUM(anzahlstd)
FROM ach053.MITARBEITER
JOIN ach053.ARBEITET_AN USING (mid)
JOIN ach053.PROJEKT USING (prjid)
WHERE prjname = 'ERP Roll Out'
GROUP BY (mid, mname, prjname);

/* Output:
       MID MNAME                PRJNAME                   SUM(ANZAHLSTD)
---------- -------------------- ------------------------- --------------
      1005 Walter               ERP Roll Out                          20
      1006 Behrendt             ERP Roll Out                          35
      1007 Krüger               ERP Roll Out                           5
      1008 Horst                ERP Roll Out                          30
*/

-- 3
SELECT prjid, prjname, mid, mname, SUM(anzahlstd)
FROM ach053.ARBEITET_AN
JOIN ach053.PROJEKT USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
GROUP BY (prjid, prjname, mid, mname);

/* Output:
1101    ERP Roll Out            1005    Walter      20
1103    Optimierung Verkauf     1006    Behrendt     5
1103    Optimierung Verkauf     1004    Krause      30
1104    Strategieentwicklung    1006    Behrendt     7
1102    Optimierung Einkauf     1007    Krüger      50
1101    ERP Roll Out            1007    Krüger       5
1101    ERP Roll Out            1008    Horst       30
1105    Optimierung ReWe        1009    Schmidt     10
1102    Optimierung Einkauf     1001    Müller      10
1105    Optimierung ReWe        1007    Krüger      20
1104    Strategieentwicklung    1008    Horst       10
1105    Optimierung ReWe        1010    Hansen      15
1103    Optimierung Verkauf     1005    Walter      20
1104    Strategieentwicklung    1005    Walter       5
1101    ERP Roll Out            1006    Behrendt    35
1103    Optimierung Verkauf     1008    Horst        5
1104    Strategieentwicklung    1003    Schulze     40
1105    Optimierung ReWe        1005    Walter      10
1104    Strategieentwicklung    1007    Krüger       3
1102    Optimierung Einkauf     1006    Behrendt    20
1103    Optimierung Verkauf     1007    Krüger      10
1102    Optimierung Einkauf     1002    Meier       15
1103    Optimierung Verkauf     1003    Schulze     15
1104    Strategieentwicklung    1004    Krause      25
1105    Optimierung ReWe        1008    Horst       10
*/

-- 4
SELECT prjid, prjname
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
WHERE prjname LIKE '%Optimierung%'
GROUP BY prjid, prjname
HAVING SUM(anzahlstd) > 90;

/* Output:
1102    Optimierung Einkauf
*/

-- 5
SELECT prjname, COUNT(DISTINCT mid) as Anzahl
    FROM ach053.PROJEKT
    JOIN ach053.ARBEITET_AN USING (prjid)
    GROUP BY prjname
HAVING COUNT(DISTINCT mid)  >= (SELECT  max(COUNT(DISTINCT mid))
        FROM ach053.ARBEITET_AN
        GROUP BY prjid);

/* Output:
PRJNAME                       ANZAHL
------------------------- ----------
Optimierung Verkauf                6
Strategieentwicklung               6
*/

-- 6 *
SELECT prjname, mname
FROM ach053.ARBEITET_AN
JOIN ach053.MITARBEITER USING (mid)
JOIN ach053.PROJEKT USING (prjid)
GROUP BY prjid, mid, prjname, mname
HAVING (SUM(anzahlstd)/35*100) >= (SELECT SUM(anzahlstd) FROM ach053.ARBEITET_AN GROUP BY prjname, mname);
     

/* Output:

PRJNAME                   MNAME               
------------------------- --------------------
Strategieentwicklung      Krüger              
Optimierung Einkauf       Meier               
Strategieentwicklung      Horst               
Strategieentwicklung      Schulze             
Strategieentwicklung      Walter              
Optimierung Einkauf       Behrendt            
Optimierung Einkauf       Müller              
Optimierung Einkauf       Krüger              
Strategieentwicklung      Krause              
Strategieentwicklung      Behrendt            

10 Zeilen gewählt. 
*/

SELECT DISTINCT prjname, ach053.MITARBEITER.mname
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
JOIN (SELECT mid, prjid, SUM(anzahlstd) as Stunden
    FROM ach053.MITARBEITER
    JOIN ach053.ARBEITET_AN USING (mid)
    JOIN ach053.PROJEKT USING (prjid)
    GROUP BY mid, prjid)
USING (prjid)
JOIN(SELECT prjid, SUM(anzahlstd) as GesStundenPrj
     FROM ach053.PROJEKT
     JOIN ach053.ARBEITET_AN USING (prjid)
     GROUP BY prjid)
USING (prjid)
WHERE Stunden / GesStundenPrj > 0.35;
