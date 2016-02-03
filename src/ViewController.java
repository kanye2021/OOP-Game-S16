/**
 * Created by sergiopuleri on 2/1/16.
 */

import java.awt.event.KeyEvent;

abstract public class ViewController {

    protected View view;

    public ViewController() {

    }
    public ViewController(View view) {
        this.view = view;
    }

    abstract void handleKeyPress(int key);
    abstract void handleKeyRelease(int key);

    // TODO: Not sure if need
    protected void reRender() {
        this.view.repaint();
    }
}
