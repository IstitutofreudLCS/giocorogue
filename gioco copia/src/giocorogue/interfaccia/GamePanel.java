package giocorogue.interfaccia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import javax.swing.JPanel;
import giocorogue.entity.*;
import giocorogue.objects.SuperObjects;
import res.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale; // 64x64 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1 b024 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public Player player;
    public SuperObjects obj[] = new SuperObjects[10];






    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();

        player = new Player(this, keyH);
    }
    // ...existing code...

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // ...existing code...

    @Override
    public void run() {
        
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0; 
        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
         
    }
    public void update() {
        player.update();
    }
   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw game elements
        Graphics2D g2 = (Graphics2D) g;

        //tile
        tileM.draw(g2);
        //object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //player
        player.draw(g2);
        //object
        
        g2.dispose();
    }
}
