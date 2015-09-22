/*
  Copyright 2009 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cds.multilab.sim.models.simpleConsumption;

import agent.SimpleDesigner;
import agent.SimpleConsumer;
import artifact.SimpleProduct;
import sim.engine.*;
import sim.field.grid.*;
import sim.util.*;
import sim.models.simpleConsumption.Constants;
import artifact.*;
import active.*;
import capabilities.*;
import agent.*;
import java.lang.reflect.Method;
import interaction.*;
import java.awt.Color;
import space.*;


public /*strictfp*/ class SimpleConsumption extends SimState
    {
    private static final long serialVersionUID = 1;

    
 
    public int numConsumers = 100;
    
    public int numProducts = 20;
    
    public int maxConsumptionDistance = 30;
    
    public double prConsumeScaling = 0.3;
    public double prConsumeMin = 0.001;
    
    public int consumptionPeriod = 20;
    public double adjustmentRate = 0.1;
    
    public boolean logging = false;
 
        
    // getters/setters for mutable properties
    public int getNumConsumers() { return numConsumers; }
    public void setNumConsumers(int val) {if (val > 0) numConsumers = val; }
    
    public int getNumProducts() { return numProducts; }
    public void setNumProducts(int val) {if (val > 0) numProducts = val; }
    
    public int getMaxConsumptionDistance() { return maxConsumptionDistance; }
    public void setMaxConsumptionDistance(int val) {if (val > 0) maxConsumptionDistance = val; }
    
    public double getPrConsumeScaling() { return prConsumeScaling; }
    public void setPrConsumeScaling(double val) {if (val > 0) prConsumeScaling = val; }
    
    public double getPrConsumeMin() { return prConsumeMin; }
    public void setPrConsumeMin(double val) {if (val > 0) prConsumeMin = val; }
    
    public int getConsumptionPeriod() { return consumptionPeriod; }
    public void setConsumptionPeriod(int val) {if (val > 0) consumptionPeriod = val; }
    
    public double getAdjustmentRate() { return adjustmentRate; }
    public void setAdjustmentRate(double val) {if (val > 0) adjustmentRate = val; }
    
    public void setLogging(boolean l){
        logging = l;
    }
    public boolean getLogging(){
        return logging;
    }

    public SparseGrid2D valueSpace = new SparseGrid2D(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
    public SparseGrid2D consumerGrid = new SparseGrid2D(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);


    public SimpleConsumption(long seed)
        { 
        super(seed);
        }
        
    public void start()
        {
        super.start();  // clear out the schedule

        // make new grids
        valueSpace = new SparseGrid2D(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
        consumerGrid = new SparseGrid2D(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
        
 //       SimpleDesigner designer = new SimpleDesigner(this);
                
        AgentFactory agentFactory = new AgentFactory(this);
  //      Interactor test1 = new Interactor();
        Agent designer = agentFactory.build(Designer.class,1);
/*      System.out.println(designer.getType()+ " " + designer.getID()
                + " is an " + designer.getShortDesc()
                + " that is " + designer.getState());
*/        
     
        
        
// initialize the valueSpace with products 
        for(int i=0; i < numProducts; i++)
            {
             PlanarGraphProduct product = new PlanarGraphProduct();
      // only for SimpleDesigner       product.setMyFeatures(designer.designProductTask());
             Bag content = new Bag();
             content.add("random");
             Bag labels = new Bag();
             labels.add("algorithm");
             LabeledBag args = new LabeledBag(String.class,content, labels);
             
             Bag targets = new Bag();
             targets.add(product);
             Task t = new Task(Designing.class,targets, args);
             TaskList tList = new TaskList();
             Bag tasks = new Bag();
             tasks.add(t);
             tList.setTaskStack(tasks);
             designer.setTaskList(tList);
             designer.actAsynchronously();
   // REMOVE THIS          Results r = designer.doTask(t);
   //          System.out.println(i + ": " + r.status.getString() + "; # produced = " + r.outputs.numObjs + ": " + r.outputs.toString());
             product.setID(i);
             // pick a random location
             int x = random.nextInt(Constants.GRID_WIDTH);
             int y = random.nextInt(Constants.GRID_HEIGHT);
             product.setLoc(x, y);
             valueSpace.setObjectLocation(product, x, y);
             //schedule.scheduleRepeating(Schedule.EPOCH + i, 0, product, 1);  // sequential activation
            }
        
        // initialize the valueSpace with consumers
     
        for(int i=0; i < numConsumers; i++)
            {
             //SimpleConsumer consumer = new SimpleConsumer(this);
             Agent consumer = agentFactory.build(Consumer.class, i);
             // pick a random location

             int x = random.nextInt(Constants.GRID_WIDTH);
             int y = random.nextInt(Constants.GRID_HEIGHT);
             consumer.setLoc(x, y);
             consumerGrid.setObjectLocation(consumer, x, y);
             schedule.scheduleRepeating(Schedule.EPOCH, 0, consumer, 1);  // sequential activation
             
            }
        Agent consumer1 = agentFactory.build(Consumer.class, numConsumers);
        consumer1.setLoc(consumerGrid.getWidth()/2, consumerGrid.getHeight()/2);
        consumer1.setNeutralColor(Color.BLUE);
        consumer1.setActiveColor(Color.BLUE);
        if (logging) {consumer1.setLogging();}
        consumerGrid.setObjectLocation(consumer1, consumerGrid.getWidth()/2, consumerGrid.getHeight()/2);
        schedule.scheduleRepeating(Schedule.EPOCH, 0, consumer1, 1);  // sequential activation

        }

    public static void main(String[] args)
        {
        doLoop(SimpleConsumption.class, args);
        System.exit(0);
        }    
    }
    
    
    
    
    
