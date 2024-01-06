package src;


import java.io.Serializable;

public class Client implements Serializable {
    private static int count = 0;
    private final int idClient;
    private String prenom;
    private String nom;
    private Facture facture;

    public Client(String p, String n) {
        this.idClient = ++count;
        this.prenom = p;
        this.nom = n;
        this.facture = new Facture();
    }

    public int getIdClient() {
        return idClient;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Facture getFacture() {
        return facture;
    }

    @Override
    public String toString() {
        return this.prenom +" " +this.nom + " client n°"+this.idClient;
    }

    public String ClientMontantFacture(){
        return "Le montant de la facture de "+ this.prenom + this.nom + facture;
    }
    public String ClientDetailFacture() {
        return "Le détail de la facture de "+this.prenom + " " +this.nom + " " + facture.detailFacture();
    }
}
