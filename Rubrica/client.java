package Rubrica;

import java.net.*;
import java.io.*;

public class client extends Thread {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Inizializzazione del client in corso...");

            //Creo socket (127.0.0.1 = localhost / 8080 = porta di connessione tra client e server)
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Il client è stato correttamente inizializzato");

            //stream per operazioni di Input/output
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            //buffer per la lettura di input da tastiera
            BufferedReader stdIn = new BufferedReader((new InputStreamReader(System.in)));
            BufferedReader input = new BufferedReader(isr);

            //gestione output
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter output = new PrintWriter(bw, true);//????

            String richiestaUtente; //richiesta dell'utente ricevuta dal client

            while (true) { //loop infinito

                //menu' che indica le possibili azioni che l'utente puo' compiere
                System.out.println(" ");
                System.out.println("Benvenut* nella rubrica.\n Scegli una tra le seguenti operazioni: ");
                System.out.println("1 - Inserire un contatto all'interno della rubrica");
                System.out.println("2 - Ricercare un contatto all'interno della rubrica");
                System.out.println("0 - Uscita");
                System.out.println(" ");

                //Leggo l'input dall'utente
                richiestaUtente = stdIn.readLine();
                //definisco due variabili di tipo stringa, nome e cognome
                String nome;
                String cognome;

                //se l'utente digita 0, esce dalla rubrica
                if (richiestaUtente.equals("0")) {
                    break;
                    // se l'utente digita 1, invia il nome e il cognome al server che poi lo salvera' nella rubrica come contatto

                } else if (richiestaUtente.equals("1")) {
                    output.println(richiestaUtente); //invio richiesta
                    System.out.println("Inserisci il nome della persona: ");
                    nome = stdIn.readLine();
                    System.out.println("Inserisci il cognome della persona: ");
                    cognome = stdIn.readLine();
                    // creo una stringa contatto composta dal nome e dal cognome che poi invierò al server
                    String contatto = nome + " " + cognome;
                    //invio il nome e il cognome da inserire al server
                    System.out.println("Invio contatto server");
                    output.println(contatto);
                    System.out.println(input.readLine());

                    // se l'utente digita '2' invia il congome al server che lo ricercherà all'interno della rubrica
                } else if (richiestaUtente.equals("2")) {
                    output.println(richiestaUtente); //invio richiesta
                    System.out.println("Inserisci il cognome della persona: ");
                    cognome = stdIn.readLine();
                    //invio il cognome da ricercare al server
                    System.out.println("Invio cognome al server...");
                    output.println(cognome);
                    System.out.println(input.readLine());
                    System.out.println(input.readLine());
                    System.out.println(input.readLine());
                    System.out.println(input.readLine());
                }

            }

            stdIn.close();
            socket.close();
            output.close();
            input.close();

        } catch (UnknownHostException e) {
            System.out.println("Host non trovato, connessione fallita...");
        } catch (IOException e) {
            System.err.println("Non riesco a effettuare le operazioni di I/O");
        }
        System.out.println("Chiusura del client e uscita dalla rubrica ");
    }
}