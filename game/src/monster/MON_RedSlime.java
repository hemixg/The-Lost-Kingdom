package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_RedSlime extends Entity {
    GamePanel gp;
    public MON_RedSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Red Slime";
        setDefaultValues(40 * gp.tileSize, 80 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/redslime_down_1.png", "/monster/redslime_down_2.png", "/monster/redslime_down_1.png", "/monster/redslime_down_2.png",
                "/monster/redslime_down_1.png", "/monster/redslime_down_2.png", "/monster/redslime_down_1.png", "/monster/redslime_down_2.png");
    }
}
