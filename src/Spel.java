/*
David Andersson
2018-04-23
Pang pang spel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Namnge variabler
 */
public class Spel extends Canvas {
    JFrame frame;

    Image dbImage;
    Graphics dbg;

    Ball b;
    Thread t;
    Player p;
    Timer timer;

    int width = 700;
    int height = 700;
    int y = 1;
    int x = 1;

    static boolean movingUp = false;
    static boolean movingDown = false;
    boolean movingLeft = false;
    boolean movingRight = false;

    boolean running = false;

    static boolean gameOver = false;

    /**
     * Skapa allt
     */
    public Spel(){
        this.frame = new JFrame("Spel");
        this.setSize(new Dimension(width,height));
        this.frame.add(this);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setBackground(Color.lightGray);
        b = new Ball();
        p = new Player(b);
        timer = new Timer();

        this.addKeyListener(new KL());

        Thread ball = new Thread(b);
        Thread Player = new Thread(p);
        ball.start();
        Player.start();
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

    /**
     * Rita ut bollen och spelaren
     */
    private void draw() {
        dbImage = createImage(getWidth(),getHeight());
        dbg =  dbImage.getGraphics();
        b.draw(dbg);
        p.draw(dbg);
        getGraphics().drawImage(dbImage,0,0,this);
    }

    private void check() {
        //Do something
    }

    /**
     * Kör programmet
     * @param args
     */
    public static void main(String[] args) {
        Spel spl = new Spel();
    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Gör så knapparna W, A, S och D flyttar spelaren upp, vänster, ner och höger på skärmen
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_W) {
                p.r2.y-=12;
                movingUp = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                p.r2.x-=12;
                movingLeft = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                p.r2.y+=12;
                movingDown = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                p.r2.x+=12;
                movingRight = true;
            }
            if (movingUp && movingLeft) {
                p.r2.y-=12;
                p.r2.x-=12;
            }
            if (movingUp && movingRight) {
                p.r2.y-=12;
                p.r2.x+=12;
            }
            if (movingDown && movingLeft) {
                p.r2.y+=12;
                p.r2.x-=12;
            }
            if (movingDown && movingRight) {
                p.r2.y+=12;
                p.r2.x+=12;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                movingUp = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                movingLeft = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                movingDown = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                movingRight = false;
            }
        }
    }
}
