/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package affordances;

/**
 *
 * @author Russell Thomas
 */
import capabilities.*;

public class Designable extends Affordance {
    private static final long serialVersionUID = 11;
    
    public Designable(){
        Designing d = new Designing();
        this.relevantCapabilities.add(d);
    }
}
