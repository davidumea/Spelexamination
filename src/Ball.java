import java.awt.*;

public class Ball implements Runnable {
    public static Rectangle projectileModel;
    private Color c;
    private int vx = (int) Math.pow(-1,(int)(Math.random()*2));
    private int vy = (int) Math.pow(-1,(int)(Math.random()*2));

    Ball() {
        projectileModel = new Rectangle((int)(Math.random()*700),(int)(Math.random()*700),25,25);
        c = new Color(0xFF2ACB);
    }

    @Override
    public void run() {
        try {
            while (true) {
                projectileModel.x += vx;
                projectileModel.y += vy;

                collision();
                Thread.sleep(7);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void collision() {
        if ((vx >= 0 && projectileModel.x >= 675) || (vx <= 0 && projectileModel.x <= 0)) {
            if (vx >= 0) {
                vx = (int) (-(Math.random()+1)*3);
            } else{
                vx = (int) ((Math.random()+1)*3);
            }
        }
        if ((vy >= 0 && projectileModel.y >= 675) || (vy <= 0 && projectileModel.y <= 0)) {
            if (vy >= 0) {
                vy = (int) (-(Math.random()+1)*3);
            } else{
                vy = (int) ((Math.random()+1)*3);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(c);
        g.fillOval(projectileModel.x, projectileModel.y,(int) projectileModel.getWidth(),(int) projectileModel.getHeight());
    }
}
