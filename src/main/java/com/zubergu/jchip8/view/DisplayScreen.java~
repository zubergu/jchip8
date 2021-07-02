package com.zubergu.jchip8.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import com.zubergu.jchip8.model.Display;


public class DisplayScreen extends JPanel implements ModelObserver {
    
    private static final int PIXEL_WIDTH = 10;
    private static final int PIXEL_HEIGHT = 10;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PIXEL_COLOR = Color.GREEN;
    
    private Display display = null;
    
    
    public DisplayScreen( Display d) {
        this.display = d;
        setOpaque( true );
        setBackground( BACKGROUND_COLOR );
        setPreferredSize( new Dimension( Display.NUMBER_OF_COLUMNS * PIXEL_WIDTH,
                 Display.NUMBER_OF_ROWS * PIXEL_HEIGHT ) );
    }
    
    
    @Override
    public void update( ModelObservable subject ) {
        repaint();     
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        for( int row = 0; row < Display.NUMBER_OF_ROWS; row++ ) {
            for( int col = 0; col < Display.NUMBER_OF_COLUMNS; col++ ) {
                
                if( display.getPixel( col, row ) ) {
                    g.setColor( PIXEL_COLOR );
                } else {
                    g.setColor( BACKGROUND_COLOR );
                }
                
                g.fillRect( col * PIXEL_HEIGHT, row * PIXEL_WIDTH, PIXEL_WIDTH, PIXEL_HEIGHT );

            }
        }
    }
    
}
