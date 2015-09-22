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
public class PlanarGraphProduct extends Product {
    private static final long serialVersionUID = 19;
    

    
    
    public PlanarGraphProduct(){
        super.initializeAffordances();
        setColor(Color.red);
        setFill();
        setType(PlanarGraphProduct.class);
    }
    public PlanarGraphProduct(final SimState state){
        super.setRNG(state.random);
        super.initializeAffordances();
        setColor(Color.red);
        setFill();
        setType(PlanarGraphProduct.class);
    }
    
    public PlanarGraphProduct(final SimState state, int serial){  
        super.setRNG(state.random);
        super.initializeAffordances();
        setID(serial);
        setColor(Color.red);
        setFill();
        setType(PlanarGraphProduct.class);
    }
    
    
    

     
    @Override
    public void setMyFeatures(Bag f){
        super.setMyFeatures(f); // do the main work in the parent class

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
    
    
    
    @Override
    public void step( final SimState state )
        {
      
        }
    
   
    
}
