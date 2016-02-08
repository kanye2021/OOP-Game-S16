/**
 * Created by sergiopuleri
 * on 2/1/16.
 */

package controllers;

import views.View;

abstract public class ViewController {

    protected View view;

    public ViewController() {

    }

    public ViewController(View view) {
        this.view = view;
    }

    public abstract void handleKeyPress(int key);

    public abstract void handleKeyRelease(int key);
}
