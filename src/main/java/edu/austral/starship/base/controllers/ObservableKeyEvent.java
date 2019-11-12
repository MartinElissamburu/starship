package edu.austral.starship.base.controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableKeyEvent {
    private List<KeyEventObserver> observers;

    public ObservableKeyEvent(List<KeyEventObserver> observers){
        this.observers = observers;
    }

    public ObservableKeyEvent() {
        this.observers = new ArrayList<>();
    }

    public void notifyAll(Integer keyEvent){
        for (KeyEventObserver keyEventObserver : observers) {
            keyEventObserver.onKeyEvent(keyEvent);
        }
    }

    public void notifyAllKeyPressed(Integer keyEvent){
        for (KeyEventObserver keyEventObserver : observers) {
            keyEventObserver.onKeyEventKeyPressed(keyEvent);
        }
    }

    public void addObserver(KeyEventObserver observer) {
        observers.add(observer);
    }

    public void setObservers (List<KeyEventObserver> observers){
        this.observers = observers;
    }
}
