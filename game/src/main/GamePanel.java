package main;

import entity.Entity;
import entity.NPC_FallenHero;
import entity.Player;
import entity.NPC_OldMan;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 32;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 100;
    public final int MaxWorldRow = 100;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[30];
    ArrayList<Entity> entityList = new ArrayList<>();
    public int gameState;
    public final int dialogueState = 4;
    public int gameOver = 3;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setNPC();
        player = new Player(this, keyH);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
        }
        if (player.life <= 0) {
            gameState = gameOver;
            player.life = player.maxLife;
        }

        if (gameState == pauseState) {

        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);
            entityList.add(player);
            for(int i = 0; i < npc.length; i ++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++ ) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }

            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            entityList.clear();
            ui.draw(g2);
        }
        g2.dispose();
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.setVolume(50.0f);
        sound.play();
        sound.loop();

    };
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}