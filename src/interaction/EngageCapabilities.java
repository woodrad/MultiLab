/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interaction;

import capabilities.Capability;
import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public interface EngageCapabilities {
    
    public Bag getMyCapabilities();
     public void setMyCapabilities(Bag replacementCapabilities);
     public void addToMyCapabilities(Bag incrementalCapabilities);
     public Bag getMatchingCapabilities(Bag otherCapabilities);  // returns only those capabilities that match myCapabilities
     public boolean isInMyCapabilities(Capability c);     // returns true if c is in the list of capabilities


}
