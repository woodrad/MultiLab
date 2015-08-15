/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.multilab.processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import java.util.Comparator;
import sim.util.*;
import sim.models.simpleConsumption.Constants;
import space.*;
import utilities.*;

/**
 *
 * @author Russell Thomas
 */
public class Prioritization extends ImplementationProcess {

    int order = 0;
    Comparator comp;

    public Prioritization(MersenneTwisterFast r) {
        this.rng = r;
    }

    @Override
    public Results execute() {
        results.desc = "Prioritizing....";
        order = Constants.ASCENDING;
        String orderText = "";
        Bag toBeSorted = new Bag();
        IntBag sortOrder = new IntBag();
        Bag sortedObjects = new Bag();
        toBeSorted.addAll(targets);
        BagMethods bm = new BagMethods();
        DoubleBag scores = new DoubleBag();
        
        Results getArgs = arguments.get("distances");
        
        if (getArgs.status.isCompleted()){
        
            Bag distances = getArgs.getOutputs();

            for (int i = 0; i < distances.numObjs; i++) {
                    double d = (double) distances.get(i);
                    scores.add(d);
            }
        
//        System.out.println(">>>target hc#" + targets.hashCode() + "; # objects = " + targets.numObjs);
        if (targets.numObjs > 0) {

            if (comp != null) {
                if (order == Constants.ASCENDING) {
                    orderText = "ascending";
                    //toBeSorted.addAll(targets);
                    toBeSorted.sort(comp);
                } else if (order == Constants.DECENDING) {
                    orderText = "decending";
                    //toBeSorted.addAll(targets);
                    toBeSorted.sort(comp);
                    toBeSorted.reverse();

                }

                results.copyOutputs(toBeSorted);
                results.status.setCompleted();
            } else {
                if (order == Constants.ASCENDING) {
                    orderText = "ascending";
                    //toBeSorted.addAll(targets);
                    if (scores.numObjs > 0) {
                        sortOrder = bm.sortOrder(scores);
                        for (int i = 0; i < scores.numObjs; i++) {
                            sortedObjects.add(null); // fill with nulls since "resize" doesn't work on empty bag
                        }
                        for (int i = 0; i < sortOrder.numObjs; i++) {
                            int index = sortOrder.get(i);
                            sortedObjects.set(index, toBeSorted.get(i));
                        }

                        toBeSorted = sortedObjects;
                    } else {
                        toBeSorted.sort();
                    }
                } else if (order == Constants.DECENDING) {
                    orderText = "decending";
                    //toBeSorted.addAll(targets);
                    if (scores.numObjs > 0) {
                        sortOrder = bm.sortOrder(scores);
                        sortedObjects = new Bag(scores.numObjs);
                        for (int i = 0; i < scores.numObjs; i++) {
                            sortedObjects.add(null); // fill with nulls since "resize" doesn't work on empty bag
                        }
                        for (int i = 0; i < sortOrder.numObjs; i++) {
                            int index = sortOrder.get(i);
                            sortedObjects.set(index, toBeSorted.get(i));
                        }

                        toBeSorted = sortedObjects;
                    } else {
                        toBeSorted.sort();
                    }
                    toBeSorted.reverse();
                }
            }

            results.setLabeledOutput("prioritized objects",toBeSorted);
            scores.sort();
            results.setLabeledOutput("scores",scores);
            results.status.setCompleted();
        } else {
            results.status.setCompletedByStopCondition();
            results.desc = results.desc + "nothing to prioritize";
        }

        if (results.status.isCompleted()) {
            results.desc = results.desc + toBeSorted.numObjs + " items " + orderText;
        }
        if (results.getLabeledOutput("prioritized objects").contains(null)){
            System.err.println("ERROR: priority list includes a null entry");
        }
        } else {
            results.desc = results.desc + "no arguments (scores)";
            results.status.setError();
        }
        return results;
    }

    /*
     public Prioritizing(Bag o, String ord, Comparator c) {
     targets = o;
     order = ord;
     comp = c;
     }
    */
    
    public void setObjects(Bag b){
        targets = b;
    }
    
    void setOrder(int ord){
        order = ord;
    }
    
    public void setOrderAscending(){
        order = Constants.ASCENDING;
    }
    
    public void setOrderDescending(){
        order = Constants.DECENDING;
    }
    
    public void setComparitor(Comparator c){
        comp = c;
    }
    

}
