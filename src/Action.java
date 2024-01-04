package src;

public abstract class Action {

    private final Client client;
    private boolean valide;



    public Action(Client c) {
        this.client = c;
        this.valide = true;
    }

    public Client getClient() {
        return this.client;
    }
    public boolean isValide() {
        return valide;
    }
    public void setValide(boolean valide) {
        this.valide = valide;
    }
}
