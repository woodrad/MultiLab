/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import interaction.Interactor;

/**
 *
 * @author Russell Thomas
 */
public class SituationProcess extends ImplementationProcess{

    public SituationProcess(MersenneTwisterFast r) {
        super.rng = r;
    }
    
    @Override
    public Results execute(){  // this is a stub for now.
        if (targets.numObjs > 0){
            Interactor interactor = (Interactor)targets.get(0);
        }
        if (arguments.getNumObjs() > 0){
            Object arg = arguments.get(0);
            if (arg.getClass() == boolean.class){
                
            }
        }
        
        results.desc = "Situating...";
        results.status.setCompleted();
        return results;
    }
}
