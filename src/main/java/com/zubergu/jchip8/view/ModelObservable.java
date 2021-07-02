package com.zubergu.jchip8.view;

public interface ModelObservable {
    
    void addObserver( ModelObserver observer );
    void removeObserver( ModelObserver observer );
    void notifyObservers();
}
