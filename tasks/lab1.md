# Lab #1

	marco patrignani		marco.patrignani@unitn.it

1: Language and IDE setup
----------------------------------------------------------
In caso non l'abbiate fatto a casa in precedenza, seguite 
le istruzioni per installare Java, JavaFX e IntelliJ Idea 
sulla vostra macchina.

1.1: Project Templates
----------------------------------------------------------
Create un nuovo progetto Java, e runnatene il main.Main.
Create un nuovo progetto JavaFX e runnatene il main.Main.


2: Lab 
----------------------------------------------------------
Il progetto che svilupperemo in laboratorio prevede di
codificare un semplice gioco inspirato da Minecraft, dove
si posano blocchi, si minano blocchi, etc.

 ****** ****** ****** ****** ****** ****** ****** ******
I prossimi laboratori estendono quanto farete in questo.
Se non finite tutto durante il laboratorio, si consiglia
di finire il tutto entro il prossimo lab. 
Usate piazza per chiedere consigli e discutere di cose
che non vi tornano. 

2.1: Struttura
----------------------------------------------------------
Create un nuovo progetto.
Al suo interno, create due packages:
 - main.Main: qui dentro mettete la classe main.Main col metodo main
 - data: qui dentro mettete le classi specifiche che 
 	andate a creare oggi

2.2: Block Class
----------------------------------------------------------
Create una classe Block che contiene
 - un campo contenuto di tipo char
 - un costruttore che inizializza il contenuto ad un 
 	valore di default (per es: '.' )
 - un costruttore che inizializza il contenuto ad un
 	valore dato in input
 - un metodo display che ritorna il contenuto

Domande:
- che modificatore date a contenuto? perche`?
- il valore di default come viene scelto?

2.3: Map Class
----------------------------------------------------------
Create una classe Map che contiene:
 - una matrice di Block (i.e.: Block[][])
 - un costruttore che crea una mappa di soli blocchi
 	default. Ricordatevi di inizializzare il contenuto 
 	della matrice, non solo la matrice. Non basta fare:
 		Block[][] contenuto = new Block[][]; 
 - un metodo display_on_out che stampa la matrice per 
 	visualizzarne i contenuti
 - un metodo change_cell che prende 2 interi e cambia la
 	cella individuata dalle coordinate con un blocco non
 	di default (per es: 'A')

Domande:
- di che dimensioni create la mappa? 
- per quale motivo non basta l'inizializzazione scritta
	sopra?
- come definite quelle dimensioni? Sono costanti?
- cosa succede se i valori di change_cell eccedono queste
	costanti? Cosa fate in quel caso?

2.4: main.Main
----------------------------------------------------------
Create una sotto-procedura del main che
 - crea una mappa default
 - all'interno di un ciclo for, usate questo codice per 
 	leggere input utente e cambiare delle celle.
 	Aggiungete il codice per stampare la mappa ogni volta.
 	Ragionate quante iterazioni del for fare.

```java
System.out.print("Enter row: ");
Scanner myObj = new Scanner(System.in);
int row = myObj.nextInt();

System.out.print("Enter column: ");
int col = myObj.nextInt();

System.out.println("Changing: "+row+" - "+col);
m.change_cell(row,col);
```


3: Estensioni
----------------------------------------------------------
Ora aggiungiamo la gravita` alla nostra matrice: alcuni 
blocchi, quando inseriti, cadono verso la riga piu bassa.

3.1: Estendere Block
----------------------------------------------------------
Aggiungete 2 campi a Block:
 - falls_with_gravity, un booleano
 - fall_through, un booleano

Modificate il costruttore del default con valori di 
default per questi due nuovi campi: i blocchi di default
non cadono con la gravita` (falls_with_gravity = false) e
i blocchi ci cadono attraverso (fall_through = true).

Modificate il costruttore non-default che crei blocchi
che cadono con la gravita`, e attraverso i quali i blocchi
non cadano.

Create due 'getter' per i nuovi campi.
https://www.geeksforgeeks.org/getter-and-setter-in-java/

3.2: Estendere Map
----------------------------------------------------------
Aggiungete un metodo swap a Map che prende due coordinate
e scambia il blocco a quelle coordinate con il blocco di
sotto.

Domanda: come lo testate? Se tutta la mappa contiene solo
	blocchi default, non lo si vede.
	Cambiate il main.Main per inserire un blocco non default,
	stampare la mappa, e poi chiamare swap per 
	verificarne la correttezza.
Domanda: cosa fare se le coordinate sono errate? O se
	sono quelle di un blocco nell'ultima riga? 
	Dovete aggiungere dei controlli (sanity checks).

Aggiungete un metodo insert_at_coords a Map per inserire 
un Block a delle coordinate specifiche. 
Una volta inserito il blocco, verificate se il blocco 
cade secondo la gravita` o no.
Per far cadere il blocco, utilizzate il metodo swap con
le coordinate del blocco appena inserito.

Domanda: come potete rendere questo codice piu modulare?
	Potete fare un metodo che fa inserire un blocco
	in cima ad una colonna e riutilizzare il metodo fatto
	qui sopra?
Domanda: una volta che chiamate swap da questo metodo,
	possiamo rendere swap 'private'. Potete eliminare
	i controlli (sanity checks) sulle coordinate? 
	Ha senso? 

Che soluzione sviluppare? Si puo affrontare questo 
problema in tanti modi diversi, ma sostanzialmente 
suddivisibili in due categorie: ricorsivamente e
iterativamente. Fatele entrambe.

3.2.1: Una Soluzione Ricorsiva
----------------------------------------------------------
Aggiungete un metodo insert_rec a Map per inserire un 
Block a delle coordinate specifiche.
Questo metodo deve essere ricorsivo, quindi deve chiamare 
se stesso. Concettualmente, un metodo (o una funzione) 
ricorsivo chiama se stesso a meno che una condizione 
(detta base) sia incontrata.
[ pensate al fattoriale. Nel fattoriale, la condizione 
base si ha con input == 0, in quel caso il fattoriale 
ritorna direttamente 1, e non fa la chiamata ricorsiva a 
se stesso ]
In questo metodo abbiamo diverse condizioni base:
- se inseriamo il blocco nell'ultima riga
- se il blocco inserito non ha falls_with_gravity == true
- se il blocco sotto al blocco inserito non ha 
	fall_through == true
In questi casi, il metodo ritorna.

Altrimenti, si ha il caso ricorsivo.
Il metodo chiama swap con la cella di sotto e fa la 
chiamata ricorsiva con le nuove coordinate.

3.2.2: Una Soluzione Iterativa
----------------------------------------------------------
Aggiungete un metodo insert_iter a Map per inserire un 
Block a delle coordinate specifiche.
Questo metodo deve essere iterativo, quindi deve usare un 
ciclo for o un ciclo while.
Si consiglia di provare con il ciclo la cui condizione
vi appare 'personalmente' piu facile, e di provare anche 
l'altro una volta completato il primo.
A seconda della 'metrica' di iterazione, un tipo di ciclo
potrebbe essere piu semplice di altri.
Alcuni esempi di metrica di iterazione:
- si tiene un indice che identifica il blocco corrente,
	nella colonna di inserimento, e si aumenta l'indice
	fino a raggiungere la dimensione massima della colonna
	(attenti agli 'off by 1' errors)
- fintanto che il blocco sottostante ha 
	fall_through == true e il blocco corrente ha 
	falls_with_gravity == true
	Se scrivete un while con piu clausole collegate da
	un operatore logico tipo and-&& o or-||, ricordatevi
	dello short-circuit: l'ordine degli operatori conta!
	https://www.geeksforgeeks.org/short-circuit-logical-operators-in-java-with-examples/

Potrebbero tornare utili comandi come break, per 
interrompere un ciclo in caso non sia piu necessario farlo
(per es, se avete raggiunto un blocco che ha 
fall_through == false).

3.3: Cambiamo il main.Main
----------------------------------------------------------
Cambiate il main in modo che chieda le coordinate dove 
inserire un blocco e che lo faccia cadere secondo la
gravita`.


4: Go wild
----------------------------------------------------------
Sperimentate.
Provate a fare metodi che inizializzano la mappa con 
blocchi generati casualmente 
https://www.educative.io/answers/how-to-generate-random-numbers-in-java

