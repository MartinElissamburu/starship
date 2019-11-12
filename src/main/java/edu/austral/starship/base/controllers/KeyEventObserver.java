package edu.austral.starship.base.controllers;

public interface KeyEventObserver {
    void onKeyEvent(int keyCode);

    void onKeyEventKeyPressed(int keyCode);
}
