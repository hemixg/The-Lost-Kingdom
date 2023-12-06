package entity;

import main.GamePanel;

public class NPC_OldMan extends Entity {
    GamePanel gp;

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setDefaultValues(35 * gp.tileSize, 60 * gp.tileSize, 1, "down", maxLife = 6, life = maxLife);
        initSolidArea(30, 15, 40, 15);
        initImage("/npc/oldman_up_1.png", "/npc/oldman_up_2.png", "/npc/oldman_down_1.png", "/npc/oldman_down_2.png",
                "/npc/oldman_right_1.png", "/npc/oldman_right_2.png", "/npc/oldman_left_1.png", "/npc/oldman_left_2.png");
    }
}

