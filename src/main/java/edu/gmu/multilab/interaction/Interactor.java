/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.interaction;

import capabilities.Capability;
import affordances.Affordance;
import characteristics.Characteristic;
import sim.engine.*;
import sim.util.Bag;
import sim.portrayal.*;
import active.*;
import capabilities.Designing;
import utilities.*;
import space.*;
import characteristics.*;

/**
 *
 * @author Russell Thomas
 
 An Interactor is an artifact or an agent that can be treated as a bundle
 of Affordances for purposes of interaction. 
 * 
 * Agents can interact with artifacts and other agents.  Artifacts can interact with
 * agents and other artifacts.  Interactors can also (in principle) interact with themselves
 * (i.e. self as object for action, reflection, or even self-modification).
 * 
 *  - Affordances are combinations of Capabilities and (artifact) Characteristics that
 *     enable Behaviors and Functions.  Affordances can be generic or specific to Situations
 *  - Features are functional components of the Interactor (i.e. what it's composed of)
 *  - Capabilities are the contextual functionality of the Interactor
 *  - Behaviors are what actions or reactions the Interactor can perform (incl. internal)
 *  - Functions are purposes the Interactor can be used for
 *  - Structures are how the Features are linked and interrelated
 *  - Situations are the contexts and settings within which the Interactor operates
 * 
 * Nearly all interactions are mediated and enabled by Affordances, contextualized by the
 * Situations of the Interactor and Interactee.
 * 
 */
public class Interactor  extends ActiveObject
        implements Steppable, 
                    EngageAffordances, 
                    EngageCapabilities, 
                    EngageCharacteristics,
                    EngageFeatures,
                    EngageBehaviors,
                    EngageFunctions,
                    EngageStructures,
                    EngageSituations,
                    ActionStep
{
     Bag  myAffordances = new  Bag ();  // What afforandances apply to me?
     Bag myFeatures = new Bag();  // "myFeatures" are the functional components that sit "behind" Characteristics
     String featureString = "";
     Bag myFunctions = new Bag();  // What functions can I be used for?
     Bag myBehaviors = new Bag();  // What behaviors are possible for me?
     Bag myCapabilities = new Bag();  // What capabilities for action do I have 
                                      // (i.e. the "verb" in tasks that execute)
     Bag mySituations = new Bag();    // What mySituations guide my behavior and constrain my capabilities? 
     LabeledBag myCharacteristics;
     
    //String typeString = null;     // human-readable typeString
    //String state = null;   // human-readable summary of the state
    //int id = 0;       // unique serial number for all artifacts
//    Stoppable stopMe;  // must be set before it can be used, after construction
    
    
 //   public GenericActionStep action;  // the task list is now ONLY stored in the ActionStep

    final BagMethods bagMethods = new BagMethods();
    
    // constructors
    public Interactor(){
        
    }
    public Interactor(final SimState state){   
        super.setRNG(state.random);
        TaskList taskList = new TaskList();
        taskList.setActor(this);
        this.action = new GenericActionStep(taskList);
    }
    
    public Interactor(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
        TaskList taskList = new TaskList();
        taskList.setActor(this);
        this.action = new GenericActionStep(taskList);
   
    }
    

/* default is defined in ActiveObject 
// STEPPABLE   
     @Override
    public void step(SimState state){
        
    }
 */  

// STOPPABLE -- this is in ActiveObject
/*     public void setStoppable(Stoppable s){
        stopMe = s;
    }

    public Stoppable getStoppable(){
        return stopMe;
    }
*/
     @Override
    public void setActionStep(GenericActionStep a){
        action = a;
    }
    
     @Override
    public void setTaskList(TaskList t){

        t.setActor(this);
        action.setTaskList(t);
    }
    
     @Override
    public TaskList getTaskList(){
        return action.getTaskList();
    }
    
// AFFORDANCES     
     @Override
     public Bag getMyAffordances(){
         return myAffordances;
     }
     @Override
     public void setMyAffordances(Bag replacementAffordances){
         myAffordances = new Bag(replacementAffordances);
     }
     @Override
     public void addToMyAffordances(Bag incrementalAffordances){
         myAffordances.addAll(incrementalAffordances);
     }
     
     @Override
     public Bag getMatchingAffordances(Bag myCapabilities){  
        // returns only those myAffordances that match an agent's myCapabilities
         Bag result = new Bag();
         for (Object o : myAffordances){
             Affordance thisAffordance = (Affordance)o;
                if (thisAffordance.doesMatchCapabilities(myCapabilities)){
                    result.add(thisAffordance);
                }
             
         }
         return result;
         
     }
     
     public Bag getAffordedCapabilities(){
         Bag result = new Bag();
         for (Object o: myAffordances){
             Affordance a = (Affordance)o;
             result.addAll(a.getRelevantCapabilities());
         }
         
         return result;
     }
     @Override
     public boolean isInMyAffordances(Affordance a){     
         // returns true if a is in the list of myAffordances
         return bagMethods.containsClass(myAffordances,a.getClass());
     }
     
// BEHAVIORS
     @Override
     public Bag getMyBehaviors(){
         Bag result = new Bag();
         return result;
     }
     @Override
     public void setMyBehaviors(Bag replacementBehaviors){
         
     }
             
     @Override
     public void addToMyBehaviors(Bag incrementalBehaviors){
         
     }
     @Override
     public Bag getMatchingBehaviors(Bag myBehaviors){  
         // returns only those myBehaviors that match myBehaviors
         Bag result = new Bag();
         return result;
     }
     @Override
     public boolean isInMyBehaviors(Behavior c){
         // returns true if c is in the list of Behaviors
         return bagMethods.containsClass(myBehaviors,c.getClass());
     }
     
// CAPABILITIES
     @Override
     public Bag getMyCapabilities(){
         return myCapabilities;
     }
     @Override
     public void setMyCapabilities(Bag replacementCapabilities){
         myCapabilities.clear();
         myCapabilities.addAll(replacementCapabilities);
     }
             
     @Override
     public void addToMyCapabilities(Bag incrementalCapabilities){
         myCapabilities.addAll(incrementalCapabilities);
     }
     @Override     
     public Bag getMatchingCapabilities(Bag c) {
        Bag result = new Bag();
        for (Object o1 : c) {
            for (Object o2 : myCapabilities) {
                if (o1.getClass() == o2.getClass()) {
                    result.add(o2);
                }
            }
        }
        return result;
    }
     
     public Bag getCompatibleCapabilities(Class cl){
         // returns all myCapabilities that are same class as cl or are a sub-class
         // http://stackoverflow.com/questions/3504870/how-to-test-if-one-java-class-extends-another-at-runtime
         Bag result = new Bag();
         for (Object o : myCapabilities){
             Capability c = (Capability)o;
             Class thisClass = c.getClass();
             if (thisClass == cl || 
                     cl.isAssignableFrom(thisClass)){
                result.add(c); 
             }
         }
         return result;
     }
     
     @Override
     public boolean isInMyCapabilities(Capability c){
         // returns true if c is in the list of myCapabilities
         return bagMethods.containsClass(myCapabilities,c.getClass());
     }
     
// CHARACTERISTICS     
     @Override
     public Bag getMyCharacteristics(){
         Bag result = new Bag();
         return result;
     }
     @Override
     public void setMyCharacteristics(Bag replacementCharacteristics){
         myCharacteristics.clear();
         Bag rLabels = new Bag();
         for (int i = 0; i < replacementCharacteristics.numObjs; i++){
             Characteristic ch = (Characteristic)replacementCharacteristics.get(i);
             myCharacteristics.add(ch.getLabel(),ch);
         }
     }
             
     @Override
     public void addToMyCharacteristics(String label, Object obj){
         GenericCharacteristic ch = new GenericCharacteristic();
         ch.setLabel(label);
         ch.setValue(obj);
         myCharacteristics.add(ch);
         
     }
     @Override
     public Bag getMatchingCharacteristics(Bag characteristics){  
         // returns only those myCapabilities that match myCharacteristics
         Bag result = new Bag();
         Bag theseLabels = new Bag();
         for (int i = 0; i < characteristics.numObjs; i++){
             Characteristic ch = (Characteristic)characteristics.get(i);
             theseLabels.add(ch.getLabel());
         }
         for (int i = 0; i < theseLabels.numObjs; i++){
             Results findResult = myCharacteristics.get((String)theseLabels.get(i) ); 
             if (findResult.status.isCompleted()){
                 result.add(findResult.getOutputs() );
             }
         }
         return result;
     }
     
     @Override
    public Results getMatchingCharacteristic(String label){  
        // returns only those myCapabilities that match myCharacteristics
        Results result = new Results();

        Results findResult = myCharacteristics.get(label ); 
        if (findResult.status.isCompleted()){
            Characteristic ch = (Characteristic)findResult.getOutputs().get(0);
            result.addToOutputs(ch);
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result;
     }
     
     @Override
     public boolean isInMyCharacteristics(Characteristic c){
         // returns true if c is in the list of characteristics
         boolean result;
         Bag myLabels = new Bag();
         for (int i = 0; i < myCharacteristics.getNumObjs(); i++){
             Characteristic ch = (Characteristic)myCharacteristics.get(i);
             myLabels.add(ch.getLabel());
         }
         String thisLabel = c.getLabel();
         result = myLabels.contains(thisLabel);
         return result; 
     }
     

// FEATURES
     public void setFeatureString(String s){
        featureString = s;
    }
     
    
    @Override
    public Bag getMyFeatures(){
        return myFeatures;
    }
    
     @Override
     public void setMyFeatures(Bag replacementFeatures){
         myFeatures.clear();
        myFeatures.addAll(replacementFeatures);
        //setFeatureString(fs);    // need to add this as side effect
         
     }
             
     @Override
     public void addToMyFeatures(Bag incrementalFeatures){
         
     }
     @Override
     public Bag getMatchingFeatures(Bag myFeatures){  
         // returns only those myFeatures that match myFeatures
         Bag result = new Bag();
         return result;
     }
     @Override
     public boolean isInMyFeatures(Feature c){
         // returns true if c is in the list of Features
         return false;
     }
    
    public String getFeatureString(){  // RUSS: CONSIDER MAKING THIS MORE GENERAL
        return(featureString);
    }
// FUNCTIONS
    @Override
    public Bag getMyFunctions(){
        Bag result = new Bag();
        return result;
    }
    
     @Override
     public void setMyFunctions(Bag replacementFunctions){

         
     }
             
     @Override
     public void addToMyFunctions(Bag incrementalFunctions){
         
     }
     @Override
     public Bag getMatchingFunctions(Bag myFunctions){  
         // returns only those functuctions that match myFunctions
         Bag result = new Bag();
         return result;
     }
     @Override
     public boolean isInMyFunctions(Function c){
         // returns true if c is in the list of Functions
         return false;
     }
     
// SITUATIONS
    @Override
    public Bag getMyStructures(){
        Bag result = new Bag();
        return result;
    }
    
     @Override
     public void setMyStructures(Bag replacementStructures){
      
         
     }
             
     @Override
     public void addToMyStructures(Bag incrementalStructures){
         
     }
     @Override
     public Bag getMatchingStructures(Bag myStructures){  
         // returns only those structures that match myStructures
         Bag result = new Bag();
         return result;
     }
     @Override
     public boolean isInMyStructures(Structure c){
         // returns true if c is in the list of Structures
         return false;
     }
     
    // SITUATIONS
    @Override
    public Bag getMySituations(){
        Bag result = new Bag();
        return result;
    }
    
     @Override
     public void setMySituations(Bag replacementSituations){
   
         
     }
             
     @Override
     public void addToMySituations(Bag incrementalSituations){
         
     }
     @Override
     public Bag getMatchingSituations(Bag mySituations){  
         // returns only those mySituations that match mySituations
         Bag result = new Bag();
         return result;
     }
     @Override
     public boolean isInMySituations(Situation c){
         // returns true if c is in the list of Situations
         return false;
     }
     


     
    
     @Override
    public void setActor(ActiveObject aObj){
        action.setActor(aObj);
    }
    
     @Override
    public Interactor getActor(){
        return (Interactor) action.getActor();
    }
    
     @Override
    public void act(){
        action.act();
    }
     @Override
    public void actAsynchronously(){
        action.actAsynchronously();
    }
    
     @Override
    public int getActorID(){  // of the actor taking this action step
        int ID = action.getActorID();
        return ID;
    } 
    
     @Override
    public String getActorType(){ // of the actor taking this action step
        return action.getActorType();
    }
    
 

}
