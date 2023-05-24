package fisherman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    BufferedImage bg = ImageUtils.getImage("/resource/WinLayerBg.png");
    ArrayList<Fish> fish = new ArrayList<>();
    int fishNum;
    Net net = new Net();
    int mouseX,mouseY;
    double theta;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    Cannon cannon = new Cannon();
    ArrayList<Gold> golds = new ArrayList<>();


    public GamePanel(){
        fishNum = 20;
        for(int i=0;i<fishNum;i++){
            fish.add(new Fish(this));
        }
        createMouseListener();
        createKeyListener();
        this.requestFocus();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.black);
        g.drawImage(bg,0,0,null);

        for(int i=0;i<fishNum;i++){
            Fish f = fish.get(i);
            g.drawImage(f.image,f.getX(),f.getY(),f.getWidth(),f.getHeight(),null);
        }


        for (int i = 0; i < golds.size(); i++) {
            Gold gold = golds.get(i);
            g.drawImage(gold.getImage(),gold.getX(),gold.getY(),gold.width,gold.getHeight(),null);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Gold",Font.BOLD,30));
            g.drawString("+"+gold.price,gold.getX()+50,gold.getY()+30);
        }

        //画大炮
        double centerX = cannon.getX() + (double)cannon.getWidth()/2;
        double centerY = cannon.getY() + (double)cannon.getHeight()/4*3;
        double xx = mouseX - centerX;
        double yy = mouseY - centerY;
        Graphics2D graphics2d = (Graphics2D) g.create();
        theta = -Math.atan(xx/yy);
        graphics2d.rotate(theta,centerX,centerY);
        graphics2d.drawImage(cannon.getImage(),cannon.getX(),cannon.getY(),cannon.getWidth(),cannon.getHeight(),null);

        if(bullets.size()>0) {
            for (Bullet bullet : bullets) {
                if (bullet != null) {
                    Graphics2D graphics2D2 = (Graphics2D) g.create();
                    graphics2D2.rotate(bullet.getTheta(), centerX, centerY);
                    graphics2D2.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
                }
            }
        }

    }

    public void createKeyListener(){
        KeyAdapter adapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD1 -> {
                        cannon.change(1);
                    }
                    case KeyEvent.VK_NUMPAD2 -> {
                        cannon.change(2);
                    }
                    case KeyEvent.VK_NUMPAD3 -> {
                        cannon.change(3);
                    }
                    case KeyEvent.VK_NUMPAD4 -> {
                        cannon.change(4);
                    }
                    case KeyEvent.VK_NUMPAD5 -> {
                        cannon.change(5);
                    }
                    case KeyEvent.VK_NUMPAD6 -> {
                        cannon.change(6);
                    }
                    case KeyEvent.VK_NUMPAD7 -> {
                        cannon.change(7);
                    }
                }
                repaint();
            }
        };
        this.addKeyListener(adapter);
    }


    public void createMouseListener(){
        MouseAdapter adapter = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
                GamePanel.this.requestFocus();
                repaint();
            }


            @Override
            public void mouseClicked(MouseEvent e) {
                Music shootMusic = new Music("src/resource/shoot.wav",false);
                shootMusic.start();
                Bullet bullet = new Bullet(cannon.getX()+30, cannon.getY(), theta, GamePanel.this,cannon.getPower());
                bullet.start();
                bullets.add(bullet);

            }
        };
        addMouseMotionListener(adapter);
        addMouseListener(adapter);
    }

    public void action(){
        Music bgMusic = new Music("src/resource/GameLayerBGM.wav",true);
        bgMusic.start();
        for(int i=0;i<fishNum;i++){
            fish.get(i).start();
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Fish> getFish() {
        return fish;
    }
}
