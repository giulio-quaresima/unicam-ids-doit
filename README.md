# DoIt

## Introduzione
Progetto di laboratorio di Ingegneria del Software, Università di Camerino, A.A. 2020/21.

## Descrizione del progetto
Il progetto è un semplice abbozzo di un gestore di progetti, dove enti
propongono la realizzazione di progetti e "progettisti", che possono essere
a loro volta enti o progettisti individuali, si candidano alla sua realizzazione.

## Getting started
Il progetto, per essere compilato ed eseguito, necessita della presenza nel proprio ambiente
di una installazione di [Maven](https://maven.apache.org/) (anche solo integrato in un IDE come Eclipse) 
e di una installazione di Angular (vedi https://angular.io/guide/setup-local).

Il sorgente è diviso in due parti, backend e frontend. Una volta scaricato in locale, 
andare nella cartella `unicam-ids-doit-backend` ed eseguire il comando
```
mvn spring-boot:run
```
andare quindi nella cartella `unicam-ids-doit-frontend` ed eseguire il comando
```
ng serve
```
(potrebbe essere necessario prima eseguire `npm install` nella stessa cartella).

**ATTENZIONE!!!** A causa del bug #1 le versioni 0.3, 0.4 e 0.5 non compilano correttamente. Una volta scaricate tale versioni, applicare le medesime correzioni applicate con la commit bd4542f38576f4f15fa8090c98ea24067310405d (ovviamente con la relativa versione).

A questo punto la webapp dovrebbe essere disponibile all'indirizzo http://localhost:4200

L'applicazione parte con alcuni dati precaricati tramite [Liquibase](https://www.liquibase.org/),
e allo stato attuale permette la creazione di nuovi progetti (per gli utenti che ne hanno il permesso), 
la candidatura a progetti, e l'invio e la ricezione di inviti nell'ambito di una candidatura,
che in questo modo diviene una cordata di più progettisti.

L'autenticazione è simulata per il _rapid prototyping_, l'utente viene semplicemente
selezionato da una tendina ed è _statico_: a livello di backend può esistere un solo
utente autenticato per volta; questo per permettere il rapido test del comportamento
dell'applicazione con i diversi utenti coinvolti.

Il DB è _in memory_, quindi ad ogni riavvio qualsiasi dato inserito viene eliminato
e si riparte dai dati iniziali precaricati.
