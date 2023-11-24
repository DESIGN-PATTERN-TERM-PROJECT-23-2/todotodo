package com.project.todotodo.domain;

public class OneWeekDisplay implements Observer, DisplayElement {
    Observable observable;

    public OneWeekDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("OneWeekDisplay is displaying");
    }

    @Override
    public void update(Observable o, Object arg) {
        // Handle the update event if needed
    }
}