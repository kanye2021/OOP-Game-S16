package views;

import controllers.ViewController;
import utilities.Load_Save;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameView extends View {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int Y_OFFSET = View.B_HEIGHT / 6;

    private static  SaveGameView saveGameView = new SaveGameView();
    private SaveGameView() {}


    public static void init(){

    }


    public static void render(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        clear(g);

        FontMetrics fm = g.getFontMetrics(small);
        g.setFont(small);


        int fraction = 1;
        for (SaveGameController.SaveOptions option : SaveGameController.SaveOptions.values()) {

            //Box Stuff
            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
            int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
            int boxY = Y_OFFSET + ((fraction) * View.B_HEIGHT / 6) - BUTTON_HEIGHT / 2;
            int boxDX = BUTTON_WIDTH;
            int boxDY = BUTTON_HEIGHT;

            // String stuff
            Rectangle2D r = fm.getStringBounds(option.getText(), g);
            int stringX = View.B_WIDTH / 2 - (int) (r.getWidth() / 2);
            int stringY = boxY + (int) (r.getHeight()) + fm.getAscent();

            Color primaryColor;
            Color secondaryColor;

            /*if (option == ((SaveGameController) viewController).getSelectedOption()) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;
            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;
            }*/

            /*g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(option.getText(), stringX, stringY);
            g.drawRect(boxX, boxY, boxDX, boxDY);
*/
            fraction += 1;

        }

        Toolkit.getDefaultToolkit().sync();

    }

    public static SaveGameController getController() {

        return SaveGameController.getInstance();

    }

    private static class SaveGameController extends ViewController {


        public enum SaveOptions {
            SAVE_AND_EXIT("Save and Exit") {
                protected void selectOption() {
                    doAction(SaveOptions.SAVE_AND_EXIT);
                }

                ;},
            SAVE("Just Save") {
                protected void selectOption() {
                    doAction(SaveOptions.SAVE);
                }

                ;},
            BACK("Nevermind...") {
                protected void selectOption() {
                    doAction(SaveOptions.BACK);
                }

                ;};

            private String text;

            protected abstract void selectOption();

            private SaveOptions(String s) {
                this.text = s;
            }

            protected SaveOptions previous() {
                if (this.ordinal() == 0) {
                    return SaveOptions.values()[SaveOptions.values().length - 1];
                } else {
                    return SaveOptions.values()[this.ordinal() - 1];
                }
            }

            protected SaveOptions next() {
                if (this.ordinal() == SaveOptions.values().length - 1) {
                    return SaveOptions.values()[0];
                } else {
                    return SaveOptions.values()[this.ordinal() + 1];
                }
            }

            public String getText() {
                return this.text;
            }
        }

        private SaveOptions selectedOption;
        private static SaveGameController controller = new SaveGameController();

        public SaveOptions getSelectedOption() {
            return selectedOption;
        }

        public static SaveGameController getInstance() {

            return controller;

        }

        public SaveGameController() {
            selectedOption = SaveOptions.SAVE_AND_EXIT;
        }

        private static void doAction(SaveOptions opt) {
            switch (opt) {
                case SAVE_AND_EXIT:
                    System.out.println("SAVING GAME FROM SAVE GAME VIEW AND EXITING");
                    Load_Save.getInstance().save();
                    System.exit(0);
                    break;
                case SAVE:
                    System.out.println("SAVING GAME FROM SAVE GAME VIEW");
                    Load_Save.getInstance().save();
//                IOMediator.setActiveView(IOMediator.Views.GAME);
                    break;
                case BACK:
//                IOMediator.setActiveView(IOMediator.Views.GAME);
                    break;
            }

        }

        //Remember that function has to go within keypress
        @Override
        public void handleKeyPress(int key) {

            if (key == KeyEvent.VK_UP) {
                System.out.println("up");
                selectedOption = selectedOption.previous();

            } else if (key == KeyEvent.VK_DOWN) {
                System.out.println("down");
                selectedOption = selectedOption.next();
            } else if (key == KeyEvent.VK_ENTER) {
                selectedOption.selectOption();
            }

        }

        public void handleKeyRelease(int key) {

        }
    }

}
