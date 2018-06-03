import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Namnger variabler / fält
 */
public class Laser implements Runnable {
    static Rectangle laserModelUp;
    static Rectangle laserModelDown;
    Rectangle laserModelRight;
    Rectangle laserModelLeft;
    private static Color laserColor;

    private Timer shootingTimer;

    private boolean visibleCheck = false;

    private ArrayList<Ball> balls;

    /**
     * Kopplar arraylistan med bollar, uppdaterar platsen av lasrarna.
     * @param b1
     */
    public Laser(ArrayList<Ball> b1) {
        balls = b1;
        updateLocation();
        laserColor = new Color(0xFF1E1E);
    }

    /**
     * uppdaterar var lasern ska skjutas ifrån när de tillkallas,
     * alltså spelarens plats på skärmen och sedan justerad till vilken laser som avfyras.
     */
    public void updateLocation(){
        laserModelUp = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() - 30, 0, 0);
        laserModelLeft = new Rectangle((int)Player.characterModel.getX() - 30,(int)Player.characterModel.getY(), 0, 0);
        laserModelDown = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() + 50, 0, 0);
        laserModelRight = new Rectangle((int)Player.characterModel.getX() + 50,(int)Player.characterModel.getY(), 0, 0);
    }

    /**
     * Ritar ut lasrarna
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(laserColor);
        if (Spel.shootingUp){
            visibleCheck = true;
            laserModelUp.setSize(4,30);
            g.fillRect((laserModelUp.x + 23),laserModelUp.y,(int)laserModelUp.getWidth(),(int)laserModelUp.getHeight());
        } else if (Spel.shootingLeft) {
            visibleCheck = true;
            laserModelLeft.setSize(30,4);
            g.fillRect(laserModelLeft.x,(laserModelLeft.y +23),(int)laserModelLeft.getWidth(),(int)laserModelLeft.getHeight());
        } else if (Spel.shootingDown) {
            visibleCheck = true;
            laserModelDown.setSize(4,30);
            g.fillRect((laserModelDown.x + 23),laserModelDown.y,(int)laserModelDown.getWidth(),(int)laserModelDown.getHeight());
        } else if (Spel.shootingRight) {
            visibleCheck = true;
            laserModelRight.setSize(30,4);
            g.fillRect(laserModelRight.x,(laserModelRight.y + 23),(int)laserModelRight.getWidth(),(int)laserModelRight.getHeight());
        }
    }

    /**
     * Uppdaterar platsen var lasrarna ska avfyras ifrån.
     * När en piltangent blir nedtryckt så skapar och startar den en timer som för lasern i den riktningen.
     * @param direction
     */
    public void shoot(String direction) {
        updateLocation();
        ActionListener shootListener = null;

        if (direction.equals("up")) {
            shootListener = e -> laserModelUp.y -= Spel.laserSpeed;
        } else if (direction.equals("left")) {
            shootListener = e -> laserModelLeft.x -= Spel.laserSpeed;
        } else if (direction.equals("down")){
            shootListener = e -> laserModelDown.y += Spel.laserSpeed;
        } else if (direction.equals("right")) {
            shootListener = e -> laserModelRight.x += Spel.laserSpeed;
        }
        shootingTimer = new Timer(Spel.timesLaserSpeed, shootListener);
        shootingTimer.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                collision();
                Thread.sleep(7);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * När det finns en boll på skärmen så kollar den ifall lasern träffar bollen.
     * Om en laser träffar en boll så gör den bollen alive = false så att bollen blir width 0, height 0 enligt metoden run() i klassen Ball
     *
     * Om lasern träffar en vägg så försvinner den, och gör det möjligt för en till laser att skjutas.
     */
    private void collision() {
        for(Ball ballNumber : balls) {
            if ((laserModelDown.intersects(ballNumber.ballModel) || laserModelUp.intersects(ballNumber.ballModel)
                    || laserModelLeft.intersects(ballNumber.ballModel) || laserModelRight.intersects(ballNumber.ballModel)) && visibleCheck) {
                visibleCheck = false;
                ballNumber.alive = false;
            }
        }
        if (laserModelUp.y <= 0 || laserModelDown.y >= 670 || laserModelLeft.x <= 0 || laserModelRight.x >= 670) {
            updateLocation();
            shootingTimer.stop();
            Spel.shootingUp = false;
            Spel.shootingLeft = false;
            Spel.shootingDown = false;
            Spel.shootingRight = false;
            Spel.checkedShot = true;
        }
    }
}
