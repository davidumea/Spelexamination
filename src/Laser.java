import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Laser implements Runnable {
    static Rectangle laserModelUp;
    static Rectangle laserModelDown;
    Rectangle laserModelRight;
    Rectangle laserModelLeft;
    private static Color c3;

    private Timer shootingTimer;

    private boolean visibleCheck = false;

    public Laser() {
        updateLocation();
        c3 = new Color(0xFF1E1E);
    }
    public void updateLocation(){
        laserModelUp = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() - 30, 4, 30);
        laserModelLeft = new Rectangle((int)Player.characterModel.getX() - 30,(int)Player.characterModel.getY(), 30, 4);
        laserModelDown = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() + 50, 4, 30);
        laserModelRight = new Rectangle((int)Player.characterModel.getX() + 50,(int)Player.characterModel.getY(), 30, 4);
    }
    public void draw(Graphics g){
        g.setColor(c3);
        if (Spel.shootingUp){
            visibleCheck = true;
            g.fillRect((laserModelUp.x + 23),laserModelUp.y,(int)laserModelUp.getWidth(),(int)laserModelUp.getHeight());
        } else if (Spel.shootingLeft) {
            visibleCheck = true;
            g.fillRect(laserModelLeft.x,(laserModelLeft.y +23),(int)laserModelLeft.getWidth(),(int)laserModelLeft.getHeight());
        } else if (Spel.shootingDown) {
            visibleCheck = true;
            g.fillRect((laserModelDown.x + 23),laserModelDown.y,(int)laserModelDown.getWidth(),(int)laserModelDown.getHeight());
        } else if (Spel.shootingRight) {
            visibleCheck = true;
            g.fillRect(laserModelRight.x,(laserModelRight.y + 23),(int)laserModelRight.getWidth(),(int)laserModelRight.getHeight());
        }
    }

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
    private void collision() {
        if ((laserModelDown.intersects(Ball.projectileModel) || laserModelUp.intersects(Ball.projectileModel)
                || laserModelLeft.intersects(Ball.projectileModel) || laserModelRight.intersects(Ball.projectileModel))&& visibleCheck) {
            visibleCheck = false;
            System.out.println("jaha");
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
