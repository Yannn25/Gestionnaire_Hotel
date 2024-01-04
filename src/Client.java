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
    }
}
