package main;

import object.Chest_Object;
import object.Door_Object;
import object.Key_Object;

public class AssetPlacer {
    GamePanel gamePanel;
    public AssetPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }
    public void setObject(){
        gamePanel.obj[0] = new Key_Object();
        gamePanel.obj[0].worldX = 23 * gamePanel.TILE_SIZE;
        gamePanel.obj[0].worldY = 7 * gamePanel.TILE_SIZE;

        gamePanel.obj[1] = new Key_Object();
        gamePanel.obj[1].worldX = 23 * gamePanel.TILE_SIZE;
        gamePanel.obj[1].worldY = 40 * gamePanel.TILE_SIZE;

        gamePanel.obj[2] = new Key_Object();
        gamePanel.obj[2].worldX = 38 * gamePanel.TILE_SIZE;
        gamePanel.obj[2].worldY = 8 * gamePanel.TILE_SIZE;

        gamePanel.obj[3] = new Door_Object();
        gamePanel.obj[3].worldX = 10 * gamePanel.TILE_SIZE;
        gamePanel.obj[3].worldY = 11 * gamePanel.TILE_SIZE;

        gamePanel.obj[4] = new Door_Object();
        gamePanel.obj[4].worldX = 8 * gamePanel.TILE_SIZE;
        gamePanel.obj[4].worldY = 28 * gamePanel.TILE_SIZE;

        gamePanel.obj[5] = new Door_Object();
        gamePanel.obj[5].worldX = 12 * gamePanel.TILE_SIZE;
        gamePanel.obj[5].worldY = 22 * gamePanel.TILE_SIZE;

        gamePanel.obj[6] = new Chest_Object();
        gamePanel.obj[6].worldX = 10 * gamePanel.TILE_SIZE;
        gamePanel.obj[6].worldY = 7 * gamePanel.TILE_SIZE;
    }
}
