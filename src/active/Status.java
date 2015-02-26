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
import sim.models.simpleConsumption.*;

public class Status {
    int code;
    
    public Status(){
        code = Constants.NULL_STATUS;
    }
    
    public void setCode(int s){
        code = s;  // need to check for valid code
    }
    
    public int getCode(){
        return code;
    }
    
    public String getString(){
        return Constants.STATUS_STRINGS[code + Constants.STATUS_OFFSET];
    }
    
    public void setCompleted(){
        code = Constants.COMPLETED; 
    }
    
    public void setNotCompleted(){
        code = Constants.NOT_COMPLETED;  
    }
    
    public void setNullStatus(){
        code = Constants.NULL_STATUS;
    }
    
    public void setError(){
        code = Constants.ERROR;  
    }
    
    public void clear(){
        code = Constants.NULL_STATUS;  
    }
    
    public void setUnableToPerform(){
        code = Constants.UNABLE_TO_PERFORM;
    }
    
    public void setMissingObjectOfTask(){
        code = Constants.MISSING_TARGET_OF_TASK;
    }
    
    public void setCompletedByStopCondition(){
        code = Constants.COMPLETED_BY_STOP_CONTITION;
    }
    
    public boolean isCompleted(){
        return code == Constants.COMPLETED; 
    }
    
    public boolean isNotCompleted(){
        return code == Constants.NOT_COMPLETED;  
    }
    
    public boolean isError(){
        return code == Constants.ERROR;  
    }
    
    
    public boolean isUnableToPerform(){
        return code == Constants.UNABLE_TO_PERFORM;
    }
    
    public boolean isMissingObjectOfTask(){
        return code == Constants.MISSING_TARGET_OF_TASK;
    }
    
    public boolean isCompletedByStopCondition(){
        return code == Constants.COMPLETED_BY_STOP_CONTITION;
    }


}
