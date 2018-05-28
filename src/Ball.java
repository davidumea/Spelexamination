import java.awt.*;

public class Ball implements Runnable {
    public Rectangle ballModel;
    private Color c;
    private int vx = (int) Math.pow(-1,(int)(Math.random()*2));
    private int vy = (int) Math.pow(-1,(int)(Math.random()*2));

    Ball() {
        ballModel = new Rectangle((int)(Math.random()*700),(int)(Math.random()*700),25,25);
        c = new Color(0xFF2ACB);
    }

    @Override
    public void run() {
        try {
            while (true) {
                ballModel.x += vx;
                ballModel.y += vy;

                collision();
                Thread.sleep(7);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void collision() {
        if ((vx >= 0 && ballModel.x >= 675) || (vx <= 0 && ballModel.x <= 0)) {
            if (vx >= 0) {
                vx = (int) (-(Math.random()+1)*3);
            } else{
                vx = (int) ((Math.random()+1)*3);
            }
        }
        if ((vy >= 0 && ballModel.y >= 675) || (vy <= 0 && ballModel.y <= 0)) {
            if (vy >= 0) {
                vy = (int) (-(Math.random()+1)*3);
            } else{
                vy = (int) ((Math.random()+1)*3);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(c);
        g.fillOval(ballModel.x, ballModel.y,(int) ballModel.getWidth(),(int) ballModel.getHeight());
    }
}
