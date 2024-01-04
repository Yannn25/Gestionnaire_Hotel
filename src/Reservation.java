package src;

/*
    Fait référence a la réservation d'une chambre
 */
public class Reservation extends Action {

    protected static int count = 0;
    private String Arrivee;
    private String Depart;
    protected final int idReservation;

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
}
