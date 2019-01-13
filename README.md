# OMO Smart Home

Semestrální práce OMO

### Mělo by to byt...
něco jiného než to, co to ve skutečností je.

Systém by měl fungovat takhle: Vytvoření domu (`House`), naplnění místnostmi (`Room`) ve kterých jsou různé přístroje (`Device`).

Přístroje můžou byt použité lidmi (`Human`), pro specifické účely. Různé přístroje můžou spotřebovat zdroje (`Resource`) když jsou v použití. 

### Tohle všechno jsou ale statické elementy.

Po (a nebo taky pře, nebo před) vytvoření domu vytvořím živé objekty: zvířátka (`Animal`) a lidi (`Human`). Ti mají potřeby (`Need`) a takže když jím něco chybí, tak snaží to dostat. Komunikují s jinými živými objekty a **používají přistroje.**

**Dohromady** se to spojuje systém událostí (`Event`) a času (`Time`). Funguje tak, že paralelně se spouští vlákno, které simulují běh času a každý interval (`tick`) délkou v 15 min. virtuálního času kontroluje všechny událostí.

Přes událostí se provádějí změny v celém domě. *A můžou se nastat různé situace.*

### Nejzajímavější je příklad situace beznadějnosti, reprezentovaná LifeIsHardException.
Jednoduchý způsob jak tuto situaci nasimulujete je vytvořit dům (`House`) bez vany (`Bath`), přidat do bytu maminku (`Adult`) + dítě (`Baby`). Po nějakém čase (pseudo random) dítě bude potřebovat koupání, “oznámí” matku, a ve chvíli když matka pochopí, že žádná vana v domě není, tak zastaví čas a *rozbije program.*


*Bohužel zkusil jsem napsat něco přišil složitého v té časové délce, kterou jsem měl, takže není to tak hezké, jak bych chtěl aby bylo.*
