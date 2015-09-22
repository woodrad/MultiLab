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
public class Line {
    
    private String start;
    private String end;
    
    private String fromToString;
    
    public Line(){
        
    }
    
    public void setEndPoints(String s1, String s2){
        if (s1.compareTo(s2) < 0){
            start = s1;
            end = s2;       
        } else {
            start = s2;
            end = s1;
        }
        fromToString = start + "-" + end;
        //System.out.println(s1 + "," + s2 + ": first? " + (s1.compareTo(s2) < 0) + "; " + fromToString);
        
    }
    
    public String getFromToString() {
        String s = "";
        if (fromToString.isEmpty()) {
            if (!start.isEmpty() && !end.isEmpty()) {
                s = start + "-" + end;
            }
            return s;
        } else {
            return fromToString;
        }
    }
    
    public String getStart(){
        return start;
    }
    
    public String getEnd(){
        return end;
    }

}
