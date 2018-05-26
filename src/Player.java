import java.awt.*;

public class Player implements Runnable {
    public static Rectangle characterModel;
    private Color c2;
    private Color c4;
    Ball b;
    //public int xPos = (int) characterModel.getX();
    //public int yPos = (int) characterModel.getY();

    public Player(Ball b1) {
        this.b = b1;
        characterModel = new Rectangle(325,325,50,50);
        c2 = new Color(0x42C0FF);
        c4 = new Color(0x000000);
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
            //System.exit(0);
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

    public void draw(Graphics g) {
        g.setColor(c2);
        g.fillRect(characterModel.x, characterModel.y,(int) characterModel.getWidth(),(int) characterModel.getHeight());
        g.setColor(c4);
        g.drawRect(characterModel.x,characterModel.y,(int)characterModel.getWidth(),(int)characterModel.getHeight());
    }
}