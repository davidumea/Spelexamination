/*
David Andersson
2018-04-23
Pang pang spel
 */

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class Spel extends Canvas {
    JFrame frame;

    Image dbImage;
    Graphics dbg;

    Ball b;
    Thread t;
    //Player p;

    int width = 900;
    int height = 900;
    int y = 10;

    Dimension screenSize = new Dimension(width,height);
    boolean running = false;

    public Spel(){
        this.frame = new JFrame("Spel");
        this.setSize(screenSize);
        this.frame.add(this);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.pack();
        this.frame.setBackground(Color.lightGray);
        //p = new Player();
        b = new Ball();
        t = new Thread(b);
        t.start();

        Thread ball = new Thread(b);
        //Thread Player = new Thread(p);
        ball.start();
        //Player.start();
        running = true;

        long lastUpdate = System.nanoTime();
        long lastCheck = System.nanoTime();
        int fps = 60;
        int ups = 40;
        long dt = 1000000000/fps;
        long dt2 = 1000000000/ups;
        while (running) {

            if (System.nanoTime() - lastUpdate > dt) {
                lastUpdate = System.nanoTime();
                draw();
            }
            if (System.nanoTime() - lastCheck > dt2) {
                lastCheck = System.nanoTime();
                check();
            }
        }
    }
    private void draw() {
        dbImage = createImage(getWidth(),getHeight());
        dbg =  dbImage.getGraphics();
        b.draw(dbg);
        getGraphics().drawImage(dbImage,0,0,this);
    }
    private void check() {
        //Do something
    }
    public void paint(Graphics g) {
        try {
            draw(g);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void draw(Graphics g) throws InterruptedException {
        g.setColor(Color.RED);
        b.draw(g);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Spel spl = new Spel();
    }
}
