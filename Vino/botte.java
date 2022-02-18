package Vino;

/* CLASSE BOTTE:
    * si stabiliscono le caratteristiche della botte: litri di vino contenuti (500L) e quantità di rubinetti destinati alla degustazione (5)
    * Si procede con la degustazione utilizzando il metodo inizia degustazione:
        il socio va a bere, beve la quantità di vino che desidera (numero random compreso tra 1 e 20)
*/

public class botte {
    public int litri_vino; // var che rappresenta la quantità di vino
    public int rubinetti_degustazione; // posti liberi per la degustazione

    // costruttore botte che inizializza le variabili della classe botte (che contiene 500 litri ed è composta da cinque rubinetti)
    public botte() {
        litri_vino = 500;
        rubinetti_degustazione = 5;
    }

    /* metodo con cui viene gestita la degustazione del vino contenuto nella botte da parte dei soci:
        dato che questo metodo viene utilizzato da tutti i Thread (i bevitori) è
        necessario sincronizzare il metodo per evitare situazioni critiche */
    public synchronized void degustazione() {
        bevitore bevitore = (bevitore) Thread.currentThread();

        // se la botte è vuota e non ci sono più posti i thread devono attendere
        while ((rubinetti_degustazione == 0) && (litri_vino == 0)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //se c'è vino all'interno della botte e ci sono posti disponibili
        if (litri_vino > 0 && rubinetti_degustazione > 0) {
            //il socio va a bere
            System.out.println("Il socio " + bevitore.getNomeSocio() + " va a bere");
            rubinetti_degustazione--;
            // la quantità di vino bevuta da un socio è random tra 1 e 20.
            int vino_bevuto = (int) (Math.random() * 20);
            //se la quantità di vino bevuta dal socio è superiore a quella contenuta nella botte
            if (vino_bevuto > litri_vino) {
                litri_vino = 0; // il socio beve solo la quantità rimasta nella botte
            } else {//altrimenti berra' la quantità che desidera (quantità random tra 1 e 20)
                litri_vino -= vino_bevuto;
            }
            //restituisco la quantità di vino bevuta dal socio
            System.out.println("La quantita' di vino diminuisce. Sono rimasti " + this.litri_vino + " litri di vino");

            //il socio termina la sua degustazione ed esce
            System.out.println("Il socio " + bevitore.getNomeSocio() + " ha finito di bere e se ne va");
            System.out.println(" ");
            rubinetti_degustazione ++;
            notifyAll();
            //se il vino termina, termina anche la degustazione
            if (this.litri_vino == 0) {
                System.out.println("Il vino e' terminato, la degustazione e' finita! " +
                        "\n La Federazione Italiana Sommelier ringrazia tutti i soci per la loro partecipazione!");

            }

        }
    }
}
