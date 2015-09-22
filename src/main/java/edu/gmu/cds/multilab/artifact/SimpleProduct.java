/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.artifact;

import java.awt.Color;
import java.awt.Graphics2D;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.*;
import sim.models.simpleConsumption.Constants;
import utilities.*;

/**
 *
 * @author Russell Thomas
 */
public class SimpleProduct extends Product {
    private static final long serialVersionUID = 21;
    int id = 0;
    
    Int2D loc;
    
    Bag features = new Bag();
    String featureString = "";
    
    
    // constructors
    public SimpleProduct(){
        setColor(Color.red);
        setFill();
        
    }
    public SimpleProduct(final SimState state){
        super.setRNG(state.random);
        setColor(Color.red);
        setFill();

    }
    
    public SimpleProduct(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
        setColor(Color.red);
        setFill();
    }
    
    public void setFeatureString(String s){
        featureString = s;
    }
    
    public String getFeatureString(){
        return(featureString);
    }
    public void setID(int i){
        id = i;
    }
    public int getID(){
        return(id);
    }
    public void setLoc(Int2D l){
        loc = new Int2D(l.x,l.y);
    }
    
     public void setLoc(int x1, int y1){
        loc = new Int2D(x1,y1);
    }
     
     public Int2D getLoc(){
        return(loc);
    }
     
    public void setFeatures(Bag f){
        features.clear();
        features.addAll(f);
        String fs = "";
        String comma = "";
        for(int i = 0; i < f.numObjs; i ++){
            Line l = (Line)f.get(i);
            String s  = l.getFromToString();
            if (i > 0) {comma = ",";}
            fs = fs + comma + s;
        }
        setFeatureString(fs);
    }
    
    public Bag getFeatures(){
        return features;
    }
    
    @Override
    public void step( final SimState state )
        {
      
        }
    
   
    
}
