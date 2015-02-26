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
public interface EngageFeatures {
    public Bag getMyFeatures();
     public void setMyFeatures(Bag replacementFeatures);
     public void addToMyFeatures(Bag incrementalFeatures);
     public Bag getMatchingFeatures(Bag otherFeatures);  // returns only those features that match myFeatures
     public boolean isInMyFeatures(Feature b);     // returns true if b is in the list of myFeatures

}
