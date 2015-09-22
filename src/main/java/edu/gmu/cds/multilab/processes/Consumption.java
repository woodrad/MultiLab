/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import interaction.*;
import affordances.*;

/**
 *
 * @author Russell Thomas
 */
public class Consumption extends ImplementationProcess {
    
    public Consumption(MersenneTwisterFast r){
        super.rng = r;
    }

    
    @Override
    public Results execute(){  // this is a stub for now.  Default to random design
        results.desc = "Consume....";
        if (targets != null & targets.numObjs > 0){
            Object o = targets.get(0);
            Interactor thisInteractor = (Interactor)o;
            Consumable consumable = new Consumable();
            if (thisInteractor.isInMyAffordances(consumable)){
                results.desc = results.desc + thisInteractor.getTypeString() + " #" + thisInteractor.getID() ;
                results.addToOutputs(thisInteractor);
                results.status.setCompleted();
            } else {
                results.desc = results.desc + "but " + thisInteractor.getTypeString() + " is not consumable";
                results.status.setNotCompleted();
            }
        }
        
        return results;
    }

}
