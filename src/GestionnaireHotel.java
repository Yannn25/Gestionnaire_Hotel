package src;

import src.erreurs.ContenuException;
import src.erreurs.DateException;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GestionnaireHotel implements Serializable {

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

    public void afficheClients() {
        for (Client c : getClients()) {
            System.out.println(c);
        }
    }

    public void AllFacture(){
        double total = 0.0;
        for (Client c : getClients()) {
            System.out.println(c.ClientMontantFacture());
            total += c.getFacture().getTotal();
        }
        System.out.println("L'ensemble des factures de l'établisement "+ this.NomHotel +" s'élève à " + total+"€");
    }

    private boolean allEmpty() {
        for (Chambre c : chambres) {
            if(!c.getOccupations().isEmpty())
                return false;
        }
        return true;
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
        if(allEmpty()){
            System.out.println("Aucune réservation dans l'hotel pour le moment !");
        }
        for(Chambre c : chambres) {
            if(!c.getOccupations().isEmpty())
                System.out.println(c.DateOccupe());
        }
    }

    public Client clientContenu(String p, String n) {
        for (Client c : getClients()) {
            if(c.getPrenom() == p && c.getNom() == n)
                return c;
        }
        return null;
    }


    public Chambre getChambreById(int id) {
        for (Chambre c : chambres) {
            if(c.getIdChambre() == id)
                return c;
        }
        return null;
    }

    public boolean containsReservation(int numR) {
        for (Chambre c : chambres) {
            if(containsReservation(numR))
                return true;
        }
        return false;
    }

    public Client getClientById(int id) {
        return getClients().stream().filter(c -> c.getIdClient() == id).findFirst().get();
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
            r.getClient().getFacture().detail.put(r, r.nbNuits() * c.getPrix());
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
                    chambre.getReservationById(numR).getClient().getFacture().detail.put(chambre.getReservationById(numR),chambre.getReservationById(numR).nbNuits() * chambre.getPrix());
                } catch (DateException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
        if (!flag) {
            try {
                throw new ContenuException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
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
                throw new ContenuException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
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
                chambre.getReservationById(numR).getClient().getFacture().supReservation(numR);
                chambre.supReservation(numR);
                break;
            }
        }
        if (!flag) {
            try {
                throw new ContenuException("Il semblerait qu'aucune réservation ne corresponde à votre demande");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
