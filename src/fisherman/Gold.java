package fisherman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Gold extends Thread{
    BufferedImage image;
    int num=0;
    int price;
    int x;
    int y;
    int width;
    int height;
    ArrayList<BufferedImage> images = new ArrayList<>();
    GamePanel panel;
    public Gold(int x,int y,GamePanel panel,int gold){
        for (int i = 1; i <= 9; i++) {
            String goldName = "/resource/gold/gold0"+i+".png";
            images.add(ImageUtils.getImage(goldName));
        }
        this.x = x;
        this.y = y;
        image = images.get(0);
        this.panel = panel;
        width = image.getWidth();
        height = image.getHeight();
        price = gold;
    }

    @Override
    public void run(){
        while (num<8) {
            image = images.get(num);
            width=image.getWidth();
            height=image.getHeight();
            num++;
            if(num==8) panel.golds.remove(this);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
}
