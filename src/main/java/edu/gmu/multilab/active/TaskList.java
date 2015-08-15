/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.multilab.active;

import affordances.Affordance;
import capabilities.Capability;
import interaction.Interactor;
import sim.util.Bag;
import utilities.*;
import space.*;
import sim.models.simpleConsumption.Constants;

/**
 *
 * @author Russell Thomas
 */
public class TaskList {
    boolean doNotClearOnStop = false;
    Bag taskStack = new Bag();
    Bag nextArgs = new Bag();

    Bag taskStackEachStep = new Bag(); // every step, this is used to initialize the task stack

    Interactor actor;

    public TaskList() {

    }

    public TaskList(Interactor a) {
        this.actor = a;
    }
    
    public void setDoNotClearOnStop(){
        doNotClearOnStop = true;
    }
    
    public void clearDoNotClearOnStop(){
        doNotClearOnStop = false;
    }
    
    public boolean getDoNotClearOnStop(){
        return doNotClearOnStop;
    }

    public void setActor(Interactor a) {
        actor = a;
    }

    public ActiveObject getActor() {
        return actor;
    }

    public void setTaskStack(Bag s) {
        taskStack.clear();
        taskStack.addAll(s);
    }

    public Bag getTaskStack() {
        return taskStack;
    }
    
    public void clear(){
        taskStack.clear();
    }
    
    public void push(Task t){
        taskStack.push(t);
    }

    public void setTaskStackEachStep(Bag s) {
        taskStackEachStep.clear();
        taskStackEachStep.addAll(s);
    }

    public Bag getTaskStackEachStep() {
        return taskStackEachStep;
    }

    public String getTaskCapabilitiesAsString() {
        String result = "";
        String comma = "";
        for (Object o : taskStack) {
            Task t = (Task) o;
            result = result + comma + t.getCapabilityClass().getSimpleName();
            comma = ", ";

        }
        return result;
    }
    
    
    private void setNextArgs(Bag b){
        nextArgs.clear();
        nextArgs.addAll(b);
    }

    public void intialize() {
        setTaskStack(taskStackEachStep);
    }

    public Results execute() {
        Results result = null;
        Bag taskListCopy = new Bag();
        taskListCopy.addAll(taskStack);  // for clearing results at end of execution
        
        if (actor != null) {
            int i = 0;
            while (taskStack.numObjs > 0) {
                Object o = taskStack.pop();
                Task t = (Task) o;
//                System.out.println("About to execute " + t.capabilityClass.getSimpleName()  
//                        + " on target #" + t.targets.hashCode() + " with intended results #" + t.results.hashCode()
//                        + " and intended output #" + t.results.getOutputs().hashCode()
//                    );
                
                t.setActor(actor);
                doTask(t);                      // EXECUTE THE TASK
                
//                System.out.println("Result for " + t.capabilityClass.getSimpleName() + "; results #" + t.results.hashCode() 
//                    + ": " + t.results.desc);
//                System.out.println("...output #" + t.results.getOutputs().hashCode() + ": " + t.results.getOutputs().numObjs + " objects");
                                
                
                if (t.stopAt != null) {
                    t.stopAt.condition.update();                // UPDATE THE STATE VARIABLES
                    if (t.stopAt.condition.test().isTrue()) {   // TEST FOR TASK LIST STOPPING CONDITION
                        t.results.desc = t.results.desc + ": " + t.stopAt.condition.getTestString();
                        t.results.status.setCompletedByStopCondition();
                    }
                }
                if (Constants.PRINT_RESULTS_STATUS_AND_DESC && actor.getLogging()) {
                    System.out.println(i + ": " + t.results.desc + ": " + t.results.status.getString());
                }
                if (t.results.status.isCompletedByStopCondition()) {
                    // stop execution of tasks by clearing the task stack and exiting the loop
                    if (!doNotClearOnStop){
                        taskStack.clear();
                    }
                    break;
                }
                i++;
                result = t.results;
            }
            
        } else {
            result = new Results();
            result.status.setError();
            System.err.println("ERROR: can't execute task list because 'actor' is not set");
        }
        // clear results associated with each task before exiting
        for (int i = 0; i < taskListCopy.numObjs; i ++){
            Task t = (Task)taskListCopy.get(i);
            t.results.clearOutputs();
        }
        return result;
    }

    // generic task execution method
    public boolean doTask(Task t) {
        //Results result = t.results;
        
//        System.out.println("... pre-execution hashcodes: results #" + t.results.hashCode() 
//                + "; output #" + t.results.getOutputs().hashCode());
        final ObjectUtilities util = new ObjectUtilities();
        
        // if either the target or arguments are unresolved, resolve them now
        t.resolveTargetsAndArgs();
        //plan the task by selecting all myCapabilities that are under the capabilityClass of the task

        
        Class topLevelClass = t.getCapabilityClass();

            Bag compatibleCapabilities = actor.getCompatibleCapabilities(topLevelClass);

            if (compatibleCapabilities.numObjs == 0) {
                t.results.status.setUnableToPerform();  // because no myCapabilities compatible with task
                System.err.println(actor.getType() + " " + actor.getID()
                        + " : no capabilities compatible with task: " + topLevelClass.getSimpleName());
                System.err.println("...(" + util.getClassNames(actor.getMyCapabilities()) + ")");
            } else {

                TypedBag theArgs = t.getArgs();
                if (theArgs.getNumObjs() == 0) {
                    //if no arguments, check to see if the task can be performed with no args (i.e. no object)
                    if (t.args.getNumObjs() == 0 && !t.canExecuteWithoutArgs()) {

                        t.results.status.setMissingObjectOfTask();
                        System.err.println(this
                                + " : missing object for task " + topLevelClass.getSimpleName());

                    } else {

                        //System.out.println("...executing " + topLevelClass.getSimpleName() +" without object(s)");
                        Class cClass = t.getCapabilityClass();
                        Bag viableCapabilities = actor.getCompatibleCapabilities(cClass);
                        Capability c = (Capability) viableCapabilities.get(0);
                        c.getProcess().setActor(actor);
                        t.copyResults( c.getProcess().execute(t.getTargets(), t.getArgs(), t.getResults()) ); // t.getArgs() is empty bag
                    }
                } else {
                    //check to see if I have relevant myCapabilities. If not, exit with NOT_COMPLETED status
                    Interactor interactor = null;
                    if (t.targets.numObjs > 0 && t.getTargets().get(0).getClass() == Interactor.class) {
                        interactor = (Interactor) t.getTargets().get(0); // for now, just one interactor as argument

                        //query the myAffordances of the object of the task
                        Bag matchingAffordances = interactor.getMatchingAffordances(compatibleCapabilities);
                        if (matchingAffordances.numObjs == 0) {
                            t.results.status.setUnableToPerform();  // because no myCapabilities compatible with object's myAffordances
                            System.err.println(actor.getType() + " " + actor.getID()
                                    + " : no capabilities compatible with object of task: "
                                    + interactor.getType()
                                    + " " + interactor.getID());
                            System.err.println("...my capabilities = (" + util.getClassNames(actor.getMyCapabilities()) + ")");
                            System.err.println("...object affords = (" + util.getClassNames(interactor.getAffordedCapabilities()) + ")");
                        } else {
                            Affordance a = (Affordance) matchingAffordances.get(0); // get the first affordance, for now
                            // get the relevant myCapabilities of this affordance that also match mine
                            Bag viableCapabilities = actor.getMatchingCapabilities(a.getRelevantCapabilities());
                            //choose a viable capability
                            if (viableCapabilities.numObjs == 0) {
                                t.results.status.setError();  // this shouldn't happen
                                System.err.println(actor.getType() + " " + actor.getID()
                                        + " : ERROR: no viable capabilities");
                                System.err.println("...my capabilities = (" + util.getClassNames(actor.getMyCapabilities()) + ")");
                                System.err.println("...object affords relevant capabilities = (" + util.getClassNames(a.getRelevantCapabilities()) + ")");
                            } else {

                                Capability c = (Capability) viableCapabilities.get(0);
                                /*  System.err.println(this.getClass().getSimpleName() + " " + this.getID() 
                                 + " : Executing process '" + c.getProcess().getClass().getSimpleName() 
                                 + "' with " + t.getArgs().numObjs + " arguments"); */

                                // execute the task using the chosen capability
                                c.getProcess().setActor(actor);
                                t.copyResults(c.getProcess().execute(t.getTargets(), t.getArgs(), t.getResults()) );
                                nextArgs.clear();
                                //    System.err.println("..." + result.status.getString() ); 
                            }
                        }
                    } else {
                        Capability c = (Capability) compatibleCapabilities.get(0);
                        c.getProcess().setActor(actor);
                        t.copyResults( c.getProcess().execute(t.getTargets(), t.getArgs(), t.getResults()) );
                        nextArgs.clear();
                    }
                }

            }

//        System.out.println("... post-execution hashcodes: results #" + t.results.hashCode() 
//                + "; output #" + t.results.getOutputs().hashCode() 
//                + " with " + t.results.getOutputs().numObjs + " objects");
        return true;
    }

}
