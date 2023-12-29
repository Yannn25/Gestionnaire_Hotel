package src;

public class Chambre implements Disponibilité{

    private static int count = 0;
    private String place;
    private String type;
    private int prix;
    private final int idChambre;

    public Chambre (String place, String type, int prix) {
        this.place = place;
        this.type = type;
        this.prix = prix;
        this.idChambre = ++count;
    }

    @Override
    public String toString(){
        return "Voici la chambre n°"+this.idChambre+ ", elle est de type "+this.type+" avec un lit " + this.place;
    }

    public String DateDispo() {

        return null;
    }

    public String DateOccupe() {
        return null;
    }
}
