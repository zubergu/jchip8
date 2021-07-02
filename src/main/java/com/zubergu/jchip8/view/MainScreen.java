package com.zubergu.jchip8.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainScreen extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadFileItem;
    private JMenu controlMenu;
    private JMenuItem startStopItem;    
    
    public MainScreen() {
        super( "JCHIP8 by zubergu" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setupMenuBar();
    }
    
    
    public JMenuItem getLoadFileItem() {
        return loadFileItem;
    }
    
    public JMenuItem getStartStopItem() {
        return startStopItem;
    }
    
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu( "File" );
        controlMenu = new JMenu( "Control" );
        loadFileItem = new JMenuItem( "Load" );
        startStopItem = new JMenuItem( "Start" );
        
        menuBar.add( fileMenu );
        menuBar.add( controlMenu );
        
        fileMenu.add( loadFileItem );
        controlMenu.add( startStopItem );
        
        setJMenuBar( menuBar );
    }
}
