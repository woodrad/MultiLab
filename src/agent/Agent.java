/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agent;

import interaction.Interactor;
import sim.engine.*;
import sim.util.*;
import sim.models.simpleConsumption.*;
import active.*;


/**
 *
 * @author Russell Thomas
 */
public  class Agent extends Interactor{
    private static final long serialVersionUID = 10;
    MutableInt2D loc = new MutableInt2D();
    
    public Agent(){
        
    }
    
    public Agent(final SimState simState) { 
        rng = simState.random;
        setColor( Constants.NEUTRAL_COLOR );
        clearFill();
        TaskList taskList = new TaskList();
        taskList.setActor(this);
        super.setActionStep( new GenericActionStep(taskList) );
    }
    
    public MutableInt2D getLoc(){
        return loc;
    }
    
    public void setLoc(Int2D l){
        loc.x = l.x;
        loc.y = l.y;
    }
    
    public void setLoc(int x , int y){
        loc.x = x;
        loc.y = y;
    }
    
    public void setLoc(MutableInt2D l){
        loc = l;
    }
    
    public void intializeActionStep(){
        TaskList taskList = new TaskList();
        taskList.setActor(this);
        super.setActionStep( new GenericActionStep(taskList) );
    }
    
       
}
    
    
    
    


