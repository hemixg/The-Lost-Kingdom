package tile;

import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[101];
        mapTileNum = new int[gp.maxWorldCol] [gp.MaxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[17].collision = true;
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tile[32].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand2.png")));
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock1.png")));
            tile[30].collision = true;
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rocks.png")));
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/arbustre.png")));
            tile[21].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree1.png")));
            tile[11].collision = true;
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree2.png")));
            tile[22].collision = true;
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree3.png")));
            tile[33].collision = true;
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/trunk.png")));
            tile[24].collision = true;
            tile[44] = new Tile();
            tile[44].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/trunk1.png")));
            tile[44].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass01.png")));
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/bush.png")));
            tile[45] = new Tile();
            tile[45].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterdown.png")));
            tile[45].collision = true;
            tile[46] = new Tile();
            tile[46].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterup.png")));
            tile[47] = new Tile();
            tile[47].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterleft.png")));
            tile[48] = new Tile();
            tile[48].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterright.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.MaxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
       int worldCol = 0;
       int worldRow = 0;

       while (worldCol < gp.maxWorldCol && worldRow < gp.MaxWorldRow) {
           int tileNum = mapTileNum[worldCol][worldRow];
           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {


               g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
           }
           worldCol++;

           if (worldCol == gp.maxWorldCol) {
               worldCol = 0;
               worldRow++;
           }
       }
    }
}
