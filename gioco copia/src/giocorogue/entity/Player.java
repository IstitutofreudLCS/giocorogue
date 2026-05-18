package giocorogue.entity;

import java.awt.*;
import giocorogue.interfaccia.* ;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import res.tile.*;

public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
            worldX = gp.tileSize * 23;
            worldY = gp.tileSize * 21;
            speed = 4;
            direction = "down";
    }

    public void getPlayerImage() {

        try {
            // Carica le immagini dalla cartella res/player
            up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/player/boy_right_2.png"));

            System.out.println("Immagini caricate con successo!");

        }catch(IOException e) {
            System.out.println("Errore nel caricamento delle immagini: " + e.getMessage());
            System.out.println("Assicurati che i file PNG siano in: res/player/");
            e.printStackTrace();
        }
    }




    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
             keyH.leftPressed == true || keyH.rightPressed == true) {
            


                if (keyH.upPressed) {
                    direction = "up";
                }
                if (keyH.downPressed) {
                    direction = "down";
                }
                if (keyH.leftPressed) {
                    direction = "left";
                }
                if (keyH.rightPressed) {
                    direction = "right";
                }
                //check tile collision
                collisionOn = false;
                gp.cChecker.checkTile(this);

                //if collision is false, player can move
                if (collisionOn == false) {
                    switch (direction) {
                        case "up":worldY -= speed;
                            break;
                        case "down":worldY += speed;
                            break;
                        case "left":worldX -= speed;
                            break;
                        case "right":worldX += speed;
                            break;
                    }
                }


                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
    }

}
