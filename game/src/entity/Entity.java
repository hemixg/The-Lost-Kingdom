package entity;


import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Heart;

import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class Entity {
    public String name;
    public boolean collision = false;
    public int solidAreaDefaultX, solidAreaDefaultY;

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, image, image2, image3;
    private int circleMovementIterations = 0;
    private int directionChangeCooldown = 0;
    private int maintainDirectionIterations = 0;
    public BufferedImage ups1, ups2, downs1, downs2, lefts1, lefts2, rights1, rights2;
    public String direction = "down";
    KeyHandler keyH;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int screenX;
    public int screenY;
    public int maxLife;
    public int life;
    public int hasKey = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;
    boolean attacking = false;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int value;
    public int type;
    public final int type_pickupOnly = 7;
    public void checkDrop(){
        dropItem(new OBJ_Heart(gp));
    }
    public void dropItem(Entity droppedItem) {
        boolean itemDropped = false;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null) {
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = this.worldX;
                gp.obj[i].worldY = this.worldY;
                itemDropped = true;
                break;
            }
        }

    }

    public void initSolidArea(int x, int y, int width, int height) {
        solidArea = new Rectangle(x, y, width, height);
    }
    public Entity(GamePanel gp) {
        this.gp = gp;
        initSolidArea(0, 0, 0, 0);
    }

    public void setDefaultValues(int worldX, int worldY, int speed, String direction, int maxLife, int life) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        this.direction = direction;
        this.maxLife = maxLife;
        this.life = life;
    }

    public void initImage(String up1Path, String up2Path, String down1Path, String down2Path, String right1Path, String right2Path, String left1Path, String left2Path) {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream(up1Path));
            up2 = ImageIO.read(getClass().getResourceAsStream(up2Path));
            down1 = ImageIO.read(getClass().getResourceAsStream(down1Path));
            down2 = ImageIO.read(getClass().getResourceAsStream(down2Path));
            right1 = ImageIO.read(getClass().getResourceAsStream(right1Path));
            right2 = ImageIO.read(getClass().getResourceAsStream(right2Path));
            left1 = ImageIO.read(getClass().getResourceAsStream(left1Path));
            left2 = ImageIO.read(getClass().getResourceAsStream(left2Path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getPlayerAttackImage() {
        ups1 = setup("/player/up1s", gp.tileSize, gp.tileSize);
        ups2 = setup("/player/up2s", gp.tileSize, gp.tileSize);
        downs1 = setup("/player/down1s", gp.tileSize, gp.tileSize);
        downs2 = setup("/player/down2s", gp.tileSize, gp.tileSize);
        lefts1 = setup("/player/left1s", gp.tileSize, gp.tileSize);
        lefts2 = setup("/player/left2s", gp.tileSize, gp.tileSize);
        rights1 = setup("/player/right1s", gp.tileSize, gp.tileSize);
        rights2 = setup("/player/right2s", gp.tileSize, gp.tileSize);
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if (hasKey == 3) {
                        gp.obj[i] = null;
                        hasKey = 0;
                    }
                    break;
                    case "heart":
                    gp.obj[i] = null;
                    life ++;

            }
        }
    }



    public void reverseDirection() {
        switch (direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void setAction() {
        if (circleMovementIterations == 0) {

            if (directionChangeCooldown == 0) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }

                directionChangeCooldown = 30;
            } else {
                directionChangeCooldown--;
            }
        } else {
            circleMovementIterations--;

            if (checkCollisionInCurrentDirection()) {
                reverseDirection();
            }
            maintainDirectionIterations = 5;
        }
        if (maintainDirectionIterations > 0) {
            maintainDirectionIterations--;
        } else {
            circleMovementIterations = 20;
        }
    }
    public boolean checkCollisionInCurrentDirection() {
        int nextX = worldX;
        int nextY = worldY;

        switch (direction) {
            case "up":
                nextY -= speed;
                break;
            case "down":
                nextY += speed;
                break;
            case "left":
                nextX -= speed;
                break;
            case "right":
                nextX += speed;
                break;
        }
        return false;
    }

    public void speak() {}
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        if (!collisionOn) {
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
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        screenX = worldX - gp.player.worldX + gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = worldY - gp.player.worldY + gp.screenHeight / 2 - gp.tileSize / 2;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = ups1;
                        }
                        if (spriteNum == 2) {
                            image = ups2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = downs1;
                        }
                        if (spriteNum == 2) {
                            image = downs2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = lefts1;
                        }
                        if (spriteNum == 2) {
                            image = lefts2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = rights1;
                        }
                        if (spriteNum == 2) {
                            image = rights2;
                        }
                    }
                    break;
            }

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public BufferedImage setup(String imagePath, int width, int height ) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}