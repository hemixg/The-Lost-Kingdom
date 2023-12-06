package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;

import java.util.Random;

public class MON_Squeleton extends Entity {
    GamePanel gp;
    public MON_Squeleton(GamePanel gp) {
        super(gp);
        this.gp = gp;
        life = 4;
        name = "Skeleton Lord";
        setDefaultValues(40 * gp.tileSize, 40 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/squelette/Squelette_up1.png", "/monster/squelette/squelette_up2.png", "/monster/squelette/squelette_down1.png", "/monster/squelette/squelette_down2.png",
                "/monster/squelette/squelette_right1.png", "/monster/squelette/squelette_right2.png", "/monster/squelette/squelette_left1.png", "/monster/squelette/squelette_left2.png");
    }
}
