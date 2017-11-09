import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class Program extends PApplet{
    int tick = 0;
    private Character player;
    private ArrayList<Bomb> bombs;
    public static void main(String[] args) {
        PApplet.main("Program");
    }

    @Override
    public void settings() {
        this.size(800, 600);
    }

    @Override
    public void setup() {
        this.background(13, 124, 48);
        ResourceManager.init(this);
        ResourceManager.cropImage(Constants.MOVE,"./image/bomberman-movement.png", 21, 32, 5, 4);
        ResourceManager.cropImage(Constants.STAY, "./image/bomberman-stay.png", 21, 32, 3, 4);
        ResourceManager.cropImage(Constants.BOMB, "./image/bomberman-effect.png", 24, 24, 4, 1);

        player = new Character(new Position(400, 530));
        bombs = new ArrayList<>();
    }

    @Override
    public void draw() {
        this.background(13, 124, 48);
        tick++;

        drawPlayer();

        makeBomb();
        drawBomb();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();

        switch(code){
            case LEFT :
                player.setDir(Constants.MOVE_LEFT);
                break;
            case RIGHT :
                player.setDir(Constants.MOVE_RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int code = event.getKeyCode();
        switch(code){
            case LEFT :
                player.setDir(Constants.STAY_LEFT);
                break;
            case RIGHT :
                player.setDir(Constants.STAY_RIGHT);
                break;
        }
    }

    public void drawPlayer() {
        player.update();
        player.draw(this);
    }

    public void makeBomb() {
        if(Math.random() < 0.1){
            Bomb b = new Bomb();
            bombs.add(b);
        }
    }

    public void drawBomb() {
        for(int i=0;i<bombs.size();i++) {
            Bomb b = bombs.get(i);
            b.update();
            if(b.getPosition().getPosY()>540)
                bombs.remove(b);
            b.draw(this, player);
        }
    }
}
