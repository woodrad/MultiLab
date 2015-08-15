/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.processes;

import active.Results;
import artifact.PlanarGraphProduct;
import ec.util.MersenneTwisterFast;
import interaction.*;
import sim.util.*;

/**
 *
 * @author Russell Thomas
 */
public class ActRandomlyProcess extends ImplementationProcess {

    public ActRandomlyProcess(MersenneTwisterFast r) {
        super.rng = r;
    }

    @Override
    public Results execute() {  // this is a stub for now.  Default to random design
        results.desc = "ActRandomly....";

        // target is the objects of choice
        // arguments are:                       
        //    -   distances
        //    -   maxScore
        //    -   prActScaling
        //    -   prActMin
        
        DoubleBag distances = new DoubleBag();
        Results res = arguments.get("scores");
        if (res.status.isCompleted() ){
            for (int i = 0; i < res.getOutputs().numObjs; i++){
             distances.add((double)res.getOutputs().get(i) );
            }
        }
        double maxScore = 30;
        Results res1 = arguments.get("maxScore");
        if (res1.status.isCompleted() ){
            Integer sc = (Integer)res1.getOutputs().get(0);
            maxScore = (double)sc;
        }
        double prActScaling = 0.3;
        Results res2 = arguments.get("prActScaling");
        if (res2.status.isCompleted() ){
            prActScaling = (double)res2.getOutputs().get(0);
        }
        double prActMin = 0.001;
        Results res3 = arguments.get("prActMin");
        if (res3.status.isCompleted() ){
            prActMin = (double)res3.getOutputs().get(0);
        }
        
        
        boolean notYetDecided = true;
        int i = 0;

        while (notYetDecided && i < distances.numObjs) {

            double prAct
                    = Math.max(
                            prActScaling
                            * ((double) distances.get(i) / maxScore),
                            prActMin); // Pr(Act) >= 0.1
            double draw = rng.nextDouble();
            if (draw <= prAct) {
                notYetDecided = false;
                Interactor interactor = (Interactor) targets.get(i);
                results.addToOutputs(interactor);
                String postFix = "";
                switch (i + 1) {
                    case 1:
                        postFix = "st";
                        break;
                    case 2:
                        postFix = "nd";
                        break;
                    case 3:
                        postFix = "rd";
                        break;
                    default:
                        postFix = "th";
                        break;
                }
                if (interactor == null) {
                    System.err.println("ERROR: Can't act randomly. Target chosen is null");
                } else {
                    results.desc = results.desc + "one chosen: "
                            + interactor.getTypeString()
                            + " #"
                            + interactor.getID()
                            + ": "
                            + (i + 1) + postFix + " out of " + targets.numObjs;
                }
                break;
            }
            i++;
        }
        if (notYetDecided) {
            results.desc = results.desc + "none chosen";
        }
        results.status.setCompleted();
        return results;
    }

}
