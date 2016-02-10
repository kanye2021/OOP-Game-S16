/**
 * Created by sergiopuleri
 * on 2/1/16.
 */

package controllers;

import views.View;

abstract public class ViewController {

    public abstract void handleKeyPress(int key);

    public abstract void handleKeyRelease(int key);

}
