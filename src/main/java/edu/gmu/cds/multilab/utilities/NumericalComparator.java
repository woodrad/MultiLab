/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.utilities;

/**
 *
 * @author Russell Thomas
 */
import java.util.Comparator;

public class NumericalComparator implements Comparator {
    
    ScoredObject so;
    
    public NumericalComparator (){
        
    }
    
    public void setScoredObject(ScoredObject s){
        so = s;
    }
    
    @Override
    public boolean equals (Object o1){
        ScoredObject so1 = (ScoredObject)o1;
        boolean result = false;
        if (so != null){
            double s0 = so.getScore();
            double s1 = so1.getScore();
            result = s0 == s1;
        }
        return result;
    }
    
    public boolean equals (ScoredObject so1){
        boolean result = false;
        if (so != null){
            double s0 = so.getScore();
            double s1 = so1.getScore();
            result = s0 == s1;
        }
        return result;
    }
    
    @Override
    public int compare (Object o1, Object o2){
        ScoredObject so1 = (ScoredObject)o1;
        ScoredObject so2 = (ScoredObject)o2;
        int result = 0;
        if (so1.getScore() < so2.getScore()){
            result = -1;
        } else if (so1.getScore() > so2.getScore()){
            result = 1;
        }
        return result;
    }
    
    public int compare (ScoredObject so1, ScoredObject so2){
        int result = 0;
        if (so1.getScore() < so2.getScore()){
            result = -1;
        } else if (so1.getScore() > so2.getScore()){
            result = 1;
        }
        return result;
    }

}
