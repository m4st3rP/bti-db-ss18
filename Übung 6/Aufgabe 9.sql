-- 1
SELECT prjid, prjname
FROM ach053.PROJEKT
INTERSECT
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
JOIN ach053.ARBEITSPAKETE USING (apid) -- or maybe prjid?
WHERE prjname = 'ERP Roll Out'
GROUP BY (mid, mname, prjname);

/* Output:
1007    Krüger       25
1006    Behrendt    100
1005    Walter      100
1008    Horst        90
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
SELECT prjname, COUNT(mid) as AnzahlMitarbeiter
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
GROUP BY prjname
ORDER BY AnzahlMitarbeiter DESC
FETCH FIRST ROW ONLY;

/* Output:
Optimierung Verkauf    8
*/

-- 6 unfinished
SELECT prjname, mname
FROM ach053.PROJEKT
JOIN ach053.ARBEITET_AN USING (prjid)
JOIN ach053.MITARBEITER USING (mid)
JOIN(SELECT prjname, SUM(anzahlstd) as GesStundenPrj
     FROM ach053.PROJEKT
     JOIN ach053.ARBEITET_AN USING (prjid)
     GROUP BY prjname)
USING (prjname)
WHERE anzahlstd / GesStundenPrj > 0.35;
/* Output:
Optimierung Einkauf     Krüger
Strategieentwicklung    Schulze
*/
