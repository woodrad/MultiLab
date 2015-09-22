/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.affordances;

import capabilities.Searching;

/**
 *
 * @author Russell Thomas
 */
public class Searchable extends Affordance{
        private static final long serialVersionUID = 12;
    
    public Searchable(){
        Searching s = new Searching();
        this.relevantCapabilities.add(s);
    }

}
