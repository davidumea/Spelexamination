import java.awt.*;

public class Player implements Runnable {
    Rectangle characterModel;
    Color c2;
    int vx = 1;
    int vy = 1;

    Ball b;

    public Player(Ball b1) {
        this.b = b1;
        characterModel = new Rectangle(50,50,50,50);
        c2 = new Color(0xff00ff);
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
        if (characterModel.intersects(b.projectileModel)) {
            //System.exit(1);
        }
    }

    public void draw(Graphics g) {
        g.setColor(c2);
        g.fillRect(characterModel.x, characterModel.y,(int) characterModel.getWidth(),(int) characterModel.getHeight());
    }
}