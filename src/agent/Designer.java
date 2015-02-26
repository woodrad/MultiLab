/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agent;

import active.Task;
import capabilities.Adapting;
import capabilities.Designing;
import ec.util.MersenneTwisterFast;
import sim.field.grid.SparseGrid2D;
import sim.util.Bag;
import active.*;
import interaction.*;
import sim.models.simpleConsumption.SimpleConsumption;
import space.*;

/**
 *
 * @author Russell Thomas
 */
public class Designer extends PredefinedAgent {
    final static int NUM_TASKS = 1;
    Task [] taskArray = new Task[NUM_TASKS];
    SparseGrid2D consGrid;
    SparseGrid2D prodGrid;
    Bag targetProduct = new Bag();

    public Designer(final SimpleConsumption simState, int dID, LabeledBag args, SparseGrid2D pGrid, SparseGrid2D cGrid, int distance) {
        this.setRNG(simState.random );
        this.setID(dID);
        this.targetProduct = new Bag();
        this.targetProduct = new Bag();
        this.setType(Designer.class);
        this.setShortDesc("Agent that is able to design products");
        Designing c = new Designing(simState.random);
        Bag cap = new Bag();
        cap.add(c);
        setMyCapabilities(cap);
        setInitialState("Ready to design a product");
        this.consGrid = cGrid;
        this.prodGrid = pGrid;
        
        // RUSS -- I'D RATHER NOT HAVE TO DO THIS INITIALIZATION HERE AND IN ALL PREDEFINED AGENTS
        intializeActionStep();
        
        taskArray[0] = new Task(Designing.class, targetProduct, args); // Design with no object (at this time) 
        
        action.initializeTaskList(taskArray);
    }
    
    // PROBABLY DELETE THIS AS INITIALIZING AND SETTING TASKS IS NOW DONE IN INTERACTOR
    public void initializeTask(Interactor p, LabeledBag args){
        targetProduct.clear();
        targetProduct.add(p);
        taskArray[0] = new Task(Designing.class, targetProduct, args); // Design with an object (at this time) 
        System.err.println("Initializing design task with target: " + p.getType() + " # " + p.getID());
        super.action.initializeTaskList(taskArray);
    }
    
    
    
    //@Override
    //public void act(){
    
    //}
}
