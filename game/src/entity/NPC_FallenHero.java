package entity;
import main.GamePanel;

public class NPC_FallenHero extends Entity{
    GamePanel gp;

    public NPC_FallenHero(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Fallen Hero";
        setDefaultValues(70 * gp.tileSize, 88 * gp.tileSize, 1,"down", maxLife = 6, life = maxLife);
        initSolidArea(30, 80, 40, 15);
        initImage("/npc/fallenhero_up_1.png", "/npc/fallenhero_up_2.png", "/npc/fallenhero_down_1.png", "/npc/fallenhero_down_2.png",
                "/npc/fallenhero_right_1.png", "/npc/fallenhero_right_2.png", "/npc/fallenhero_left_1.png", "/npc/fallenhero_left_2.png");
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0] = "Bonjour, preux chevalier, nous avons besoin de toi..\n";
        dialogues[1] = "Des monstres se sont emparés du royaume ! Tu dois le protéger.\n";
        dialogues[2] = "Retrouves les cléfs du royaume, il y en a une ici, cherches les 2 autres ! \n";
    }
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
}