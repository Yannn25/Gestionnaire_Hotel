package src.erreurs;

/*
    Exception lever lorsqu'un élement n'est pas contenue dans une liste
 */

public class ContenuException extends Exception {

    public ContenuException(String s) {
        super(s);
    }

}
