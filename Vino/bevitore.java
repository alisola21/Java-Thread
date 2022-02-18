package Vino;

public class bevitore extends Thread {
    private final botte rubinetto; //  Rubinetto = oggetto della classe botte che sarà conteso tra tutti i Thread
    public String nome; // nome del bevitore

    // Definisco il Thread socio bevitore composto da un nome e un rubinetto.
    public bevitore(String nome, botte rubinetto) {
        this.nome = nome;
        this.rubinetto = rubinetto;
        start(); // avvio il Thread
    }

    // Il thread socio bevitore attende/'dorme' per 1200 millisecondi e poi può iniziare la degustazione al rubinetto (definita nella classe botte)
    public  void run() {
        while (rubinetto.litri_vino > 0 && rubinetto.rubinetti_degustazione > 0) {//finch� nella botte c'� vino viene fatto questo ciclo
                try {
                    sleep(1200);
                    rubinetto.degustazione();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //metodo che restituisce il nome del socio
        public String getNomeSocio () {
            return this.nome;
        }

    }