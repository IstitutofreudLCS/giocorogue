package giocorogue.entity;

import giocorogue.interfaccia.* ;

public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

}
