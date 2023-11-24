package com.project.todotodo.domain;

public interface Observer {
    void update(Observable o, Object arg);
}
