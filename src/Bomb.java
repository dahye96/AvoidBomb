import processing.core.PApplet;

public class Bomb extends RenderObject{

    private Position position;
    private float speed;
    private float accel = 0.03f;
    int tick = 0;

    public Bomb() {
        float x = (float) (Math.random() * 800);
        position = new Position(x, 0);
        speed = (float) (Math.random() * 3 + 1);

        RenderObject.makeMode(Constants.BOMB_READY, Constants.BOMB, 0, 2);
        RenderObject.makeMode(Constants.BOMB_EXPLODE, Constants.BOMB, 3, 3);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void update() {
        speed += accel;
        position.setPosY(position.getPosY()+speed);
    }

    public void draw(PApplet applet, Character p) {
        tick++;
        int frame = tick / 5 % 3;
        if(!checkCollision(p))
            applet.image(RenderObject.render(Constants.BOMB_READY).get(frame), position.getPosX(), position.getPosY());
        else
            applet.image(RenderObject.render(Constants.BOMB_EXPLODE).get(0), position.getPosX(), position.getPosY());
    }


    public boolean checkCollision(Character p) {
        if (position.getPosX() - 20 < p.getPosition().getPosX() && position.getPosX() + 20 > p.getPosition().getPosX()
                && position.getPosY() - 20 < p.getPosition().getPosY() && position.getPosY() + 20 > p.getPosition().getPosY()) {
            System.out.println("충돌");
            return true;

        } else {
            return false;
        }
    }
}
