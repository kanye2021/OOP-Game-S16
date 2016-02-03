import javax.swing.*;
import java.awt.*;

/**
 * Created by Bradley on 2/2/16.
 */

public class TestingAreaViewport extends JFrame {

    public TestingAreaViewport() {

        initUI();
    }

    private void initUI() {
        Map m = new Map();
        Entity e = new Entity();

        add(new AreaViewport(m, e));

        setResizable(false);
        pack();

        setTitle("Star");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new TestingAreaViewport();
                ex.setVisible(true);
            }
        });
    }
}