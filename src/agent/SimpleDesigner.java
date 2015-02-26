/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agent;

import java.awt.Color;
import java.awt.Graphics2D;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.*;
import ec.util.MersenneTwisterFast;
import sim.models.simpleConsumption.Constants;
import utilities.*;
import capabilities.*;
import active.*;
import interaction.*;
import sim.portrayal.SimplePortrayal2D;

/**
 *
 * @author Russell Thomas
 */
public class SimpleDesigner extends  Agent{
    private static final long serialVersionUID = 2;
    MersenneTwisterFast rng;
   
    
    // constructors
 
    
    public SimpleDesigner(final SimState state){
        rng = state.random;
        Designing designCapability = new Designing(rng);
        Bag c = new Bag();
        c.add(designCapability);
        //this.addToMyCapabilities(c);
    } 
    
    // methods
    
    public void step( final SimState state )
        {
        
        }
    

    /*
    public Bag designProductTask(){
        //default method is random
        return designProductTask(Constants.RANDOM_DESIGN);
    }
    
    public Bag designProductTask(int method){
        Bag newDesign = new Bag();
        switch (method) {
            case Constants.RANDOM_DESIGN:
                newDesign.addAll(designCapability.doRandomDesign());
                break;
            default: 
                newDesign.addAll(designCapability.doRandomDesign());
                break;
            
        }
        return newDesign;
    }
    
    */
}
