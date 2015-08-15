/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.processes;
/**
 *
 * @author Russell Thomas
 */

import sim.models.simpleConsumption.Constants;
import sim.util.Bag;
import utilities.Line;
import ec.util.MersenneTwisterFast;
import active.*;
import interaction.*;

public class DesignProcess extends ImplementationProcess {

    public DesignProcess(MersenneTwisterFast r) {
        super.rng = r;
    }
    
    @Override
    public Results execute(){  // this is a stub for now.  Default to random design
        if (targets.numObjs > 0){
            Interactor p = (Interactor)targets.get(0);
            p.setMyFeatures(doRandomDesign() );
            results.addToOutputs(p);
            results.desc = "...Designing a product randomly";
            results.status.setCompleted();
        } else {
            System.err.println("Can't design a product because there is no target set.");
            results.status.setError();
        }
        return results;
    }

    public Bag doRandomDesign() {
        Bag newDesign = new Bag();
        String newDesignString = ""; // just used to avoid duplicates
        // pick a the number of lines to draw, N = (0,MAX_LINES)
        String comma = "";
        int numLines = rng.nextInt(Constants.MAX_LINES - 1) + 1; // at least one line
        // randomly draw N lines
        for (int i = 0; i < numLines; i++) {
            boolean done = false;
            int safety = Constants.MAX_LINES * 5;
            if (i > 0) {
                comma = ",";
            }
            while (!done && safety > 0) { // keep trying until a non-duplicate line is drawn
                // pick random start node
                Line newLine = new Line();
                int startIndex = rng.nextInt(Constants.NUM_NODES);
                String start = Constants.PRODUCT_NODES[startIndex];
                int endIndex = rng.nextInt(Constants.FEASABLE_END_NODES[startIndex].length);
                String end = Constants.FEASABLE_END_NODES[startIndex][endIndex];
                String newLineString = "";
                newLine.setEndPoints(start, end); // this automatically sorts them correctly
                newLineString = newLine.getFromToString();

                if (!newDesignString.contains(newLineString)) {
                    newDesignString = newDesignString + comma + newLineString;
                    newDesign.add(newLine);
                    done = true;
                } else {
                    safety--;
                    done = false;
                }
            }
        }
        // System.out.println(newDesignString);
        return newDesign;
    }

}
