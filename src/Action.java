package src;

public abstract class Action {

    private Client client;

    public Action(Client c) {
        this.client = c;
    }
    public void setClient(Client c) {
        this.client = c;
    }
    public Client getClient(Client c) {
        return this.client;
    }
}
