import java.awt.*;
import java.util.ArrayList;

/**
 * Namnge variabler / fält
 */
public class Player implements Runnable {
    public static Rectangle characterModel;
    private Color playerColor;
    private Color playerOutlineColor;

    private ArrayList<Ball> balls;

    /**
     * Skapar storleken och färgerna för spelaren, kopplar arraylisten med bollar.
     * @param b1
     */
    public Player(ArrayList<Ball> b1) {
        balls = b1;
        characterModel = new Rectangle(325,325,50,50);
        playerColor = new Color(0x42C0FF);
        playerOutlineColor = new Color(0x000000);
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
     * Om spelaren blir träffad av en boll så kommer spelet stängas ner
     *
     * Ifall spelaren försöker röra sig utanför spelytan så kommer den att stoppas
     */
    private void collision() {
        for(Ball ballNumber : balls) {
            if (characterModel.intersects(ballNumber.ballModel)) {
                //System.exit(0);
                System.out.println("Dead");
            }
        }
        if (characterModel.y <= 0) {
            Spel.timerUp.stop();
        }
        if (characterModel.x <= 0) {
            Spel.timerLeft.stop();
        }
        if (characterModel.y >= 650) {
            Spel.timerDown.stop();
        }
        if (characterModel.x >= 650) {
            Spel.timerRight.stop();
        }
    }

    /**
     * Ritar ut spelaren
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(playerColor);
        g.fillRect(characterModel.x, characterModel.y,(int) characterModel.getWidth(),(int) characterModel.getHeight());
        g.setColor(playerOutlineColor);
        g.drawRect(characterModel.x,characterModel.y,(int)characterModel.getWidth(),(int)characterModel.getHeight());
    }
}