package src;

import src.erreurs.ContenuException;
import src.erreurs.DateException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GestionnaireHotel {

    protected String NomHotel;
    private List<Chambre> chambres;


    public GestionnaireHotel(String name) {
        this.NomHotel = name;
        this.chambres = new LinkedList<>();
    }


    public String getNomHotel() {
        return NomHotel;
    }
    public void setNomHotel(String nomHotel) {
        NomHotel = nomHotel;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void addChambre(Chambre c) {
        if(chambres.contains(c))
            return;
        chambres.add(c);
    }

    public List<Client> getClients() {
        List<Client> ret = new LinkedList<>();
        for(Chambre c : chambres) {
            for(Reservation r : c.getOccupations())
                ret.add(r.getClient());
        }
        return ret;
    }

    public void printChambres() {
        System.out.println("L'ensemble des chambres de " + this.NomHotel +" sont :");
        for(Chambre c : chambres) {
            System.out.println(c.toString());
        }
    }

    public void AfficheDisponibilite() {
        for(Chambre c : chambres) {
            System.out.println(c.DateDispo());
        }
    }
    public void AfficheOccupe() {
        for(Chambre c : chambres) {
            if(!c.getOccupations().isEmpty())
                System.out.println(c.DateOccupe());
        }
    }

    public void Reserver(Reservation r, Chambre c)  {
        if(!chambres.contains(c)) {
            try {
                throw new ContenuException("La chambre saisie n'appartient pas à cet hotel !");
            } catch (ContenuException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            c.addReservation(r);
        } catch (DateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Modifier(int numR, String newDeb, String newDep) {
        boolean flag = false;

        for (Chambre chambre : this.chambres) {
            if (chambre.containsReservation(numR)) {
                flag = true;
                try {
                    chambre.modifReservation(numR, newDeb, newDep);
                } catch (DateException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
        if (!flag) {
            try {
                throw new RuntimeException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Annuler(int numR){
        boolean flag = false;

        for (Chambre chambre : this.chambres) {
            if (chambre.containsReservation(numR)) {
                flag = true;
                chambre.getReservationById(numR).setValide(false);
                break;
            }
        }
        if (!flag) {
            try {
                throw new RuntimeException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Supprimer(int numR) {
        boolean flag = false;
        for (Chambre chambre : this.chambres) {
            if (chambre.containsReservation(numR)) {
                flag = true;
                chambre.getReservationById(numR).setValide(false);
                chambre.supReservation(numR);
                break;
            }
        }
        if (!flag) {
            try {
                throw new RuntimeException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
