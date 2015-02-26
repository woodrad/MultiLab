/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interaction;

import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public interface EngageSituations {
     public Bag getMySituations();
     public void setMySituations(Bag replacementSituations);
     public void addToMySituations(Bag incrementalSituations);
     public Bag getMatchingSituations(Bag mySituations);  // returns only those situations that match mySituations
     public boolean isInMySituations(Situation b);     // returns true if b is in the list of mySituations


}
