package Rubrica;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class server {

    // creo un array list di tipo stringa contenente i contatti in rubrica
    public static ArrayList<String> rubrica = new ArrayList<>();

    //porta di connessione tra client e server
    public static final int PORT = 8080;

    //sottoclasse serverThread che estende Thread ed effettua l'overriding del metodo di Thread run()
    public static class server_Thread extends Thread {
        // definisco il lettore Buffered Reader per leggere le richieste del client e l'output writer per restituire il risultato
        BufferedReader inputClient = null;
        PrintWriter outputServer = null;
        // definisco la socket
        Socket socket;

        //costruttore che assegna al Socket s il socket del client passato dal main
        public server_Thread(Socket s) {
            //socket del client
            socket = s;
        }

        public void run() {
            try {
                //lettura dell'input
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                inputClient = new BufferedReader(isr);
                // creazione stream di output su clientSocket
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                BufferedWriter bw = new BufferedWriter(osw);
                outputServer = new PrintWriter(bw, true);

                while (true) { //loop infinito
                    //leggo la stringa dell'utente
                    String richiestaClient = inputClient.readLine();
                    //definisco la stringa contatto composta dal nome e cognome da inserire o ricercare in rubrica
                    String contatto;
                    // se l'utente digita 0, esco dalla rubrica -> OK
                    if (richiestaClient.equals("0")) {
                        break;
                    }
                    // se l'utente digita 1, il server il contatto (nome + cognome) e lo salva nella rubrica -> OK
                    else if (richiestaClient.equals("1")) {
                        contatto = inputClient.readLine();
                        rubrica.add(contatto);
                        outputServer.println("Il contatto " + contatto + " e' stato inserito");
                        outputServer.println(" ");
                    } else if (richiestaClient.equals("2")) {
                        boolean contattoInRubrica = false;
                        contatto = inputClient.readLine();
                        outputServer.println("Ricerco " + contatto + " nella rubrica");
                        for (String s : rubrica) {
                            if (s.contains(contatto)) {
                                contattoInRubrica = true;
                            }
                        }
                        if (contattoInRubrica) {
                            outputServer.println("Il contatto e' presente in rubrica: " + contatto);
                        } else {
                            outputServer.println("Il contatto non e' in rubrica");
                        }
                        outputServer.println(" ");
                    }
                }

                outputServer.close();
                inputClient.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //main in cui stabilisco la connessione tra client e server
    public static void main(String[] args) throws IOException {
        //inizializzo una nuova socket del server passando la porta 8080 in comune tra Client e Server
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientSocket = null;

        System.out.println("Avvio del server \n Server Socket: " + serverSocket);
        try {
            while (true) {
                //accetto la connessione entrante dal client
                clientSocket = serverSocket.accept();
                System.out.println("E' stata accettata la connessione con il Server : " + clientSocket);
                //nuova istanza del server che gestirà le connessioni dal client
                server_Thread threadServer = new server_Thread(clientSocket);
                threadServer.start();
            }
        } catch (IOException e) {
            System.err.println("Non è possibile inviare messaggi tra Client e Server");
        }
    }
}