package src;



public class Client {
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

}
