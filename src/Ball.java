import java.awt.*;
import java.util.ArrayList;

/**
 * Namnget variabler / fält
 */
public class Ball implements Runnable {
    public Rectangle ballModel;
    private Color ballColor;
    private int ballx = (int) Math.pow(-1,(int)(Math.random()*2));
    private int bally = (int) Math.pow(-1,(int)(Math.random()*2));

    public boolean alive = true;

    private ArrayList<Ball> balls;

    /**
     * Skapar storleken och färgen för bollen, kopplar arraylisten med bollar.
     * @param b1
     */
    Ball(ArrayList<Ball> b1) {
        balls = b1;
        ballModel = new Rectangle((int)(Math.random()*700),(int)(Math.random()*700),25,25);
        ballColor = new Color(0xFF2ACB);
    }

    /**
     * Om en boll inte har blivit träffad av en laser så är allt som vanligt,
     * om den blir träffad så ändrar den bollens dimension till width 0, height 0 så att den inte ska finnas kvar osynlig.
     */
    @Override
    public void run() {
        try {
            while (alive) {
                ballModel.x += ballx;
                ballModel.y += bally;

                collision();
                Thread.sleep(7);
            }
            ballModel = new Rectangle(0,0,0,0);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Om en boll träffar en vägg/golv/tak så kommer den att byta riktning, men även byta till en random hastighet.
     */
    private void collision() {
        if ((ballx >= 0 && ballModel.x >= 675) || (ballx <= 0 && ballModel.x <= 0)) {
            if (ballx >= 0) {
                ballx = (int) (-(Math.random()+1)*3);
            } else{
                ballx = (int) ((Math.random()+1)*3);
            }
        }
        if ((bally >= 0 && ballModel.y >= 675) || (bally <= 0 && ballModel.y <= 0)) {
            if (bally >= 0) {
                bally = (int) (-(Math.random()+1)*3);
            } else{
                bally = (int) ((Math.random()+1)*3);
            }
        }
    }

    /**
     * Ritar ut bollarna
     * @param g
     */
    public void draw(Graphics g) {
        if(alive) {
            g.setColor(ballColor);
            g.fillOval(ballModel.x, ballModel.y, (int) ballModel.getWidth(), (int) ballModel.getHeight());
        }
    }
}
