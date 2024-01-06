package src;

import java.io.Serializable;

public abstract class Action  implements Serializable {

    protected static int count = 0;
    private final Client client;
    private boolean valide;
    protected final int idAction;


    public Action(Client c) {
        this.client = c;
        this.valide = true;
        this.idAction = ++count;
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
