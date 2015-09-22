/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.interaction;

import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public interface EngageStructures {
     public Bag getMyStructures();
     public void setMyStructures(Bag replacementStructures);
     public void addToMyStructures(Bag incrementalStructures);
     public Bag getMatchingStructures(Bag myStructures);  // returns only those structures that match myStructures
     public boolean isInMyStructures(Structure b);     // returns true if b is in the list of myStructures


}
