package fisherman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bullet extends Thread{
    private BufferedImage image;
    private ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;
    private int step;
    private double theta;
    private Point point;
    private GamePanel panel;
    int power;

    public Bullet(int x,int y,Double theta,GamePanel panel,int power){
        String bulletName = "/resource/bullet/bullet0" + power +".png";
        step = 50/5;
        this.power = power;
        image = ImageUtils.getImage(bulletName);
        width = image.getWidth();
        height = image.getHeight();
        this.theta = theta;
        this.point = new Point(x, y);
        this.x = x;
        this.y = y;
        this.panel = panel;
    }

    public void move(){
        this.y = this.y - this.step;
        int distance = this.point.y - this.y;
        //求xx,需要进一步进行强制转换
        int xx = (int) (distance * Math.sin(theta));
        int xxx = this.point.x + xx;
        //求yy坐标
        int yy = (int) (distance * Math.cos(theta));
        int yyy = this.point.y - yy;
        //判断是否出界
        if (xxx < 0 || xxx > 1200 || yyy < 0) {
            //将子弹置为死亡
            isAlive = false;
            //在数组中删除子弹
            panel.getBullets().remove(this);
        }

        ArrayList<Fish> fishs = panel.fish;
        for (Fish fish : fishs) {
            if(!fish.getCatch()) {
                //鱼的x坐标范围
                int maxX = fish.getX() + fish.getWidth();
                //鱼的y坐标范围
                int mayY = fish.getY() + fish.getHeight();
                if (xxx > fish.getX() && xxx < maxX && fish.getY() < yyy && yyy < mayY) {
                    int blood = fish.getBlood() - power;
                    fish.setBlood(blood);
                    //设置鱼被抓到
                    if(fish.getBlood() <= 0) {
                        Gold gold = new Gold(xxx,yyy,panel,fish.getGold());
                        panel.golds.add(gold);
                        gold.start();
                        Music catchMusic = new Music("src/resource/catch.wav",false);
                        catchMusic.start();
                        fish.setCatch(true);
                    }
                    //设置让子弹消失
                    isAlive = false;
                    //在数组中删除子弹
                    panel.getBullets().remove(this);
                }
            }
        }

        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true){
            if (isAlive) {
                move();
            }else {
                //直接结束线程
                return;
            }
        }

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

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
