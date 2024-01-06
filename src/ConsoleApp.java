package src;

import src.Enum.Place;
import src.Enum.Type;
import src.erreurs.ContenuException;


import java.io.*;
import java.util.Scanner;

public class ConsoleApp implements Serializable {

    protected transient Scanner sc = new Scanner(System.in);
    protected GestionnaireHotel hotel;

    public ConsoleApp(int x) {
        if(x == 1)
            loadSerializer();
        else
            this.hotel = CreerHotel();
            load();
    }

    private GestionnaireHotel CreerHotel() {
        System.out.println("Bienvenue dans votre Gestionnaire d'Hotelerie\n Quel est le nom de votre hotel ?");
        String nameHotel = sc.next();
        GestionnaireHotel ret = new GestionnaireHotel(nameHotel);
        return ret;
    }

    private Chambre CreerChambre() {
        Type t;
        Place p;
        int prix = 0;
        System.out.println("De quel Type est votre chambre ?\n1.Normale\n2.Luxe");

        switch (sc.nextInt()) {
            case 1:
                t = Type.Normale;
                break;
            case 2:
                t = Type.Luxe;
                break;
            default:
                System.out.println("Entrée non compris recomencer...");
                return CreerChambre();
        }
        System.out.println("Combien de Place dispose votre chambre ?\n1.Simple\n2.Double");
        switch (sc.nextInt()) {
            case 1:
                p = Place.Simple;
                break;
            case 2:
                p = Place.Double;
                break;
            default:
                System.out.println("Entrée non comprise recommencer...");
                return CreerChambre();
        }
        System.out.println("Quel est sont prix à la nuit ?");
        prix = sc.nextInt();
        return new Chambre(p,t,prix);
    }
    private Client NouveauClient(GestionnaireHotel h) {
        String p = "", n = "";
        System.out.print("Entrer le prenom du client : ");
        p = sc.next();
        System.out.print("\nEntrer le nom du client : ");
        n = sc.next();
        if(h.clientContenu(p, n) != null){
            return h.clientContenu(p, n);
        }
        return new Client(p,n);
    }
    private Reservation NouvelleReservation(Client c) {
        String deb = "", fin = "";
        System.out.print("Entrer la date d'arriver (au format J/M/A) : ");
        deb = sc.next();
        System.out.print("Entrer la date de départ (au format J/M/A) : ");
        fin = sc.next();
        return new Reservation(c,deb,fin);
    }

    private int RecupChambreById() {
        System.out.println("Quel est le numéro de chambre voulus?");
        int num = sc.nextInt();
        return num;
    }

    public void GestionReservation(GestionnaireHotel h) {
        System.out.println("Quelle opération voulez vous réaliser ?");
        System.out.println("1.Réserver une chambre\n2.Modifier une réservation\n3.Annuler une réservation\n4.Supprimer une réservation");
        switch (sc.nextInt()) {
            case 1 :
                Client client = NouveauClient(h);
                System.out.println("Pour le moment voici les chambres libres :");
                h.AfficheDisponibilite();
                int numChambre = RecupChambreById();
                Reservation r = NouvelleReservation(client);
                h.Reserver(r,h.getChambreById(numChambre));
                h.AfficheOccupe();
                break;
            case 2 :
                h.AfficheOccupe();
                System.out.print("Quel est le numéro de la réservation a modifier : ");
                int numM = sc.nextInt();
                String deb = "", fin = "";
                System.out.print("Entrer la date d'arriver (au format J/M/A) : ");
                deb = sc.next();
                System.out.print("Entrer la date de départ (au format J/M/A) : ");
                fin = sc.next();
                h.Modifier(numM, deb, fin);
                h.AfficheOccupe();
                break;
            case 3 :
                h.AfficheOccupe();
                System.out.print("Quel est le numéro de la réservation a annuler : ");
                int numA = sc.nextInt();
                h.Annuler(numA);
                h.AfficheOccupe();
                break;
            case 4 :
                h.AfficheOccupe();
                System.out.print("Quel est le numéro de la réservation a supprimer : ");
                int numS = sc.nextInt();
                h.Supprimer(numS);
                h.AfficheOccupe();
                break;
        }
    }

    public void GestionFacture(GestionnaireHotel h, int n) {
        switch (n) {
            case 1 :
                h.afficheClients();
                System.out.print("Entrer l'id du client dont vous souhaitez la facture : ");
                int num = sc.nextInt();
                Client c = hotel.getClientById(num);
                if(c == null) {
                    try {
                        throw new ContenuException("Aucun client ne correspond a votre demande !");
                    } catch (ContenuException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(c.ClientDetailFacture());
                System.out.println(c.ClientMontantFacture());
                break;
            case 2:
                h.AllFacture();
                break;
        }
    }



    public void load(){

        System.out.println("Initialiser votre première chambre");
        hotel.addChambre(CreerChambre());
        hotel.printChambres();
        boolean flag = true;
        while(flag) {
            System.out.println("Que souhaitez vous faire :");
            System.out.println("0.Quitter\n1.Ajouter une chambre\n2.Afficher le details des chambres" +
                    "\n3.Afficher les disponibilités des chambres\n4.Afficher les reservations des chambres"+
                    "\n5.Gestion des reservations\n6.Commander un repas\n7.Facture d'un client\n8.Ensembles des factures");
            switch (sc.nextInt()){
                case 0 :
                    System.out.println("Au revoir bonne journée a vous !");
                    flag = false;
                    break;
                case 1 :
                    hotel.addChambre(CreerChambre());
                    hotel.printChambres();
                    break;
                case 2:
                    hotel.printChambres();
                    break;
                case 3 :
                    hotel.AfficheDisponibilite();
                    break;
                case 4 :
                    hotel.AfficheOccupe();
                    break;
                case 5 :
                    GestionReservation(hotel);
                    break;
                case 6 :
                    System.out.println("En cours ...");
                    break;
                case 7 :
                    GestionFacture(hotel, 1);
                    break;
                case 8 :
                    GestionFacture(hotel, 2);
                    break;
                default :
                    System.out.println("Entrée non comprise recommencer...");
                    break;
            }
        }
        saveToFile("serializedConsoleApp.ser");
        System.exit(0);
    }

    public void loadSerializer() {
        ConsoleApp serializedApp = loadFromFile("serializedConsoleApp.ser");
        if (serializedApp != null) {
            boolean flag = true;
            while(flag) {
                System.out.println("Que souhaitez vous faire :");
                System.out.println("0.Quitter\n1.Ajouter une chambre\n2.Afficher le details des chambres" +
                        "\n3.Afficher les disponibilités des chambres\n4.Afficher les reservations des chambres"+
                        "\n5.Gestion des reservations\n6.Commander un repas\n7.Facture d'un client\n8.Ensembles des factures");
                switch (sc.nextInt()){
                    case 0 :
                        System.out.println("Au revoir bonne journée a vous !");
                        flag = false;
                        break;
                    case 1 :
                        serializedApp.hotel.addChambre(CreerChambre());
                        serializedApp.hotel.printChambres();
                        break;
                    case 2:
                        serializedApp.hotel.printChambres();
                        break;
                    case 3 :
                        serializedApp.hotel.AfficheDisponibilite();
                        break;
                    case 4 :
                        serializedApp.hotel.AfficheOccupe();
                        break;
                    case 5 :
                        GestionReservation(serializedApp.hotel);
                        break;
                    case 6 :
                        System.out.println("En cours ...");
                        break;
                    case 7 :
                        GestionFacture(serializedApp.hotel, 1);
                        break;
                    case 8 :
                        GestionFacture(serializedApp.hotel, 2);
                        break;
                    default :
                        System.out.println("Entrée non comprise recommencer...");
                        break;
                }
            }
            saveToFile("serializedConsoleApp.ser");
            System.exit(0);
        } else {
            System.out.println("Erreur lors du chargement de la partie sérialisée.");
            load();
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(this);
            System.out.println("Sauvegarde réussie !");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'objet dans le fichier", e);
        }
    }


    public static ConsoleApp loadFromFile(String fileName) {
        ConsoleApp consoleApp = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            consoleApp = (ConsoleApp) objectInputStream.readObject();
            System.out.println("Chargement réussi !\n\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return consoleApp;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Appel par défaut pour restaurer les champs sérialisés
        //
        if (this.hotel == null) {
            this.hotel = CreerHotel();
            System.out.println("Erreru lors des chargements des données de la précedente session");
            load();
        }
    }

}
