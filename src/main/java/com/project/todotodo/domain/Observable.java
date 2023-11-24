package com.project.todotodo.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Observable {
    private boolean changed = false;
    private List<Observer> observers;

    public Observable() {
        observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    public void setChanged() {
        changed = true;
    }

    public void clearChanged() {
        changed = false;
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        if (!changed) {
            return;
        }
        clearChanged();
        for (Observer observer : observers) {
            observer.update(this, arg);
        }
    }
}

