import java.awt.*;

public class Player implements Runnable {
    Rectangle r2;
    Color c2;
    int vx = 1;
    int vy = 1;

    Ball b;

    public Player(Ball b1) {
        this.b = b1;
        r2 = new Rectangle(50,50,50,50);
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
        if (r2.intersects(b.r)) {
            System.exit(1);
        }
    }

    public void draw(Graphics g) {
        g.setColor(c2);
        g.fillRect(r2.x,r2.y,(int)r2.getWidth(),(int)r2.getHeight());
    }
}