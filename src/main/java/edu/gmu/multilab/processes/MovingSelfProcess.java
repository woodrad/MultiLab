/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import sim.field.grid.SparseGrid2D;
import space.ObjectGrid2DGeneric;
import sim.util.*;

/**
 *
 * @author Russell Thomas
 */
public class MovingSelfProcess extends ImplementationProcess {
    ObjectGrid2DGeneric space ;
    
    public MovingSelfProcess(MersenneTwisterFast r){
        super.rng = r;
    }

    public void setSpace(ObjectGrid2DGeneric s){
        space = s;
    }
    
    @Override
    public Results execute(){  
        results.desc = "Moving self....";
        // actor is myself (agent or artifact to be moved)
        // target is the space to be moved in
        // argument 0 is the destination in the space
        if (actor != null & targets != null && targets.numObjs > 0
            && arguments != null && arguments.getNumObjs() > 0){
                        // the target is the space to be scanned
           ObjectGrid2DGeneric s =  new ObjectGrid2DGeneric(); 
           SparseGrid2D sp = (SparseGrid2D)targets.get(0);   // TEMPORARY -- Make this code more general and robust 
           space = s;
           space.setSpaceObject(sp);
           Double2D newLoc = (Double2D)arguments.get(0);
           Int2D newLocInt = new Int2D((int)Math.round(newLoc.x), (int)Math.round(newLoc.y));
           sp.setObjectLocation(actor, newLocInt);
           results.desc = results.desc + actor.getActorType() + " #" + actor.getID() + " to (" 
                   + newLocInt.x + ", " + newLocInt.y + ") in " + sp.toString();
            results.status.setCompleted();
            
        } else {
            if (actor != null){
                results.desc = results.desc + "Can't move " + actor.getActorType() + " #" + actor.getID() ;
            } else {
                results.desc = results.desc + "No actor to move";
            }
            results.status.setNotCompleted();
        }
        return results;
    }

}
