# Lab #2
    marco patrignani        marco.patrignani@unitn.it

1: Packages
----------------------------------------------------------
Visto che andiamo ad aggiungere molte classi e interfacce
al progetto, utilizziamo i package di Java per dare
struttura e facilitare la lettura del progetto stesso.

Leggete dei package qui, o googolate:
https://www.w3schools.com/java/java_packages.asp

Si consiglia un package per i blocchi, e uno per la parte
di interfaccia utente, che per ora ha solo la Map.


2: Una Semplice Interfaccia
----------------------------------------------------------
In questa estensione modificherete la struttura del 
progetto senza alterarne le funzionalita.

2.1: Interfacce
----------------------------------------------------------
Aggiungete una interfaccia Block, spostatevi i metodi
display, e i getter dei due booleani.

2.2: Rinominare di Block
----------------------------------------------------------
Per evitare confusione, cambiate nome alla vecchia classe
Block e la chiamiamo AirBlock.
Rimuovete i metodi per creare un blocco custom e lasciate 
solo i costruttori per il default.

2.3: Classi abstract
----------------------------------------------------------
Create una abstract class AbstractBlock the implementa
l'interfaccia Block.
Spostateci tutti i campi che erano in Block e i metodi
che richiede l'interfaccia.
Aggiungete un campo blockname di tipo String.
Fate override del metodo toString che stampa una 
descrizione del blocco in base al nome e ai dati che
vogliamo.

Domanda: che visibilita` devono avere i campi? E i metodi?

Domanda: ma se non ci sono metodi abstract, per quale 
	motivo dichiariamo AbstractBlock abstract?

Attenzione: quando fate questo refactoring dobbiamo
	stare attenti a quali classi vengono utilizzate in
	altre parti del codice, tipo in Map.
	Come tipo statico, usare Block, come tipo dinamico,
	non possiamo usare AbstractBlock, dovremo creare degli
	AirBlock.

2.4: Testing
----------------------------------------------------------
Testate che questo refactoring non abbia distrutto nulla
del codice che funzionava in precedenza.

Al massimo potrebbe mancare il blocco 'A' da aggiungere,
per quello, guardate di sotto e create un SandBlock 
ogni qualvolta un blocco 'A' era richiesto.


3: Nuovi Blocchi: Water and Sand
----------------------------------------------------------
Ora aggiungerete nuovi blocchi che non alterano le 
funzionalita` del progetto.

3.1: Classi nuove
----------------------------------------------------------
Aggiungete una nuova classe SandBlock che estende 
AbstractBlock.
La sabbia ha `falls_with_gravity == true` e 
`fall_through == false`;

Aggiungete una nuova classe WaterBlock che estende
AbstractBlock
L'acqua ha `falls_with_gravity == true` e 
`fall_through == true`;

Nel costruttore, settate tutti i campi che ereditate.

3.2: Aggiunte a Map
----------------------------------------------------------
Aggiungete un metodo private a Map, addRowsOfWater che 
aggiunge una riga d'acqua.
Data una mappa default, i.e. fatta solo di AirBlock, 
aggiungete l'acqua in cima ad ogni colonna, e riutilizzate 
i metodi fatti in precedenza per far cadere un WaterBlock
in base alla gravita`.

Aggiungete un metodo public addRiver che aggiunge una
sola riga di acqua, chiamando la addRowsOfWater.

Aggiungete un metodo public addSea che aggiunge tre 
righe d'acqua.

Cambiate il costruttore di Map in modo che aggiunga un
fiume d'acqua.

Testate che la Map venga creata correttamente.
Testate che l'aggiunta della sabbia funzioni come prima.
La sabbia cade sia attraverso l'aria che attraverso 
l'acqua, e si impila solo su se stessa.


4: Nuove Interfacce
----------------------------------------------------------
Ora aggiungerete una nuova interfaccia e nuove 
funzionalita`.

4.1: Interface Smeltable
----------------------------------------------------------
Definite una interfaccia SmeltableBlock con un metodo
smelt che ritorna un Block.
SmeltableBlock estende l'interfaccia Block.

4.2: Classi Nuove
----------------------------------------------------------
Create una classe GlassBlock che estende AbstractBlock.
Nel costruttore, settate tutti i campi che ereditate.
Il vetro ha `falls_with_gravity == false` e 
`fall_through == false`.

Modificate SandBlock in modo che implementa SmeltableBlock.
Il metodo smelt in SandBlock ritorna un GlassBlock.

Come il GlassBlock, tanti altri blocchi hanno i due campi 
booleani settati entrambi a false.
Per velocizzare la scrittura del codice e rendere il
progetto modulare, create una classe astratta 
AbstractSolidBlock che estende AbstractBlock.
AbstractSolidBlock ha un costruttore che setta i campi
booleani a false.
Fate si che GlassBlock estenda AbstractSolidBlock invece
che AbstractBlock e usate super(); nel costruttore.

Create una classe NullBlock che estende AbstractSolidBlock
e che implementa SmeltableBlock.
Il metodo smelt in NullBlock ritorna un NullBlock.

4.3: Furnace 
----------------------------------------------------------
Create una classe Furnace.
Furnace ha due campi: input e output, il primo ha tipo
SmeltableBlock, il secondo Block.
Il costruttore li inizializza a NullBlock.
Aggiungete tre metodi dentro a Furnace:
- display_on_out() che stampa il contenuto di input e 
	output. Per esempio:
    `System.out.println("|| "
    	+this.furnaceInput.display()
    	+" --> "
    	+this.furnaceOutput.display()
    	+" ||");`
- smelt: che mette il risultato della chiamata a 
	smelt fatta sull'input nell'output, risettando 
	l'input a NullBlock
- setInput, che prende uno SmeltableBlock, setta l'input
    a quel blocco, e setta l'output al risultato di 
    chiamare smelt sull'input

4.4: Una UI Centralizzata e Map
----------------------------------------------------------
Create una classe MainView.
Essa fungera` da wrapper per la user interface (UI) dell'
applicazione.
All'interno di MainView mettete sia una Map che una 
Furnace.
Mentre prima il main chiamava la stampa della Map, ora
il main chiama la stampa della MainView, che a sua volta
chiama sia la stampa della Map, che della Furnace.
Dovrete fare un po' di refactoring, spostando costanti tra
le varie classi.
Aggiungete il metodo
- move_into_furnace, che prende una riga e una colonna e
    se il blocco alle coordinate riga-colonna dentro la 
    Map ha tipo SmeltableBlock,
    allora quel blocco viene spostato dentro all'input
    della Fornace, nella Map viene messo un AirBlock.

Per fare questo metodo dovete aggiungere diversi metodi 
dentro a Map.
Si consiglia: 
- un metodo che ritorna un booleano se a delle 
    coordinate riga-colonna si trova un blocco di tipo 
    SmeltableBlock
- un metodo che ritorna lo SmeltableBlock che si trova 
    a delle coordinate riga-colonna.
Entrambi questi metodi devono fare cast dei Block into 
SmeltableBlock.
Fatelo senza causare errori a runtime.

Sempre dentro a Map, fate in modo che in una mappa di 
default vengano aggiunti dei blocchi in modo casuale.
Potete usare questo codice, riempiendo i buchi commentati.
```java
Random rand = new Random();
for (int i = 0 ; i < RANDOM_BLOCKS_TO_ADD; i++){
    Block b = // create un SandBlock
    int row = rand.nextInt(DIMENSION_ROWS);
    int col = rand.nextInt(DIMENSION_COLUMNS);
    // inserite b a row-col
}
```

Si utilizzi il codice seguente per testare queste 
funzionalita:
```java
MainView m = new MainGui();
m.display_on_out();
for (int i = 0 ; i < INTERACTIONS ; i++){
    System.out.print("Enter row and then column, 
    	or enter '9' and then '9' for smelting: ");
    Scanner myObj = new Scanner(System.in);
    int row = myObj.nextInt();
    int col = myObj.nextInt();
    if (row == 9 && col == 9){
        m.smelt();
    }else{
        m.move_into_furnace(row, col);
    }
    m.display_on_out();
}
```


5: Estensioni
----------------------------------------------------------
Ci sono diverse estensioni al progetto che potete fare 
per prendere confidenza con Java e la programmazione 
orientata agli oggetti.

5.1: Coordinate
----------------------------------------------------------
Molti metodi che avete definito usano due parametri di 
tipo int per identificare riga e colonna nella Map.
Create una classe Coordinate e usatela dovunque prima
usavate i due int.
Potete anche aggiungere un metodo che controlli se un
oggetto Coordinate sia in-bound rispetto alle dimensioni
della Map, a questo punto risulta centralizzato.

Domanda: dove mettere questa classe a livello di package
	del progetto?

5.2: Ferro
----------------------------------------------------------
Definite due nuove classi: RawIronBlock, che estende 
AbstractSolidBlock, e implementa SmeltableBlock,
e IronSwordBlock, che estende AbstractSolidBlock.
Il metodo smelt di RawIronBlock ritorna un IronSwordBlock.

Cambiate la creazione della mappa in modo che aggiunga
sia SandBlock che RawIronBlock in modo casuale.

5.3: Factory
----------------------------------------------------------
Un concetto che vedrete durante il corso di Ingegneria
del Software sono i Design Pattern:
https://it.wikipedia.org/wiki/Design_pattern
Ne state usando alcuni in questo progetto, ma non lo 
sapete ancora. I Design Pattern non sono oggetto d'esame,
ma per chi volesse scrivere codice piu facile da 
mantenere e da estendere, potete aggiungerne uno basilare
al progetto.

Ora pensate che altra gente possa estendere il vostro
codice. Con tutte le classi public, si potrebbe fare
una estensione che crea dei blocchi IronSwordBlock 
direttamente! 
Invece, potete usare le astrazioni di Java per impedire 
questa cosa e fare in modo che un utente possa ottenere 
un IronSwordBlock solo facendo smelt su un blocco di 
tipo RawIronBlock.

Per fare questo, vi potete appoggiare a un oggetto che 
sia il responsabile di creare le classi.
Questo oggetto si chiama factory, e gli potete dare un 
metodo per ogni Block che vogliamo creare, tipo
```java
public SandBlock sand_block(){
    return new SandBlock();
}
public WaterBlock waterBlock(){
    return new WaterBlock();
}
```
In particolare NON gli date un metodo che crei un 
IronSwordBlock, e dichiarate quella classe 
package-private.
Come rendere IronSwordBlock utilizzabile dal codice
fuori dal package, allora?
Create una interfaccia IronSwordInterface, che la classe 
IronSwordBlock implementa, e dove sia necessario il tipo
IronSwordBlock, usate l'interfaccia (che deve essere
public). Visto che le interfacce non sono istanziabili,
si puo creare un IronSwordBlock ora solo facendo smelt
su un RawIronBlock.

