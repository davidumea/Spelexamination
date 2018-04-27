/*import javafx.scene.paint.Stop;

import java.awt.*;

public class Player implements Runnable {
    Rectangle r2;
    Color c2;
    int vx = 1;
    int vy = 1;

    public Player() {
        r2 = new Rectangle(50,50,425,425);
        c2 = new Color(0xff00ff);

    }

    @Override
    public void run() {
        try {
            while (true) {
                r2.x = vx;
                r2.y = vy;

                collision();
                Thread.sleep(7);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void collision() {

    }
}*/