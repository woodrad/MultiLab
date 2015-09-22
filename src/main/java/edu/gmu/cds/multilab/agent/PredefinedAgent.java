/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.agent;

/**
 *
 * @author Russell Thomas
 */

import sim.util.*;


public abstract class PredefinedAgent extends Agent {
   Bag initialTaskList = new Bag();
   String initialState = "";
 
 
    
    @Override
    public void setShortDesc(String d){
        super.setShortDesc(d);
    }
    
    @Override
    public String getShortDesc(){
        return super.getShortDesc();
    }
    
    @Override
    public void setType(Class d){
        super.setType(d);
    }
    
    @Override
    public Class getType(){
            return super.getType();

    }
    

    public String getInitialState(){
        return initialState;
    }
    
    public void setInitialState(String s){
        initialState = s;
    }
    
    /*
    @Override
    public void setColor(Color c){
        color = c;          
    }
    
    @Override
    public Color getColor(){
        return color;  
    }
    @Override
     public boolean isFill(){
        return fill;
    }
    
    @Override
    public void clearFill(){
        fill = false;
    }
    
    @Override
    public void setFill(){
        fill = true;
    }
    
    @Override
    public boolean getFill(){
        return fill;
    }
    
    @Override
    public void setFill(boolean f){
         fill = f;
    }
    
    */
    

    
    @Override
    public void act(){
        super.act(); // this simply executes all the tasks on the taskStack after initializing them
    }
    
    @Override
    public void actAsynchronously(){
        super.actAsynchronously(); // this simply executes all the tasks on the taskStack
    }
    
    
}
