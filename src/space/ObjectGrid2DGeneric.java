/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package space;

import sim.field.grid.*;
import utilities.*;
import sim.util.*;


/**
 *
 * @author Russell Thomas
 */
public class ObjectGrid2DGeneric extends MetricSpace implements EngageMetricSpace2D {
   // SparseGrid2D space; 
    
    Int2D focus; // hide the variable "focus" from super class, which is n dimensional
    
    public ObjectGrid2DGeneric(){    
        
    }
    
     public ObjectGrid2DGeneric(SparseGrid2D s){    
        space = s;
    }   
    
    public void setFocus(Int2D f){
        focus = f;
    }
    

    @Override
    public void getRadialNeighbors(final int x, final int y, final int distance, 
        int mode, boolean includeOrigin,
        Bag availableProducts, 
        IntBag xPos, IntBag yPos){
        SparseGrid2D s = (SparseGrid2D)space;
        s.getRadialNeighbors(x, y, distance, mode, includeOrigin, availableProducts, xPos, yPos);      
    }
    
    public Bag getObjectsWithinDistance(final double distance) {
        Bag result = new Bag();
        result.addAll(getObjectsWithinDistance( (int)Math.round(distance)) );
        return result;
    }
    
    public Bag getObjectsWithinDistance(final int distance){
        // scan for products in within the maximium range
        Bag observedObjects = new Bag();
        IntBag xPos = new IntBag();
        IntBag yPos = new IntBag();
        SparseGrid2D s = (SparseGrid2D)space;
        s.getRadialNeighbors((int)focus.x, (int)focus.y, distance, Grid2D.BOUNDED, true, observedObjects, xPos, yPos);
        return observedObjects;
    }
    
    public Bag getObjectsWithinDistance(final int distance, DoubleBag objDistances){ 
        // xPos and yPos are mutable, returns positions of found objects
        // scan for products in within the maximium range
        Bag observedObjects = new Bag();
        IntBag xPos = new IntBag();
        IntBag yPos = new IntBag();
        SparseGrid2D s = (SparseGrid2D)space;
        s.getRadialNeighbors((int)focus.x, (int)focus.y, distance, Grid2D.BOUNDED, true, observedObjects, xPos, yPos);
        EuclideanDistance ec = new EuclideanDistance();
        int [] pt2 = {focus.x, focus.y};
        for (int i = 0; i < observedObjects.numObjs; i++){
            Int2D loc = s.getObjectLocation(observedObjects.get(i));
            int[] pt1 = {loc.x,loc.y};
            objDistances.add(ec.getDistance(pt1, pt2));
        }
        return observedObjects;
    }

}
