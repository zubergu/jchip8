package com.zubergu.jchip8.model;

import com.zubergu.jchip8.view.ModelObserver;
import com.zubergu.jchip8.view.ModelObservable;
import java.util.Set;
import java.util.HashSet;

public class Display implements ModelObservable {
    
    public static final int NUMBER_OF_ROWS = 32;    // y
    public static final int NUMBER_OF_COLUMNS = 64; // x
    
    
    private boolean[][] memory;
    private Set<ModelObserver> observers = new HashSet<ModelObserver>();
    private boolean needRefresh;
    
    public Display() {
        memory = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS]; // memory[y][x] ( row, col )
    }
    
    public void setPixel( int col, int row, boolean value ) {
        memory[row][col] = value;
    }
    
    public boolean getPixel( int col, int row ) {
        return memory[row][col];
    }
    
    public boolean xorPixel( int col, int row, boolean value ) {
        boolean curr = getPixel( col, row );
        setPixel( col, row, curr ^ value );
        
        return curr && value;  // only true ^ true == collision
    }
    
    public void setNeedRefresh() {
        notifyObservers();
    }
    
    @Override
    public void addObserver( ModelObserver observer ) {
        observers.add( observer );
    }
    
    @Override
    public void removeObserver( ModelObserver observer ) {
        observers.remove( observer );
    }
    
    @Override
    public void notifyObservers() {
        for( ModelObserver observer : observers ) {
            observer.update( this ); // let observer pull data
        }
    }
    
}
