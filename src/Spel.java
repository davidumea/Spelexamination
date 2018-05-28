/*
David Andersson
2018-04-23
Pang pang spel
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;


/**
 * Namnge variabler
 */
public class Spel extends Canvas {

    private Ball ball;
    private Player player;
    private Laser laser;

    public static Timer timerUp;
    public static Timer timerLeft;
    public static Timer timerDown;
    public static Timer timerRight;

    private static final int stepSpeed = 1;
    private static final int timesSpeed = 2;

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public static boolean shootingUp = false;
    public static boolean shootingDown = false;
    public static boolean shootingLeft = false;
    public static boolean shootingRight = false;

    public static final int laserSpeed = 3;
    public static final int timesLaserSpeed = 2;

    public static boolean checkedShot = true;

    /**
     * Skapa spelyta, spelaren, bollar, kopplar tangentbordet till spelet.
     * Skapar ett system som uppdaterar hela spelet 60 gånger varje sekund så att spelet blir "60 fps" (frames per second)
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

//        ArrayList<Ball> balls = new ArrayList<Ball>();
//        balls.add(new Ball());

        player = new Player(ball);
        laser = new Laser(ball);

        this.addKeyListener(new KeyListener());

        Thread Ball = new Thread(this.ball);
        Thread Player = new Thread(this.player);
        Thread Laser = new Thread(this.laser);
        Ball.start();
        Player.start();
        Laser.start();

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
     * Ritar ut bollen och spelaren på spelytan
     */
    private void draw() {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        ball.draw(dbg);
        player.draw(dbg);
        laser.draw(dbg);
        getGraphics().drawImage(dbImage,0,0,this);
    }

    private class KeyListener implements java.awt.event.KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * När en knapp blir nedtryckt så skapas en timer för den riktningen (W = uppåt, A = vänster, S = neråt och D = höger),
         * så länge knappen är nedtryckt så rör sig spelaren 1 pixlel varje millisekund i den riktningen.
         *
         * Piltangenterna används för att skjuta en laser, en för varje riktning.
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W && !movingUp) {
                movingUp = true;
                timerUp = new Timer(timesSpeed, e1 -> player.characterModel.y -= stepSpeed);
                timerUp.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_A && !movingLeft) {
                movingLeft = true;
                timerLeft = new Timer(timesSpeed, e1 -> player.characterModel.x -= stepSpeed);
                timerLeft.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_S && !movingDown) {
                movingDown = true;
                timerDown = new Timer(timesSpeed, e1 -> player.characterModel.y += stepSpeed);
                timerDown.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_D && !movingRight) {
                movingRight = true;
                timerRight = new Timer(timesSpeed, e1 -> player.characterModel.x += stepSpeed);
                timerRight.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && checkedShot) {
                checkedShot = false;
                laser.shoot("up");
                shootingUp = true;
                shootingLeft = false;
                shootingDown = false;
                shootingRight = false;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && checkedShot) {
                checkedShot = false;
                laser.shoot("left");
                shootingLeft = true;
                shootingUp = false;
                shootingRight = false;
                shootingDown = false;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && checkedShot) {
                checkedShot = false;
                laser.shoot("down");
                shootingDown = true;
                shootingUp = false;
                shootingRight = false;
                shootingLeft = false;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && checkedShot) {
                checkedShot = false;
                laser.shoot("right");
                shootingRight = true;
                shootingUp = false;
                shootingDown = false;
                shootingLeft = false;
            }
        }

        /**
         * När knappen åt en riktning (W, A, S eller D) släpps så stannas timern, alltså slutar spelaren röra på sig åt den riktningen.
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