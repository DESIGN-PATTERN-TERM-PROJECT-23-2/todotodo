package com.project.todotodo.domain;

public class OnlyCalenderDisplay implements Observer, DisplayElement {
    Observable observable;

    public OnlyCalenderDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("OnlyCalenderDisplay is displaying");
    }

    @Override
    public void update(Observable o, Object arg) {
        // Handle the update event if needed
    }
}
