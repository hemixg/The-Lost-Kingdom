package monster;

import entity.Entity;
import main.GamePanel;
public class MON_Orc extends Entity {
    GamePanel gp;
    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Orc";
        setDefaultValues(40 * gp.tileSize, 40 * gp.tileSize, 1, "down", maxLife = 4, life = maxLife);
        initSolidArea(3, 18, 42, 30);
        initImage("/monster/orc/orc_up1.png", "/monster/orc/orc_up2.png", "/monster/orc/orc_down1.png", "/monster/orc/orc_down2.png",
                "/monster/orc/orc_right1.png", "/monster/orc/orc_right2.png", "/monster/orc/orc_left1.png", "/monster/orc/orc_left2.png");
    }
}
