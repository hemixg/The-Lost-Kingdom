package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Green Slime";
        setDefaultValues(35 * gp.tileSize, 75 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/greenslime_down_1.png", "/monster/greenslime_down_2.png", "/monster/greenslime_down_1.png", "/monster/greenslime_down_2.png",
                "/monster/greenslime_down_1.png", "/monster/greenslime_down_2.png", "/monster/greenslime_down_1.png", "/monster/greenslime_down_2.png");
    }
}
