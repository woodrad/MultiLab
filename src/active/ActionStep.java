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
public interface ActionStep {
    int id = -1;
    
    
    public void act();
    public void actAsynchronously();
    
    public ActiveObject getActor();
    public void setActor(ActiveObject a);
    public int getActorID();         // of the actor taking this action step
    public String getActorType();    // of the actor taking this action step
    public void setTaskList(TaskList t);
    public TaskList getTaskList();
        
    

}
