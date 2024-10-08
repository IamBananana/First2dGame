package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        //Screen size is halfway of the screen width and height (middle)
        SCREEN_X = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
        SCREEN_Y = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.TILE_SIZE * 23;
        worldY = gp.TILE_SIZE * 21;
        speed = 4;
        direction = "down";
    }
    File f1 = new File("src/res/player/boy_up_1.png");
    File f2 = new File("src/res/player/boy_up_2.png");
    File f3 = new File("src/res/player/boy_down_1.png");
    File f4 = new File("src/res/player/boy_down_2.png");
    File f5 = new File("src/res/player/boy_left_1.png");
    File f6 = new File("src/res/player/boy_left_2.png");
    File f7 = new File("src/res/player/boy_right_1.png");
    File f8 = new File("src/res/player/boy_right_2.png");

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            //Check tile collision 
            collisionOn = false;
            gp.colChecker.checkTile(this);

            //If collision is false, player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        direction = "right";
                        worldX += speed;
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
        //g2.setColor(Color.white);
        //draw character for now
        //g2.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE);

        BufferedImage image = null;
        //find which direction facing and swap image to that direction
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
        //draw image at x y with same width and height, image observer is null
        g2.drawImage(image, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
