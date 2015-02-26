/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interaction;

import sim.util.Bag;
import characteristics.*;
import active.*;

/**
 *
 * @author Russell Thomas
 */
public interface EngageCharacteristics {
    public Bag getMyCharacteristics();
     public void setMyCharacteristics(Bag replacementCharacteristics);
     public void addToMyCharacteristics(String label, Object value);
     public Bag getMatchingCharacteristics(Bag characteristics);  // returns only those Characteristics that match myCharacteristics
     public Results getMatchingCharacteristic(String label);  // returns only those Characteristics that match myCharacteristics
     public boolean isInMyCharacteristics(Characteristic c);     // returns true if c is in the list of Characteristics

}
