
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 4;

    final int tileSize = originalTileSize * scale; // 64x64 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 1 b024 pixels
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    // ...existing code...

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
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }
   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw game elements
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.fillRect(playerX, playerY, tileSize, tileSize);  
        g2.dispose();
    }
}
