/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import sim.util.*;
import artifact.*;

/**
 *
 * @author Russell Thomas
 */
public class Adaptation extends ImplementationProcess {
    
    public Adaptation(MersenneTwisterFast r) {
        super.rng = r;
    }
    
    @Override
    public Results execute(){   
        Results result = new Results();
        result.desc = "Adapting....";
        if (targets != null && targets.numObjs > 0
                && arguments != null && arguments.getNumObjs() > 0){
            // target has 
            //   - product
            Product thisProduct = (Product)targets.get(0);
            
            // arguments has:
            //   - my location
            //   - adjustment rate
            MutableDouble2D myLoc = (MutableDouble2D)arguments.get(0);
            double adjRate = (double)arguments.get(1);
            
            int targetX = thisProduct.getLoc().x;
            int targetY = thisProduct.getLoc().y; 
            double deltaX = ((double)targetX - (double)myLoc.x) * adjRate;
            double deltaY = ((double)targetY - (double)myLoc.y) * adjRate;

            // result
            //   - new location of actor
            Double2D newLoc = new Double2D(((double)myLoc.x + deltaX), (double)myLoc.y + deltaY);
            result.desc = result.desc + "new location = (" + newLoc.x + ", " + newLoc.y + ")";
            result.addToOutputs(newLoc);          
            result.status.setCompleted();
        } else{
            result.status.setNotCompleted();
        }
        return result;
    }
}
