package src;

import java.util.HashMap;

public class Facture {

    private double total;
    protected HashMap<Action, Integer> detail;

    public Facture() {
        this.total = 0.0;
        this.detail = new HashMap<>();
    }

    public String toString() {
        calculFacture();
        return "Le montant de la facture s'élève à "+this.total+"€";
    }

    public void detailFacture() {
        for(Action a : detail.keySet()) {
            if(a instanceof Reservation){
                Reservation r = (Reservation) a;
                System.out.println("Reservation du "+r.getArrivee()+" au "+r.getDepart()
                +" : "+detail.get(r)+"€");
            }
            if(a instanceof Commande) {
                System.out.println("");
            }
            if(a instanceof Menage) {
                System.out.println("");
            }
        }
    }

    public void supAction(int id) {

    }

    public void calculFacture() {
        this.total = 0.0;
        for(Action a : detail.keySet()) {
            total += detail.get(a);
        }
    }
}
