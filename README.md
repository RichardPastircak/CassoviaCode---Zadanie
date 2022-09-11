<h1> Cassovia Code - zadanie</h1>

<h2>Úloha 2</h2>
<b> Znenie:</b> <i>vytvoriť impex, pomocou ktorého by sme do systému vložili objednávku, pričom
objednávka obsahuje: názov, produkty, poznámku, zákazníka. Predpoklad je, že ani zákazník, a ani produkty v systéme neexistujú.</i>

S impexmi som sa žial nestretol ale vychádzaju z dokumentáciu by som úlohu spravil pomocou takéhoto impexu:
<b>INSERT Order;name[unique=true];products[mode=append, collection-delimiter = #],note;customer;</b>

<h2>Úloha 3</h2>
<b>Znenie:</b> <i>vytvoriť SQL dotaz, ktorý vyberie mená, priezviská a vek všetkých zákazníkov z
databázy, ktorí majú medzi 24 a 35 rokom života a zároveň vykonali za posledné 3 mesiace
aspoň 2 objednávky. Názvy tabuliek a stĺpcov ako aj ich prepojenia sú ponechané na uchádzačovi.</i>

Riešenie pri tabuľke, ktorá obsahuje nasledujúce údaje: Id (PK), meno, priezvisko, vek, dátum objednávky by bolo.


<b>select first_name, last_name, age from tast where age >= 24 and age <= 35 and extract(month from age(now(),dates)) <= 3 group by(first_name, last_name, age) having count(*) >= 2;<b/>
