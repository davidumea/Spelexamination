/*
David Andersson
2018-04-23
Pang pang spel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Namnge variabler
 */
public class Spel extends Canvas {

    private Ball ball;
    private Player player;

    private Timer timerUp;
    private Timer timerLeft;
    private Timer timerDown;
    private Timer timerRight;

    private static final int STEP_SPEED = 2;
    private static final int TIMES_STEPS = 5;

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public static boolean gameOver = false;

    /**
     * Skapa allt
     */
    private Spel(){
        JFrame frame = new JFrame("Spel");
        int width = 700;
        int height = 700;
        this.setSize(new Dimension(width, height));
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBackground(Color.lightGray);
        ball = new Ball();
        player = new Player(ball);

        this.addKeyListener(new KeyListener());

        Thread ball = new Thread(this.ball);
        Thread Player = new Thread(player);
        ball.start();
        Player.start();

        long lastUpdate = System.nanoTime();

        int fps = 60;
        long dt = 1000000000/fps;
        while (true) {
            if (System.nanoTime() - lastUpdate > dt) {
                lastUpdate = System.nanoTime();
                draw();
            }
        }
    }

    public static void main(String[] args) {
        new Spel();
    }

    /**
     * Rita ut bollen och spelaren
     */
    private void draw() {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        ball.draw(dbg);
        player.draw(dbg);
        getGraphics().drawImage(dbImage,0,0,this);
    }

    private class KeyListener implements java.awt.event.KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * Gör så knapparna W, A, S och D flyttar spelaren upp, vänster, ner och höger på skärmen
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W && !movingUp) {
                movingUp = true;
                timerUp = new Timer(TIMES_STEPS, e1 -> player.characterModel.y -= STEP_SPEED);
                timerUp.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_A && !movingLeft) {
                movingLeft = true;
                timerLeft = new Timer(TIMES_STEPS, e1 -> player.characterModel.x -= STEP_SPEED);
                timerLeft.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_S && !movingDown) {
                movingDown = true;
                timerDown = new Timer(TIMES_STEPS, e1 -> player.characterModel.y += STEP_SPEED);
                timerDown.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_D && !movingRight) {
                movingRight = true;
                timerRight = new Timer(TIMES_STEPS, e1 -> player.characterModel.x += STEP_SPEED);
                timerRight.start();
            }
        }

        /**
         * När W, A, S eller D inte är nertryckt så stannar den spelaren på skärmen
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                movingUp = false;
                timerUp.stop();
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                movingLeft = false;
                timerLeft.stop();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                movingDown = false;
                timerDown.stop();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                movingRight = false;
                timerRight.stop();
            }
        }
    }
}
