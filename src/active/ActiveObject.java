/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package active;

import java.awt.Color;
import java.awt.Graphics2D;
import capabilities.Capability;
import capabilities.Designing;
import ec.util.MersenneTwisterFast;
import interaction.Interactor;
import sim.engine.*;
import sim.models.simpleConsumption.Constants;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.SimplePortrayal2D;
import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public abstract class ActiveObject extends SimplePortrayal2D implements Steppable{
    private static final long serialVersionUID = 20;
    Class type;
    String typeString = "<none>";     // human-readable typeString name
    String shortDesc = "<none>"; // human-readable short description
    String state = "<none>";   // human-readable summary of the state
    int id = 0;       // unique serial number for all artifacts
    Stoppable stopMe;  // must be set before it can be used, after construction
    
    public GenericActionStep action;  // the task list is now ONLY stored in the ActionStep

    boolean logging = false;
    Color color;
    private  Color neutralColor = Color.gray;
    private  Color activeColor = Color.green;
    private boolean neutralFill = false;
    private boolean activeFill = true;
    private boolean active = false;
    
    boolean fill = false;
    
    //Shape myShape;  // Use this once I have a shape drawing library in place
    
    public MersenneTwisterFast rng;
    
    public ActiveObject(){
       
    }
    
    public ActiveObject(final SimState state){
        this.rng = state.random;
        this.color = neutralColor;

    }
    
    public ActiveObject(final SimState state, int serial){
        this.id = serial;
        this.rng = state.random;
        this.color = neutralColor;
    }
    
    public void setRNG(final MersenneTwisterFast r){
        rng = r;
    }
    
     public void setID(int serial){
        id = serial;
    }

    public int getID(){
        return id;
    }
    
    public void setTypeString(String t){
        typeString = t;
        try {
            type = Class.forName(t);
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public String getTypeString(){
        return typeString;
    }
    
    public void setType(Class t){
        type = t;
        if (t != null){
            typeString = t.getSimpleName();
        } else {
             typeString = "null";
        }
    }

    public Class getType(){
        return type;
    }
    
    public void setShortDesc(String d){
        shortDesc = d;
    }

    public String getShortDesc(){
        return shortDesc;
    }
    
    public void setState(String d){
        state = d;
    }

    public String getState(){
        return state;
    }
    
     public void setStoppable(Stoppable s){
        stopMe = s;
    }

    public Stoppable getStoppable(){
        return stopMe;
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    public Color getColor(){
        return color;
    }
    
    public boolean isFill(){
        return fill;
    }
    
    public void clearFill(){
        fill = false;
    }
    
    public void setFill(){
        fill = true;
    }
        public boolean getFill(){
        return fill;
    }
    
    public void setFill(boolean f){
         fill = f;
    }
    
    
    public void setActionStep(GenericActionStep a){
        //actionStep = a;
        action = a;
    }
    
    public void setNeutralColor (Color c){
        neutralColor = c;
    }
    
    public void setActiveColor (Color c){
        activeColor = c;
    }
    
    public Color getNeutralColor(){
        return neutralColor;
    }
    
    public Color getActiveColor(){
        return activeColor;
    }
    
    public void setActive(){
        active = true;
    }
    
    public void setActive(boolean a){
        active = a;
    }
    
    public void clearActive(){
        active = false;
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setLogging(){
        logging = true;
    }

    public void setLogging(boolean l){
        logging = l;
    }
    
    public void clearLogging(){
        logging = false;
    }
    
    public boolean getLogging(){
        return logging;
    }
    
    @Override
    public void step(final SimState state) {
        if (Constants.PRINT_RESULTS_STATUS_AND_DESC  && getLogging() ) {
            System.out.print(">STEP " + state.schedule.getSteps() + " : ");
        }
            if (action != null) {
                action.act();
                if (Constants.PRINT_RESULTS_STATUS_AND_DESC  && getLogging() ) {
                    System.out.println("<... STEP COMPLETED for "
                        + action.getActorType()
                        + " #"
                        + action.getActorID());
                }
            } else {
                if (Constants.PRINT_RESULTS_STATUS_AND_DESC  && getLogging() ) {
                    System.out.println("<...Action step == NULL ");
                }
            }
        
        if (active) {
            setColor(activeColor);
            setFill(activeFill);

        } else {
            setColor(neutralColor);
            setFill(neutralFill);
        }

    }
    
    
    
    @Override
    public final void draw(Object object, Graphics2D graphics, DrawInfo2D info)
        {
        // this code was stolen from OvalPortrayal2D
        int x = (int)(info.draw.x - info.draw.width / 2.0);
        int y = (int)(info.draw.y - info.draw.height / 2.0);
        int width = (int)(info.draw.width);
        int height = (int)(info.draw.height);
        graphics.setColor( color );
        
        if (fill){
            graphics.fillOval(x,y,width, height);
        
        } else {
            graphics.drawOval(x, y, width, height);
        }
        
        }
    

 

}
