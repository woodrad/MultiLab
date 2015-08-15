/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.interaction;

/**
 *
 * @author Russell Thomas
 */

import affordances.Affordance;
import sim.util.*;
import sim.engine.*;

public interface EngageAffordances {
     
     public Bag getMyAffordances();
     public void setMyAffordances(Bag replacementAffordances);
     public void addToMyAffordances(Bag incrementalAffordances);
     public Bag getMatchingAffordances(Bag otherAffordances);  // returns only those affordances that match myAffordances
     public boolean isInMyAffordances(Affordance a);     // returns true if a is in the list of myAffordances

}
