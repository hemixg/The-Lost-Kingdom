package main;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.GenericArrayType;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed1, downPressed1, leftPressed1, rightPressed1, enterPressed;
    public boolean upPressed2, downPressed2, leftPressed2, rightPressed2;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.pauseState || gp.gameState == gp.titleState || gp.gameState == gp.gameOver) {
            if (code == KeyEvent.VK_Z) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = gp.ui.maxMenuOptions - 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum >= gp.ui.maxMenuOptions ) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    if (gp.gameState != gp.pauseState) {
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    else {
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                }
                if (gp.ui.commandNum ==1) {
                    gp.gameState = gp.titleState;
                }
                if (gp.ui.commandNum == 2 ){
                    System.exit(0);
                }
            }

        }

        if (gp.gameState == gp.playState) {


            if (code == KeyEvent.VK_Z) {
                upPressed1 = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed1 = true;
            }
            if (code == KeyEvent.VK_Q) {
                leftPressed1 = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed1 = true;
            }
            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                }
                else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }

            if (code == KeyEvent.VK_UP) {
                upPressed2 = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed2 = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed2 = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed2 = true;
            }
            if ( code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }

        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }
    @Override
    public void keyReleased (KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            upPressed1 = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed1 = false;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed1 = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed1 = false;
        }

        if (code == KeyEvent.VK_UP) {
            upPressed2 = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed2 = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed2 = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed2 = false;
        }
    }
}
