package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;

import java.util.Random;

public class MON_Zombi extends Entity {
    GamePanel gp;
    public MON_Zombi(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Skeleton Lord";
        setDefaultValues(40 * gp.tileSize, 40 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/zombi/zombi_up1.png", "/monster/zombi/zombi_up2.png", "/monster/zombi/zombi_down1.png", "/monster/zombi/zombi_down2.png",
                "/monster/zombi/zombi_right1.png", "/monster/zombi/zombi_right2.png", "/monster/zombi/zombi_left1.png", "/monster/zombi/zombi_left2.png");
    }
}
