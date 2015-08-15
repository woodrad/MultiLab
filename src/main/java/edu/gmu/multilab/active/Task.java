/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.active;

/**
 *
 * @author Russell Thomas
 */

import sim.util.*;
import processes.*;
import space.*;
import interaction.*;

public class Task {
    Class capabilityClass;
    LabeledBag args; // = new Bag();
    Bag targets; // = new Bag();   // 'targets' = 'object' in parts of speech
    ResultsQuery unresolvedTarget;
    LabeledBag unresolvedArguments;
    boolean executeWithoutArgs = true;
    StoppingCondition stopAt;
    Results results;
    Interactor actor;
    
    public Task(final Class c){
        capabilityClass = c;
        stopAt = new StoppingCondition(new GenericCondition());
        Bag labels = new Bag();
        Bag content = new Bag();
        args = new LabeledBag(c, labels, content);
        targets = new Bag();
        results = new Results();
    }
    
    public Task(final Class c, final LabeledBag a){
        capabilityClass = c;
        args = a;
        targets = new Bag();
        stopAt = new StoppingCondition(new GenericCondition());
        results = new Results();

    }
    
    public Task(final Class c, final Bag t, final LabeledBag a){
        capabilityClass = c;
        targets = t;
        args = a;
        stopAt = new StoppingCondition(new GenericCondition());
        results = new Results();

    }
    
    public Task(final Class c, final Bag t, final LabeledBag a, final Results r){
        capabilityClass = c;
        targets = t;
        args = a;
        stopAt = new StoppingCondition(new GenericCondition());
        results = r;

    }
    
    public Task(final Class c, final Bag t, final LabeledBag a, final Results r, StoppingCondition s){
        capabilityClass = c;
        targets = t;
        args = a;
        results = r;
        stopAt = s;

    }
    
    public Task(final Class c, final Bag t, final LabeledBag a, StoppingCondition s){
        capabilityClass = c;
        targets = t;
        args = a;
        stopAt = s;
        results = new Results();
    }
    
    public Task(final Class c, final ResultsQuery t, final LabeledBag a,final Results r, StoppingCondition s){
        capabilityClass = c;
        targets = null;
        unresolvedTarget = t;
        results = r;
        args = a;
        stopAt = s;
       
    }
    
    public Task(final Class c, final ResultsQuery t, final LabeledBag a, StoppingCondition s){
        capabilityClass = c;
        targets = null;
        unresolvedTarget = t;
        args = a;
        stopAt = s;
        results = new Results();
    }
    
    public Task(final Class c, final ResultsQuery a){
        capabilityClass = c;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,labels,contents);
        unresolvedArguments.set(a.label, a);
        targets = new Bag();
        stopAt = new StoppingCondition(new GenericCondition());
        results = new Results();

    }
    
    public Task(final Class c, final Bag t, final ResultsQuery a){
        capabilityClass = c;
        targets = t;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,labels,contents);
        unresolvedArguments.set(a.label, a);
        stopAt = new StoppingCondition(new GenericCondition());
        results = new Results();

    }
    
    public Task(final Class c, final Bag t, final ResultsQuery a, final Results r){
        capabilityClass = c;
        targets = t;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,labels,contents);
        unresolvedArguments.set(a.label, a);
        stopAt = new StoppingCondition(new GenericCondition());
        results = r;

    }
    
    public Task(final Class c, final Bag t, final ResultsQuery a, final Results r, StoppingCondition s){
        capabilityClass = c;
        targets = t;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,labels,contents);
        unresolvedArguments.set(a.label, a);
        results = r;
        stopAt = s;

    }
    
    public Task(final Class c, final Bag t, final ResultsQuery a, StoppingCondition s){
        capabilityClass = c;
        targets = t;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,contents,labels);
        unresolvedArguments.set(a.label, a);
        stopAt = s;
        results = new Results();
    }
    
    public Task(final Class c, final ResultsQuery t, final ResultsQuery a,final Results r, StoppingCondition s){
        capabilityClass = c;
        targets = null;
        unresolvedTarget = t;
        results = r;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,contents,labels);
        unresolvedArguments.set(a.label, a);
        stopAt = s;
    }
    
    public Task(final Class c, final ResultsQuery t, final ResultsQuery a, StoppingCondition s){
        capabilityClass = c;
        targets = null;
        unresolvedTarget = t;
        args = null;
        Bag labels = new Bag();
        Bag contents = new Bag();
        unresolvedArguments = new LabeledBag(Object.class,contents,labels);
        unresolvedArguments.set(a.label, a);
        stopAt = s;
        results = new Results();
    }
    
    
    public void copyResults(Results r){
        if (r != results){
            results.status.setCode(r.status.getCode());
            results.desc = r.desc;
            results.newState = r.newState;
            results.copyObservables(r.getObservables());
            results.copyOutputs(r.getOutputs());
        }
    }
    
    public Results getResults(){
        return results;
    }
    public void copyArgs(TypedBag a){
        if (a != args){
            args.clear();
            args.addAll(a);
        }
    }
    
    public LabeledBag getArgs(){
        if (args != null){
            
            // iterate through the bag to check if any argmuents need resolving
            Bag labels = args.getLabels();
            for (int i = 0; i < labels.numObjs; i++){
                String thisLabel = (String)labels.get(i);
                Results result = args.get(thisLabel);
                if (result.status.isCompleted() 
                        && result.getOutputs().numObjs > 0
                        && result.getOutputs().get(0).getClass() == ResultsQuery.class){
                    ResultsQuery rq = (ResultsQuery)result.getOutputs().get(0);
                    args.set(thisLabel, rq.get() );
                }
            }
            return args;  
            
        } else {
            if (unresolvedArguments != null){
                args = resolve(unresolvedArguments);
                return args;
            } else {
                System.err.println("ERROR: empty argument in Task.");
                return null;
            }
        }
    }
    
    public void copyTargets(Bag t){
        if (t != targets){
            targets.clear();
            targets.addAll(t);
        }
    }
    
    public Bag getTargets(){
        if (targets != null){
            return targets;
        } else {
            if (unresolvedTarget != null){
                targets = unresolvedTarget.get();
                return targets;
            } else {
                System.err.println("ERROR: empty target in Task.");
                return null;
            }
        }
    }
    
    public void resolveTargetsAndArgs() {
        boolean error = false;
        if (targets == null) {
            if (unresolvedTarget != null) {
                targets = unresolvedTarget.get();
            } else {
                error = true;
            }
        }
        if (args == null) {
            if (unresolvedArguments != null) { 
                args =resolve(unresolvedArguments);
            } else {
                error = true;
            }
        }
        if (error) {
            System.err.println("For task " + capabilityClass + ", can't resolve target or arguments.");
        }

    }
    /** recursively resolve the contents of the labeled bag "lb"
     * 
     * @param lb mutable LabeledBag
     * @return 
     */
    public LabeledBag resolve(LabeledBag lb) {
        LabeledBag resolvedArgs = new LabeledBag();
        if (lb != null) {
            Bag labels = lb.getLabels();
            for (int i = 0; i < lb.getNumObjs(); i++) {
                String thisLabel = (String) labels.get(i);
                Object lbObj = lb.get(i);
                if (lbObj.getClass() == ResultsQuery.class) {
                    ResultsQuery rq = (ResultsQuery) lbObj;
                    Bag resolved = rq.get();
                    Object firstItem = new Object();
                    if (resolved.numObjs > 0) {
                        firstItem = resolved.get(0);
                        resolvedArgs.setType(firstItem.getClass());
                    }
                    resolvedArgs.set(thisLabel, resolved);
                } else {
                    resolvedArgs.set(thisLabel, lbObj);
                }
            }
        } else {
            System.err.println("For task " + capabilityClass + ", can't resolve arguments: " + lb + " is null ");

        }
        return resolvedArgs;
    }
    
    public boolean resolve(TypedBag tb){
        if (tb != null ){
            for (int i = 0; i < tb.getNumObjs(); i ++){
                Object lbObj = tb.get(i);
                if (lbObj.getClass() == ResultsQuery.class){
                    ResultsQuery rq = (ResultsQuery)lbObj;
                    Bag resolvedArgs = rq.get();
                    tb.set(i, resolvedArgs);
                }
            }
        } else {
            System.err.println("For task " + capabilityClass + ", can't resolve arguments: " + tb + " is null ");

        }
        return true;
    }
    
    public Bag resolve(ResultsQuery rq){
        return rq.get();
    }
    
    
    public Class getCapabilityClass(){
        return capabilityClass;
    }
    
    public boolean canExecuteWithoutArgs(){
        return executeWithoutArgs;
    }
    
    public void setExecuteWithoutArgs(){
         executeWithoutArgs = true;
    }
    
    public void setExecuteWithoutArgs(boolean b){
         executeWithoutArgs = b;
    }
    
    public void clearExecuteWithoutArgs(){
        executeWithoutArgs = false;
    }
    
    public void setStoppingCondition(StoppingCondition s){
        stopAt = s;
    }
    
    public StoppingCondition getStoppingCondition(){
        return stopAt;
    }
    
    public void setActor(Interactor a){
        actor = a;
    }
    
    public Interactor getActor(){
        return actor;
    }
    

}
