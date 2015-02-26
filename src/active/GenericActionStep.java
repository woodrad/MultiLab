/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package active;

/**
 *
 * @author Russell Thomas
 */
import interaction.*;
import sim.models.simpleConsumption.Constants;
import sim.util.Bag;
import utilities.ObjectUtilities;

public class GenericActionStep implements ActionStep{
    public TaskList taskList = new TaskList();
    Bag initialTaskList = new Bag();
    
    public GenericActionStep(TaskList t){
        this.taskList = t;
    }
    
    @Override
    public void act(){
        ObjectUtilities util = new ObjectUtilities();
        if (Constants.PRINT_RESULTS_STATUS_AND_DESC && taskList.actor.getLogging()) {
            System.out.println("Executing generic action step for " 
                    + taskList.getActor().getType() 
                    + " #" 
                    + taskList.getActor().getID()
            );
        };
        taskList.intialize();
        taskList.execute();
    }
    
    @Override
    public void actAsynchronously(){
        ObjectUtilities util = new ObjectUtilities();
        if (Constants.PRINT_RESULTS_STATUS_AND_DESC && taskList.actor.getLogging()) {
            System.out.println("Executing async action step for " 
                    + taskList.getActor().getType() 
                    + " #" 
                    + taskList.getActor().getID()
            );
        }
        //taskList.intialize();
        taskList.execute();
    }
    
    public Bag getInitialTaskList(){
        return initialTaskList;
    }
    
    public void setInitialTaskList(Bag t){
        initialTaskList.clear();
        initialTaskList.addAll(t);
    }
    
    public void setInitialTaskList(Object [] t){
        initialTaskList.clear();
        initialTaskList.addAll(t);
    }
    
    public void initializeTaskList(){
        taskList.setTaskStackEachStep(initialTaskList);
        taskList.intialize();
    }
    
    public void initializeTaskList(Bag t){
        setInitialTaskList( t);
        taskList.setTaskStackEachStep(initialTaskList);
        taskList.intialize();
    }
    
    public void initializeTaskList(Object [] t){
        setInitialTaskList( t);
        taskList.setTaskStackEachStep(initialTaskList);
        taskList.intialize();
    }
    
    public void initializeTaskList(Object [] t, Interactor a){
        setInitialTaskList( t);
        taskList.setActor(a);
        taskList.setTaskStackEachStep(initialTaskList);
        taskList.intialize();
    }
    
    
    @Override
    public ActiveObject getActor(){
        return taskList.getActor();
    }
    
    @Override
    public void setActor(ActiveObject act){
        Interactor i = (Interactor)act;
        taskList.setActor(i);
    }
    
    @Override
    public int getActorID(){
        return taskList.getActor().getID();
    }
    
    @Override
    public String getActorType(){
        return taskList.getActor().getType().getSimpleName();
    }
    
    @Override
    public void setTaskList(TaskList t){
        taskList = t;
    }
    
    @Override
    public TaskList getTaskList(){
        return taskList;
    }

}
