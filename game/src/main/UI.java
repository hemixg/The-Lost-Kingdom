package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heartFull, heartDemi, heartEmpty, keyImage;
    Font arial_40;
    public int commandNum = 0;
    public int maxMenuOptions = 3;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public boolean gameFinished = false;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.down1;
        Entity heart = new OBJ_Heart(gp);
        heartFull = heart.image;
        heartEmpty = heart.image3;
        heartDemi = heart.image2;
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
       this.g2 = g2;

        if (gp.player.hasKey == 1) {
            g2.drawImage(keyImage, gp.tileSize / 3, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
        }
        if (gp.player.hasKey == 2) {
            g2.drawImage(keyImage, gp.tileSize / 3, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
            g2.drawImage(keyImage, gp.tileSize+gp.tileSize/6, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
        }
        if (gp.player.hasKey == 3) {
            g2.drawImage(keyImage, gp.tileSize / 3, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
            g2.drawImage(keyImage, gp.tileSize +gp.tileSize/6, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
            g2.drawImage(keyImage, gp.tileSize  + gp.tileSize, gp.tileSize / 2*3, gp.tileSize, gp.tileSize, null);
        }

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState) {
            drawPausedScreen();
            drawPlayerLife();
        }
        if (gp.gameState == gp.gameOver) {
            drawGameOver();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }
    public void drawTitleScreen() {
        g2.setColor(new Color(29, 8, 63));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 96));
        String title = "The Lost Kingdom";
        int titleX = getXForCenteredText(title);
        int titleY = gp.tileSize * 3;

        g2.setColor(new Color(242, 162, 0));
        g2.drawString(title, titleX + 5, titleY + 5);
        g2.setColor(new Color(255, 255, 255));
        g2.drawString(title, titleX, titleY);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 66));

        drawMenuItem("Start", 0, gp.tileSize * 5);
        drawMenuItem("Load Game", 1, gp.tileSize * 6);
        drawMenuItem("Return to Desktop", 2, gp.tileSize * 7);
    }

    private void drawMenuItem(String text, int commandNumber, int yPosition) {
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (gp.screenWidth - textWidth) / 2;

        if (commandNum == commandNumber) {
            g2.setColor(new Color(242, 162, 0));
            g2.fillRect(x - 10, yPosition - 50, textWidth + 20, 70);
            g2.setColor(new Color(29, 8, 63));
        } else {
            g2.setColor(new Color(255, 255, 255));
        }

        g2.drawString(text, x, yPosition);
    }
    private int getXForCenteredText(String text) {
        return (gp.screenWidth - g2.getFontMetrics().stringWidth(text)) / 2;
    }

    public void drawPlayerLife() {
       // gp.player.life = 6;
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2) {
            g2.drawImage(heartEmpty, x , y , null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        while(i < gp.player.life) {
            g2.drawImage(heartDemi, x ,y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    private void drawDialogueScreen() {
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawPausedScreen() {
        g2.setColor(new Color(29, 8, 63, 200)); // R, G, B, Alpha
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 80));
        String title = "PAUSED";
        int titleX = getXForCenteredText(title);
        int titleY = gp.screenHeight / 6;

        g2.setColor(new Color(242, 162, 0));
        g2.drawString(title, titleX + 5, titleY + 5);
        g2.setColor(new Color(255, 255, 255));
        g2.drawString(title, titleX, titleY);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 66));

        drawPausedMenuItem("Continue", 0, gp.screenHeight / 2 + gp.tileSize);
        drawPausedMenuItem("Back to Menu", 1, gp.screenHeight / 2 + 2 * gp.tileSize);
        drawPausedMenuItem("Quit the Game", 2, gp.screenHeight / 2 + 3 * gp.tileSize);
    }

    private void drawPausedMenuItem(String text, int commandNumber, int yPosition) {
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (gp.screenWidth - textWidth) / 2;

        if (commandNum == commandNumber) {
            g2.setColor(new Color(242, 162, 0));
            g2.fillRect(x - 10, yPosition - 50, textWidth + 20, 70);
            g2.setColor(new Color(29, 8, 63, 220)); // R, G, B, Alpha
        } else {
            g2.setColor(new Color(255, 255, 255, 220)); // R, G, B, Alpha
        }

        g2.drawString(text, x, yPosition);
    }

    public void drawGameOver() {
        g2.setColor(new Color(255, 0, 0, 150)); // Red with transparency
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 80));
        String title = "Game Over";
        int titleX = getXForCenteredText(title);
        int titleY = gp.screenHeight / 6;

        g2.setColor(Color.red);
        g2.drawString(title, titleX + 5, titleY + 5);
        g2.setColor(Color.white);
        g2.drawString(title, titleX, titleY);

        g2.setFont(new Font("Press Start 2P", Font.PLAIN, 66));

        drawGameOverMenuItem("Continue", 0, gp.screenHeight / 2 + gp.tileSize);
        drawGameOverMenuItem("Back to Menu", 1, gp.screenHeight / 2 + 2 * gp.tileSize);
        drawGameOverMenuItem("Quit the Game", 2, gp.screenHeight / 2 + 3 * gp.tileSize);
    }

    private void drawGameOverMenuItem(String text, int commandNumber, int yPosition) {
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = (gp.screenWidth - textWidth) / 2;

        if (commandNum == commandNumber) {
            g2.setColor(new Color(255, 0, 0));
            g2.fillRect(x - 10, yPosition - 50, textWidth + 20, 70);
            g2.setColor(new Color(255, 255, 255, 220));
        } else {
            g2.setColor(new Color(255, 255, 255, 180));
        }
        g2.drawString(text, x, yPosition);
    }
}
