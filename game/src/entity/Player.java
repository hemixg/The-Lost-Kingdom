package entity;

import main.GamePanel;
import main.KeyHandler;
public class Player extends Entity {
    GamePanel gp;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        attackArea.width = 36;
        attackArea.height = 36;
        initSolidArea(45, 45, 40, 40);
        setDefaultValues(70 * gp.tileSize, 92 * gp.tileSize, 7, "down", maxLife = 6, life = maxLife);
        initImage("/player/up1.png", "/player/up2.png", "/player/down1.png", "/player/down2.png",
                "/player/right1.png", "/player/right2.png", "/player/left1.png", "/player/left2.png");
        getPlayerAttackImage();
    }

    public void update() {
        if (attacking) {
            attacking();
        }
        if (keyH.upPressed1 || keyH.downPressed1 || keyH.leftPressed1 || keyH.rightPressed1 || keyH.enterPressed) {

            if (keyH.upPressed1) {
                direction = "up";
            }
            if (keyH.downPressed1) {
                direction = "down";
            }
            if (keyH.leftPressed1) {
                direction = "left";
            }
            if (keyH.rightPressed1) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterindex = gp.cChecker.checkEntity(this, gp.monster);
            contanctMonster(monsterindex);

            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void interactNPC(int i){
     if (gp.keyH.enterPressed) {
         if (i != 999) {
             gp.gameState = gp.dialogueState;
             gp.npc[i].speak();
         }
         else {
             attacking = true;
         }
     }
    }

    public void attacking() {
        spriteCounter++;
        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up" :worldY -=attackArea.height; break;
                case "down" :worldY +=attackArea.height; break;
                case "left" :worldX -=attackArea.width; break;
                case "right" :worldX +=attackArea.width; break;

            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            dommageMonster(monsterIndex);

            worldY = currentWorldY;
            worldX = currentWorldX;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25 ) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void contanctMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }
    public void dommageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0) {
                    gp.monster[i].checkDrop();
                    gp.monster[i] = null;
                }
            }
        }
    }
}