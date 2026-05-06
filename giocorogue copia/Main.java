package giocorogue;

import javax.swing.JFrame ;
import giocorogue.interfaccia.* ;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon&Swords");

        GamePanel GamePanel = new GamePanel();
        window.add(GamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);    

        GamePanel.startGameThread();
    }
} 