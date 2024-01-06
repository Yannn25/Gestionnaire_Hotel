package src;

import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsoleApp app;
        System.out.println("Voulez vous charger votre ancienne session ?(Entrez 1 pour cela)");
        if(sc.nextInt() == 1){
            app = new ConsoleApp(1);
        }else {
            System.out.println("Chargement d'une nouvelle partie...\n\n");
            app = new ConsoleApp(0);
        }

    }

}
