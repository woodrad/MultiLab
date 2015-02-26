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
import sim.util.*;

public class StateVariables {

    Bag varNames = new Bag();
    Bag varTypes = new Bag();
    Bag varValues = new Bag();
    public int numObjs = 0;

    public StateVariables() {

    }

    public StateVariables(Bag names, Bag types, Bag values) {
        if ((names.numObjs != types.numObjs || names.numObjs != values.numObjs)
                || names.numObjs == 0) {

            // soft error condition -- don't initialize any variables
        } else {
            varNames.addAll(names);
            varTypes.addAll(types);
            varValues.addAll(values);
            numObjs = varNames.numObjs;
        }
    }
    
    // infer types from class names of variables
    public StateVariables(Bag names, Bag values) {
        if ( names.numObjs != values.numObjs
                || names.numObjs == 0) {

            // soft error condition -- don't initialize any variables
        } else {
            Bag types = new Bag();
            for (Object o: values){
                types.add(o.getClass());
            }
            varNames.addAll(names);
            varTypes.addAll(types);
            varValues.addAll(values);
            numObjs = varNames.numObjs;
        }
    }
    
    public void add(String name, Class type, Object value) {
        if (!isDuplicateName(name)) {
            varNames.add(name);
            varTypes.add(type);
            varValues.add(value);
            numObjs++;
        } else {
            // soft error -- duplicate state variable name
        }
    }
    
    // infer type from class name of value
    public void add(String name, Object value) {
        if (!isDuplicateName(name)) {
            varNames.add(name);
            varTypes.add(value.getClass());
            varValues.add(value);
            numObjs++;
        } else {
            // soft error -- duplicate state variable name
        }
    }
    
    public void clear(){
        varNames = new Bag();
        varTypes = new Bag();
        varValues = new Bag();
        int numObj = 0;
    }
    
    public void removeByName(String name){
        // iterate through the names looking for a match
        int matchIndex = findMatchingString(name,varNames);
       
        if (matchIndex >= 0){
            varNames.removeNondestructively(matchIndex);
            varTypes.removeNondestructively(matchIndex);
            varValues.removeNondestructively(matchIndex);
        }
    }
    
    public boolean isDuplicateName(String name){
        int matchIndex = findMatchingString(name,varNames);
        return (matchIndex >= 0);

    }
    
    public Object getValueByName(String name){
        Object o = null;
        int matchIndex = findMatchingString(name,varNames);
        if (matchIndex >=0){
            o = varValues.get(matchIndex);
        }
        return o;  
    }
    
    public void updateValueByName(String name, Object value){
        Object o = null;
        int matchIndex = findMatchingString(name,varNames);
        if (matchIndex >=0){
            if (value.getClass() == (Class)varTypes.get(matchIndex)){
                varValues.set(matchIndex,value);
            } else {
                // soft error - type mismatch
            }  
        } else {
            // soft error - no matching name
        }
    }
    
    public void updateValuesByNames(Bag names, Bag values){
        if (names.numObjs != values.numObjs){
            // soft error - number of names must match number of values
        } else {
            for (int i = 0; i < names.numObjs; i++){
                String name = (String)names.get(i);
                Object value = values.get(i);
                updateValueByName(name,value);
            }
        }
    }
    
    public void updateValues(Bag values){
        // No type checking!  Faster but slightly more dangerous
        if (values.numObjs != numObjs){
            // soft error - number of values must match number of state variables
        } else {
            varValues.clear();
            varValues.addAll(values);
        }
    }
    
    public Class getTypeByName(String name){
        Class c = null;
        int matchIndex = findMatchingString(name,varNames);
        if (matchIndex >=0){
            c = (Class)varTypes.get(matchIndex);
        }
        
        return c;
    }
    
    private int findMatchingString(String str, Bag b){
                // iterate through the names looking for a match
        int matchIndex = -1;
        if (str == null || str.equals("")) {
            // soft error -- don't try to match a null or empty string
        } else {
            for (int i = 0; i < b.numObjs; i++){  // this works if the bag is empty, i.e. loop doesn't execute
                String s = (String)b.get(i);
                if (s.equals(str)){
                    matchIndex = i;
                    
                    break;
                }
            }
        }
        return matchIndex;
    }
    
    

}
