package main;

import monster.*;
import entity.NPC_FallenHero;
import object.OBJ_Door;
import object.OBJ_Key;
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0]= new OBJ_Key(gp);
        gp.obj[0].worldX = 15 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

        gp.obj[1]= new OBJ_Key(gp);
        gp.obj[1].worldX = 75 * gp.tileSize;
        gp.obj[1].worldY = 88 * gp.tileSize;

        gp.obj[2]= new OBJ_Key(gp);
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 58 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 80 * gp.tileSize;
        gp.obj[3].worldY = 29 * gp.tileSize;

    }
    public void setNPC() {
        gp.npc[0] = new NPC_FallenHero(gp);

    }

    public void setMonster() {
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 40 * gp.tileSize;
        gp.monster[0].worldY = 40 * gp.tileSize;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 40 * gp.tileSize;
        gp.monster[1].worldY = 88 * gp.tileSize;

        gp.monster[2] = new MON_RedSlime(gp);
        gp.monster[2].worldX = 40 * gp.tileSize;
        gp.monster[2].worldY = 60 * gp.tileSize;

        gp.monster[3] = new MON_RedSlime(gp);
        gp.monster[3].worldX = 40 * gp.tileSize;
        gp.monster[3].worldY = 50 * gp.tileSize;

        gp.monster[4] = new MON_Squeleton(gp);
        gp.monster[4].worldX = 21 * gp.tileSize;
        gp.monster[4].worldY = 58 * gp.tileSize;

        gp.monster[5] = new MON_Squeleton(gp);
        gp.monster[5].worldX = 22 * gp.tileSize;
        gp.monster[5].worldY = 58 * gp.tileSize;

        gp.monster[6] = new MON_Squeleton(gp);
        gp.monster[6].worldX = 19 * gp.tileSize;
        gp.monster[6].worldY = 58 * gp.tileSize;

        gp.monster[7] = new MON_Squeleton(gp);
        gp.monster[7].worldX = 19 * gp.tileSize;
        gp.monster[7].worldY = 58 * gp.tileSize;

        gp.monster[8] = new MON_Squeleton(gp);
        gp.monster[8].worldX = 22 * gp.tileSize;
        gp.monster[8].worldY = 56 * gp.tileSize;

        gp.monster[9] = new MON_Squeleton(gp);
        gp.monster[9].worldX = 15 * gp.tileSize;
        gp.monster[9].worldY = 60 * gp.tileSize;

        gp.monster[10] = new MON_Squeleton(gp);
        gp.monster[10].worldX = 15 * gp.tileSize;
        gp.monster[10].worldY = 59 * gp.tileSize;

        gp.monster[5] = new MON_Zombi(gp);
        gp.monster[5].worldX = 14 * gp.tileSize;
        gp.monster[5].worldY = 26 * gp.tileSize;
        gp.monster[12] = new MON_Zombi(gp);
        gp.monster[12].worldX = 18 * gp.tileSize;
        gp.monster[12].worldY = 25 * gp.tileSize;
        gp.monster[13] = new MON_Zombi(gp);
        gp.monster[13].worldX = 18 * gp.tileSize;
        gp.monster[13].worldY = 24 * gp.tileSize;
        gp.monster[14] = new MON_Orc(gp);
        gp.monster[14].worldX = 16 * gp.tileSize;
        gp.monster[14].worldY = 26 * gp.tileSize;
        gp.monster[15] = new MON_Orc(gp);
        gp.monster[15].worldX = 17 * gp.tileSize;
        gp.monster[15].worldY = 26 * gp.tileSize;
        gp.monster[16] = new MON_Orc(gp);
        gp.monster[16].worldX = 18 * gp.tileSize;
        gp.monster[16].worldY = 26 * gp.tileSize;

        gp.monster[8] = new MON_Bat(gp);
        gp.monster[8].worldX = 40 * gp.tileSize;
        gp.monster[8].worldY = 40 * gp.tileSize;
        gp.monster[12] = new MON_Orc(gp);
        gp.monster[0].worldX = 70 * gp.tileSize;
        gp.monster[0].worldY = 88 * gp.tileSize;

    }
}