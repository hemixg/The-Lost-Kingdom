package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 92;
        eventRect.y = 70;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX =eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
     public void checkEvent() {
      if (hit("")) {
            domagePit();
        }

    }
    public boolean hit(String reqDirection) {
        boolean hit = false;

        if (gp.player.direction.equals(reqDirection)) {
            hit = true;
        }
        return hit;
    }
    public void domagePit() {
        gp.player.life -= 1;
    }

    public void healingPool() {
        if(gp.keyH.enterPressed) {
            gp.player.life = gp.player.maxLife;
        }
    }
}
