import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Character extends RenderObject {
    private int tick = 0;
    private int dir = Constants.STAY_DOWN;
    private float speed = 0;
    private float accel = 0.12f;

    public Character(Position p) {
        this.position = p;

        RenderObject.makeMode(Constants.MOVE_RIGHT, Constants.MOVE, 5, 9);
        RenderObject.makeMode(Constants.MOVE_LEFT, Constants.MOVE, 15, 19);

        RenderObject.makeMode(Constants.STAY_DOWN, Constants.STAY, 0, 2);
        RenderObject.makeMode(Constants.STAY_RIGHT, Constants.STAY, 3, 5);
        RenderObject.makeMode(Constants.STAY_LEFT, Constants.STAY, 9, 11);

    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        if(position.getPosX() < 20) this.position.setPosX(20);
        else if(position.getPosX() > 780) this.position.setPosX(780);
        else this.position = position;
    }


    @Override
    public void update() {
        if (this.dir == Constants.MOVE_LEFT) {
            speed -= accel;
        } else if (this.dir == Constants.MOVE_RIGHT) {
            speed += accel;
        } else {
            if (speed > accel) speed -= 2 * accel;
            else if(speed < -accel) speed += 2 * accel;
            else speed = 0;
        }
        position.setPosX(position.getPosX() + speed);
        setPosition(position);
    }

    public void draw(PApplet applet) {
        tick++;
        int frame;

        if(dir == Constants.MOVE_LEFT || dir == Constants.MOVE_RIGHT) {
            frame = tick / 5 % 5;
        } else {
            frame = tick / 5 % 3;
        }
        applet.image(RenderObject.render(dir).get(frame), position.getPosX(), position.getPosY());
    }
}
