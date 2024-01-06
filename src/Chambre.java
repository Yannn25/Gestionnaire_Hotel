package src;

import src.Enum.*;
import src.erreurs.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Chambre implements Serializable {

    private static int count = 0;
    protected Place place;
    protected Type type;
    private int prix;//Prix pour une nuit
    private final int idChambre;
    private List<Reservation> occupations;


    public Chambre (Place place, Type type, int prix) {
        this.place = place;
        this.type = type;
        this.prix = prix;
        this.idChambre = ++count;
        this.occupations = new ArrayList<>();
    }

    public int getPrix() {
        return this.prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdChambre() {
        return this.idChambre;
    }

    public List<Reservation> getOccupations() {
        return occupations;
    }
    public boolean containsReservation(int r) {
        for(Reservation res : this.occupations) {
            if(res.getIdReservation() == r)
                return true;
        }
        return false;
    }
    public Reservation getReservationById(int id) {
        for(Reservation r : this.occupations) {
            if(r.getIdReservation() == id)
                return r;
        }
        return null;
    }

    public boolean verifOccupation(String deb, String fin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateDeb = LocalDate.parse(deb, formatter);
        LocalDate dateFin = LocalDate.parse(fin, formatter);

        for (Reservation reservation : occupations) {
            LocalDate arvRes = LocalDate.parse(reservation.getArrivee(), formatter);
            LocalDate depRes = LocalDate.parse(reservation.getDepart(), formatter);
            if ((arvRes.isBefore(dateFin) || arvRes.isEqual(dateFin)) &&
                    (depRes.isAfter(dateDeb) || depRes.isEqual(dateDeb))) {
                return true; // La chambre est occupée à l'une des dates spécifiées
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "Voici la chambre n°"+this.idChambre+ ", elle est de type "+this.type+" avec un lit " + this.place +
                " coutant "+this.prix+"€ la nuit.";
    }

    public String DateDispo() {
        StringBuilder res = new StringBuilder("La chambre n°" + this.idChambre + " est disponible aux dates suivantes :\n");
        LocalDate ojrd = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Ajouter l'intervalle depuis le début jusqu'à la première réservation
        if (!occupations.isEmpty()) {
            LocalDate dateDebutPremiereResa = LocalDate.parse(occupations.get(0).getArrivee(), formatter);
            res.append(ojrd.format(formatter)).append(" au ").append(dateDebutPremiereResa.minusDays(1).format(formatter)).append("\n");
        }
        // Ajouter les intervalles entre les réservations
        for (int i = 0; i < occupations.size() - 1; i++) {
            LocalDate dateFinResaActuelle = LocalDate.parse(occupations.get(i).getDepart(), formatter);
            LocalDate dateDebutResaSuivante = LocalDate.parse(occupations.get(i + 1).getArrivee(), formatter);
            res.append(dateFinResaActuelle.plusDays(1).format(formatter)).append(" au ").append(dateDebutResaSuivante.minusDays(1).format(formatter)).append("\n");
        }
        // Ajouter l'intervalle depuis la dernière réservation jusqu'à la fin du mois
        if (!occupations.isEmpty()) {
            LocalDate dateFinDerniereResa = LocalDate.parse(occupations.get(occupations.size() - 1).getDepart(), formatter);
            res.append(dateFinDerniereResa.plusDays(1).format(formatter)).append(" au ").append(dateFinDerniereResa.plusMonths(1).withDayOfMonth(1).minusDays(1).format(formatter)).append("\n");
        } else {
            // Si la chambre n'a aucune réservation ajouter l'intervalle complet du mois
            res.append(ojrd.format(formatter)).append(" au ").append(ojrd.plusMonths(1).withDayOfMonth(1).minusDays(1).format(formatter)).append("\n");
        }
        return res.toString();
    }


    public String DateOccupe() {
        String res = "La chambre n°"+this.idChambre+" est reserver du ";
        for(Reservation r : occupations) {
            String dateDebut = r.getArrivee();
            String dateFin = r.getDepart();
            String valide = r.isValide() ? "" : " (Annuler pour le moment)";
            res += dateDebut + " au " + dateFin + valide + "; ";
        }
        return res;
    }

    public void addReservation(Reservation r) throws DateException {
        LocalDate ojrd = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate arv = LocalDate.parse(r.getArrivee(), formatter);
        LocalDate dep = LocalDate.parse(r.getDepart(), formatter);

        if (arv.isBefore(ojrd)) {
            throw new DateException("La date arriver doit être a la date du jour ou après la date du jour.");
        }
        if (dep.isBefore(arv)) {
            throw new DateException("La date de départ doit être postérieure à la date d'arrivée.");
        }
        if(verifOccupation(r.getArrivee(),r.getDepart())) {
            throw new DateException("La chambre est occuper a l'une des dates selectiones ");
        }
        this.occupations.add(r);
    }

    public void  modifReservation(int numR, String newDeb, String newFin) throws DateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate arv = LocalDate.parse(newDeb, formatter);
        LocalDate dep = LocalDate.parse(newFin, formatter);
        if (dep.isBefore(arv)) {
            throw new DateException("La date de départ doit être postérieure à la date d'arrivée.");
        }

        for(Reservation r : this.occupations) {
            if(r.getIdReservation() == numR) {
                r.setArrivee(newDeb);
                r.setDepart(newFin);
            }
        }
    }

    public void supReservation(int id) {
        occupations.removeIf(reservation -> reservation.getIdReservation() == id);
    }


}
