package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        type = type_pickupOnly;
        value = 2;
        down1 = setup("/objects/heart/heartFull", gp.tileSize,gp.tileSize );
        name = "heart";
        image = setup("/objects/heart/heartFull",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/heart/heartDemi",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/heart/heartEmpty",gp.tileSize,gp.tileSize);
        initSolidArea(16,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}