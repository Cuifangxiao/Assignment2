package fisherman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cannon {
    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int power=1;
    private final ArrayList<BufferedImage> powerImage = new ArrayList<>();


    public Cannon(){
        for (int i = 1; i <= 7; i++) {
            String fireName = "/resource/fire/net_" + i + "1.png";
            powerImage.add(ImageUtils.getImage(fireName));
        }
        image = powerImage.get(0);
        width = image.getWidth();
        height = image.getHeight();
        x = 600;
        y = 575;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void change(int i){
        power = i;
        image = powerImage.get(i-1);
        width = image.getWidth();
        height = image.getHeight();

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
