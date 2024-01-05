package src.test;

import src.*;
import src.Enum.*;
import src.erreurs.*;


/*
    Cette classe nous permet de réaliser des test d'intégration sur les 5 principales fonctionnalités demandé
    dans le sujet
 */
public class TestMain {

    public static void main(String[] args) {
        //1. Afficher les détails des chambres.
        GestionnaireHotel hotel = new GestionnaireHotel("Hotel Test");

        Chambre a = new Chambre(Place.Double, Type.Luxe,800);
        Chambre b = new Chambre(Place.Simple, Type.Normale,130);
        Chambre c = new Chambre(Place.Double, Type.Normale,200);
        Chambre d = new Chambre(Place.Simple, Type.Luxe,650);

        hotel.addChambre(a);hotel.addChambre(b);hotel.addChambre(c); hotel.addChambre(d);
        hotel.printChambres();

        //2;3 Afficher la disponibilité des chambres/ Reserver Modifier Annuler Supprimer
        Client p1 = new Client("Pierre", "Paul"); Client p2 = new Client("Amadou", "Bakary");

        hotel.Reserver(new Reservation(p1,"15/01/2024","30/01/2024"), a);
        hotel.Reserver(new Reservation(p2,"04/01/2024","02/02/2024"), b);
        hotel.Reserver(new Reservation(p2,"15/02/2024","28/02/2024"), a);
        hotel.AfficheOccupe();
        hotel.AfficheDisponibilite();
        System.out.println("\n-------------\nModification de la reservation n°1");
        hotel.Modifier(1,"15/01/2024", "20/01/2024");
        hotel.AfficheOccupe();
        System.out.println("\n-------------\nAnnulation de la reservation n°1 et suppresion de la reservation n°2");
        //hotel.Annuler(1);
        //hotel.Supprimer(2);
        hotel.AfficheOccupe();


        //4.Commander un repas


        //5. Facture du Client
        System.out.println(p1.getFacture().toString());
        p1.getFacture().detailFacture();
    }

}