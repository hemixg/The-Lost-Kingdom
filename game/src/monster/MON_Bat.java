package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Bat extends Entity {
    GamePanel gp;
    public MON_Bat(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Bat";
        setDefaultValues(40 * gp.tileSize, 80 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/bat_down_1.png", "/monster/bat_down_2.png", "/monster/bat_down_1.png", "/monster/bat_down_2.png",
                "/monster/bat_down_1.png", "/monster/bat_down_2.png", "/monster/bat_down_1.png", "/monster/bat_down_2.png");
    }
}
