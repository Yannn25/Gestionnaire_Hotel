package src;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/*
    Fait référence a la réservation d'une chambre
 */
public class Reservation extends Action {

    protected static int count = 0;
    private String Arrivee;
    private String Depart;
    private final int idReservation;

    public Reservation(Client c, String arv, String dep) {
        super(c);
        this.Arrivee = arv;
        this.Depart = dep;
        this.idReservation = ++count;
    }

    public String getArrivee() {
        return Arrivee;
    }
    public void setArrivee(String arrivee) {
        Arrivee = arrivee;
    }

    public String getDepart() {
        return Depart;
    }
    public void setDepart(String depart) {
        Depart = depart;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public int nbNuits(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate arv = LocalDate.parse(this.Arrivee, formatter);
        LocalDate dep = LocalDate.parse(this.Depart, formatter);
        Period periode = Period.between(arv, dep);
        return periode.getDays()-1;// -1 Car c'est bien le nombre de NUIT que l'on souhaite
    }
}
