import java.awt.*;

public class Laser implements Runnable {
    static Rectangle laserModelUp;
    static Rectangle laserModelDown;
    Rectangle laserModelRight;
    Rectangle laserModelLeft;
    private static Color c3;

    public Laser() {

        laserModelUp = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() - 20, 2, 20);
        laserModelDown = new Rectangle((int)Player.characterModel.getX(),(int)Player.characterModel.getY() + 50, 2, 20);
        c3 = new Color(0xFF1E1E);
    }

    public static void shoot(Graphics g) {
        if (Spel.shootingDown){
            g.setColor(c3);
            g.fillRect((laserModelDown.x + 24),laserModelDown.y,(int)laserModelDown.getWidth(),(int)laserModelDown.getHeight());

        }
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
        if (laserModelDown.intersects(Ball.projectileModel)) {
            System.out.println("jaha");
        }
        if (laserModelDown.y <= 0 || laserModelDown.y >= 680){
            Spel.shootingTimerDown.stop();
        }
    }
}
