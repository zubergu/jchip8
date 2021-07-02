package com.zubergu.jchip8.model;

public class Registers {
    
    public static final int NUMBER_OF_V_REGISTERS = 16;
    public static final int STACK_SIZE = 16;
    
    private int[] v;
    private int registerI;
    private int registerDT;
    private int registerST;
    private int registerPC;
    private int registerSP;
    private int[] stack;
    
    public Registers() {
        v = new int[NUMBER_OF_V_REGISTERS];
        stack = new int[STACK_SIZE];
    }
    
    
    public int getV( int x ) {
        return v[x];
    }
    
    public void setV( int x, int value ) {     
        v[x] = value & 0xFF;
    }
    
    public int getI() {
        return registerI;
    }
    
    public void setI( int value ) {    
        registerI = value & 0xFFFF;
    }
    
    public int getDT() {
        return registerDT;
    }
    
    public void setDT( int value ) {     
        registerDT = value & 0xFF;
    }
    
    public void decrementDT() {
        if( registerDT > 0 ) {
            registerDT--;
        }
    }
    
    public int getST() {
        return registerST;
    }
    
    public void setST( int value ) {    
        registerST = value & 0xFF;
    }
    
    public void decrementST() {
        if( registerST > 0 ) {
            registerST--;
        }
    }
    
    public int getPC() {
        return registerPC;
    }
    
    public void setPC( int value ) {       
        registerPC = value & 0xFFFF;
    }
    
    public void incrementPC() {
        registerPC = ( registerPC + 1 ) & 0xFFFF;
    }
    
    public void incrementTwicePC() {
        incrementPC();
        incrementPC();
    }
    
    public void decrementPC() {
        registerPC = ( registerPC - 1 ) & 0xFFFF;
    }
    
    public int getSP() {
        return registerSP;
    }
    
    public void setSP( int value ) {       
        registerSP = value & 0xFF;
    }
    
    public void incrementSP() {
        registerSP = ( registerSP + 1 ) & 0xFF;
    }
    
    public void decrementSP() {
        registerSP = ( registerSP - 1 ) & 0xFF;
    }
    
    public int popStack() {
        int val = stack[registerSP];
        decrementSP();
        return val;
    }
    
    public void pushStack( int value ) {
        incrementSP();
        stack[registerSP] = value;
    }
        
}
