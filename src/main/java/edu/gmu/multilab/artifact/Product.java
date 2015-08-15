/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.artifact;

/**
 *
 * @author Russell Thomas
 */

import sim.engine.*;
import affordances.*;
import sim.util.*;

public class Product extends Artifact {
    private static final long serialVersionUID = 20;
    Int2D loc;
    
    public Product(){
        
    }
    public Product(final SimState state){
        super.setRNG(state.random);
        setFill();
        // add affordances
        

    }
    
    public Product(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
        setFill();
    }
    
    public void initializeAffordances(){
        Designable d = new Designable();
        Consumable c = new Consumable();
      //Sortable s = new Sortable();
        Bag afford = new Bag();
        afford.add(d);
        afford.add(c);
        this.addToMyAffordances(afford);
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

}
