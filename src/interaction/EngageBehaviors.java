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
public interface EngageBehaviors {
    public Bag getMyBehaviors();
     public void setMyBehaviors(Bag replacementBehaviors);
     public void addToMyBehaviors(Bag incrementalBehaviors);
     public Bag getMatchingBehaviors(Bag otherBehaviors);  // returns only those behaviors that match myBehaviors
     public boolean isInMyBehaviors(Behavior b);     // returns true if b is in the list of myBehaviors


}
