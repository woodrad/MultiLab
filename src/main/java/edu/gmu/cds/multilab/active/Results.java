/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.active;

/**
 *
 * @author Russell Thomas
 */
import sim.util.*;
import space.*;

public class Results {
    public Status status = new Status();
    final private Bag outputs =  new Bag() ;
    final private Bag outputsLabels = new Bag();
    final private Bag observables = new Bag();
    final private Bag observablesLabels = new Bag();
    public String newState = "";        // for process-specific and situation-specific state changes
    public String desc = "";            // human-readable results beyond status
    
    public Results(){
        this.status = new Status();
    }
    
    public Results(Status s){
        this.status = s;
    }
    
    public Results(Status s, Bag out){
        this.status = s;
        if (! outputs.equals(out)){                 /// STOPPED HERE... TRYING TO MAKE LABELED BAGS WORK IN RESULTS
            this.outputs.clear();                   /// RIGHT NOW, SEEMS TOO COMPLICATED VS RETURNING SIMPLE BAGS
            this.outputs.addAll(out);
        }       
    }
    
    public Results(Status s, Bag out, Bag obs){
        this.status = s;
        if (! outputs.equals(out)){
            this.outputs.clear();
            this.outputs.addAll(out);
            this.outputsLabels.resize(this.outputs.numObjs);
            this.outputsLabels.fill("");
        }
        if (! this.observables.equals(obs)){
            this.observables.clear();
            this.observables.addAll(obs);
            this.observablesLabels.resize(this.observables.numObjs);
            this.observablesLabels.fill("");
        }

    }
    
    public Results(Status s, Bag out, Bag obs, String d){
        this.status = s;
        if (! outputs.equals(out)){
            this.outputs.clear();
            this.outputs.addAll(out);            
            this.outputsLabels.resize(this.outputs.numObjs);
            this.outputsLabels.fill("");
            
        }
        if (! this.observables.equals(obs)){
            this.observables.clear();
            this.observables.addAll(obs);
            this.observablesLabels.resize(this.observables.numObjs);
            this.observablesLabels.fill("");
        }
        this.desc = d;
    }
    
    public void resetOutputs(Bag o){  // SHOULD DELETE THIS BECAUSE IT DUPLICATES COPYOUTPUTS
        if (! outputs.equals(o)){
            outputs.clear();
            outputs.addAll(o);
            outputsLabels.resize(this.outputs.numObjs);
            outputsLabels.fill("");

        }
    }
    
    public void copyOutputs(Bag o){
        if (o != outputs){
            outputs.clear();
            outputs.addAll(o);
            outputsLabels.resize(this.outputs.numObjs);
            outputsLabels.fill("");
        }
    }
    
    public void copyObservables(Bag o){
        if (o != observables){
            observables.clear();
            observables.addAll(o);
            observablesLabels.resize(this.observables.numObjs);
            observablesLabels.fill("");
        }
    }
    
    public Results setLabeledOutput(String label, Object o){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < outputsLabels.numObjs; i ++){
           String lab = (String)outputsLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            outputs.set(index,o);
            result.status.setCompleted();
        } else {
            outputs.add(o);
            outputsLabels.add(label);
            result.status.setCompleted();
        }
        return result;
        
    }
    
    public Results getLabeledOutputAsResult(String label){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < outputsLabels.numObjs; i ++){
           String lab = (String)outputsLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            result.addToOutputs(outputs.get(index) );
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result; 
    }
    
    public Bag getLabeledOutput(String label){
        Bag outBag = new Bag();
        int index = -1;
        for (int i = 0; i < outputsLabels.numObjs; i ++){
           String lab = (String)outputsLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            Class oc = outputs.get(index).getClass();
            if (oc == DoubleBag.class){
                DoubleBag dout = (DoubleBag)outputs.get(index);
                for (int i = 0; i < dout.numObjs; i++){
                    outBag.add(dout.get(i));
                } 
            } else {
                if (oc == IntBag.class){
                    IntBag iout = (IntBag)outputs.get(index);
                    for (int i = 0; i < iout.numObjs; i++){
                        outBag.add(iout.get(i));
                    }
 
                } else {
                    if (oc == Bag.class){
                       outBag.addAll((Bag)outputs.get(index));
                    }
                }
            }

        } 
        return outBag; 
    }
        
    public Results getLabeledObservableAsResult(String label){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < observablesLabels.numObjs; i ++){
           String lab = (String)observablesLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            result.addToOutputs(observables.get(index) );
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result; 
    }
    
    public Bag getLabeledObservable(String label){
        Bag result = new Bag();
        int index = -1;
        for (int i = 0; i < observablesLabels.numObjs; i ++){
           String lab = (String)observablesLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            result.addAll((Bag)observables.get(index) );  
        } 
        return result; 
    }
    
    public Results setLabeledObservable(String label, Object o){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < observablesLabels.numObjs; i ++){
           String lab = (String)observablesLabels.get(i);
           if (lab.equals(label)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            observables.set(index,o);
            result.status.setCompleted();
        } else {
            observables.add(o);
            observablesLabels.add(label);
            result.status.setCompleted();
        }
        return result;
        
    }
    

    
    public Bag getOutputs(){
        return outputs;
    }
    
    public Bag getOutputsLabels(){
        return outputsLabels;
    }
    
    public void setOutputsLabels(Bag b){
        if (! b.equals(outputsLabels)){
            outputsLabels.clear();
            outputsLabels.addAll(b);
        }
    }
    
    public Bag getObservables(){
        return observables;
    }
    
    public Bag getObservablesLabels(){
        return observablesLabels;
    }
    
    public void setObservablesLabels(Bag b){
        if (! b.equals(observablesLabels)){
            observables.clear();
            observables.addAll(b);
        }
    }
    
    public void addToOutputs( Object o){
        outputs.add(o);
        outputsLabels.add("");
    }
    
    public void addAllToOutputs(Bag b){
        outputs.addAll(b);
        Bag blankLabels = new Bag(b.numObjs);
        blankLabels.fill("");
        outputsLabels.addAll(blankLabels);
    }
    
    public void addToObservables( Object o){
        observables.add(o);
        observablesLabels.add("");
    }
    
    public void addAllToObservables(Bag b){
        observables.addAll(b);
        Bag blankLabels = new Bag(b.numObjs);
        blankLabels.fill("");
        observablesLabels.addAll(blankLabels);
    }
    
    public void clearOutputs(){
        outputs.clear();
        outputsLabels.clear();
    }

}
