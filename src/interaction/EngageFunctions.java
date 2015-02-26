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
public interface EngageFunctions {
    public Bag getMyFunctions();
     public void setMyFunctions(Bag replacementFunctions);
     public void addToMyFunctions(Bag incrementalFunctions);
     public Bag getMatchingFunctions(Bag myFunctions);  // returns only those affordances that match myFunctions
     public boolean isInMyFunctions(Function b);     // returns true if b is in the list of Functions

}
