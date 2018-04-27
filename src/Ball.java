import java.awt.*;

public class Ball implements Runnable {
    Rectangle r;
    Color c;
    int vx = 1;
    int vy = 1;

    public Ball(int i, int i1) {
        r = new Rectangle((int)(Math.random()*900),(int)(Math.random()*900),25,25);
        c = new Color(0xFF193C);
    }

    public Ball() {

    }

    @Override
    public void run() {
        try {
            while (true) {
                r.x += vx;
                r.y += vy;

                collision();
                Thread.sleep(7);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void collision() {
        if ((vx >= 0 && r.x >= 675)|| (vx <= 0 && r.x <= 0)) {
            if (vx >= 0) {
                vx = (int) (-(Math.random()+1)*3);
            } else{
                vx = (int) ((Math.random()+1)*3);
            }
        }
        if ((vy >= 0 && r.y >= 675 )|| (vy <= 0 && r.y <= 0)) {
            if (vy >= 0) {
                vy = (int) (-(Math.random()+1)*3);
            } else{
                vy = (int) ((Math.random()+1)*3);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(c);
        g.fillOval(r.x,r.y,(int)r.getWidth(),(int)r.getHeight());
    }
}
