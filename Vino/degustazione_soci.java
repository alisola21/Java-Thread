package Vino;

//  MAIN: creo alcuni Thread bevitore per iniziare la degustazione
public class degustazione_soci {

    public static void main(String[] args) {
        System.out.println("---- BENVENUTI ALLA PRIMA DEGUSTAZIONE DELLA FEDERAZIONE ITALIANA SOMMELIER DEDICATA AI SOCI ---");
        System.out.println(" Diamo inizio alla degustazione ");
        System.out.println(" ");
        System.out.println(" ");

        // creo una nuova botte
        botte rubinetto = new botte();

        // creo 10 oggetti bevitore, ognuno con un nome e un rubinetto da cui bere
        bevitore socio1 = new bevitore("Mario", rubinetto);
        bevitore socio2 = new bevitore("Giorgio", rubinetto);
        bevitore socio3 = new bevitore("Carla", rubinetto);
        bevitore socio4 = new bevitore("Giorgia", rubinetto);
        bevitore socio5 = new bevitore("Alice", rubinetto);
        bevitore socio6 = new bevitore("Mirko", rubinetto);
        bevitore socio7 = new bevitore("Ercole", rubinetto);
        bevitore socio8 = new bevitore("Francesco", rubinetto);
        bevitore socio9 = new bevitore("Giovanna", rubinetto);
        bevitore socio10 = new bevitore("Lorenzo", rubinetto);
    }
}
