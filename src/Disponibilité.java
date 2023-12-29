package src;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface Disponibilité {
    List<Date> dispo = new LinkedList<Date>();
    List<Date> occupe = new LinkedList<Date>();

    String DateDispo();
    String DateOccupe();

}
