package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; //16x16 tile
    final int SCALE = 3;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48 tile

    //Achieves a 4 to 3 ratio
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    //WORLD SETTINGS
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;
    public final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    //FPS
    final int FPS = 60;
    
    //You need Thread for a game clock so that the program does not stop and wait for inputs but constantly runs
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);
    public CollisionChecker colChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel() {
        //Set the size of this class (JPanel)
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        //Buffering function, improves rendering. All drawing from this will be done offscreen
        this.setDoubleBuffered(true);
        //gamepanel can now get the key inputs
        this.addKeyListener(keyH);
        //gamepanel is focused to recieve key inputs
        this.setFocusable(true);
    }

    //Starting the Thread and passing gamePanel into Thread(this) by using this.
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Override run method is needed when Thread is started. Thread automatically calls upon a run();
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; //0.016666... seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
            //Will do 2 things in this loop. Nr 1 is UPDATE information such as character positions
            update();
            //Nr 2 is DRAW the screen with the UPDATED information
            repaint();
            delta--;
            drawCount++;
            }

            /* Counts FPS
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            } */
        }
    }
    public void update() {
        //update the player from the class player
        player.update();
    }
    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
        //set graphics to be graphics 2d
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        //draw player with the player class's method
        player.draw(g2);
        //dispose it and release any system resources that its using
        g2.dispose();
    }
}
