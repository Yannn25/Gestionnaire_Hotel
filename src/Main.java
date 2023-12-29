package src;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Gestionnaire Hotel");
        window.setBounds(0,0,1000,600);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton btn = new JButton("Bienvenue");
        btn.setBounds(400, 300, 150,50);
        window.getContentPane().add(btn);
    }

}
