import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;

public class RenderObject {
    public Position position;
    public static HashMap<Integer, ArrayList<PImage>> imageBox = new HashMap<>();;

    public RenderObject() {

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPostion() {
        return position;
    }

    public static void makeMode(int mode, int name, int start, int end) {
        ArrayList<PImage> img = new ArrayList<>();
        for(int i = start ; i <= end ; i++) {
            img.add(ResourceManager.getImage(name, i));
        }
        imageBox.put(mode, img);
    }

    public static ArrayList<PImage> render(int mode) {
        return imageBox.get(mode);
    }

    public void update() {}

}
