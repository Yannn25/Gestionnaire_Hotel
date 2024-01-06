package src;

import java.io.Serializable;
import java.util.HashMap;

public class Facture implements Serializable {

    private double total;

    protected HashMap<Action, Integer> detail;

    public Facture() {
        this.total = 0.0;
        this.detail = new HashMap<>();
    }

    public double getTotal() {
        return total;
    }

    public String toString() {
        calculFacture();
        return " s'élève à "+this.total+"€";
    }

    public String detailFacture() {
        String res = "";
        for(Action a : detail.keySet()) {
            if(a instanceof Reservation){
                Reservation r = (Reservation) a;
                res += "Reservation du "+r.getArrivee()+" au "+r.getDepart()
                +" : "+detail.get(r)+"€";
            }
            if(a instanceof Commande) {
                res += "Commande ";
            }
            if(a instanceof Menage) {
                res += "Menage du ";
            }
        }
        return res;
    }

    public void supReservation(int id) {
        for(Action a : detail.keySet()) {
            if(a instanceof Reservation){
                detail.remove(a);
            }
        }
    }

    public void calculFacture() {
        this.total = 0.0;
        for(Action a : detail.keySet()) {
            total += detail.get(a);
        }
    }
}
