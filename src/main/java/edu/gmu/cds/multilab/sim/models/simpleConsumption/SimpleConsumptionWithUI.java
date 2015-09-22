/*
  Copyright 2009 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cds.multilab.sim.models.simpleConsumption;

import sim.engine.*;
import sim.display.*;
import sim.portrayal.grid.*;
import java.awt.*;
import javax.swing.*;
import sim.models.simpleConsumption.Constants;

public class SimpleConsumptionWithUI extends GUIState
    {
    public Display2D display;
    public JFrame displayFrame;

    SparseGridPortrayal2D valueSpacePortrayal = new SparseGridPortrayal2D(); 
    SparseGridPortrayal2D consumerPortrayal = new SparseGridPortrayal2D();
                
    public static void main(String[] args)
        {
        new SimpleConsumptionWithUI().createController();
        }
    
    public SimpleConsumptionWithUI() { super(new SimpleConsumption(System.currentTimeMillis())); }
    public SimpleConsumptionWithUI(SimState state) { super(state); }
    
    // allow the user to inspect the model
    public Object getSimulationInspectedObject() { return state; }  // non-volatile

    public static String getName() { return "Simple Consumption in a Value Space"; }
    
    public void setupPortrayals()
        {
        SimpleConsumption sc = (SimpleConsumption)state;

        // tell the portrayals what to portray and how to portray them
       valueSpacePortrayal.setField(sc.valueSpace);

        consumerPortrayal.setField(sc.consumerGrid);
            
        // reschedule the displayer
        display.reset();

        // redraw the display
        display.repaint();
        }
    
    public void start()
        {
        super.start();  // set up everything but replacing the display
        // set up our portrayals
        setupPortrayals();
        }
            
    public void load(SimState state)
        {
        super.load(state);
        // we now have new grids.  Set up the portrayals to reflect that
        setupPortrayals();
        }

    public void init(Controller c)
        {
        super.init(c);
        
        // Make the Display2D.  We'll have it display stuff later.
        display = new Display2D(400,400,this); // at 400x400, we've got 4x4 per array position
        displayFrame = display.createFrame();
        c.registerFrame(displayFrame);   // register the frame so it appears in the "Display" list
        displayFrame.setVisible(true);

        // attach the portrayals from bottom to top
        display.attach(valueSpacePortrayal,"Value Space");
        display.attach(consumerPortrayal,"Consumers");
        
        // specify the backdrop color  -- what gets painted behind the displays
        display.setBackdrop(Color.black);
        }
        
    public void quit()
        {
        super.quit();
        
        // disposing the displayFrame automatically calls quit() on the display,
        // so we don't need to do so ourselves here.
        if (displayFrame!=null) displayFrame.dispose();
        displayFrame = null;  // let gc
        display = null;       // let gc
        }
        
    }
    
    
    
    
