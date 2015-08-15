/*
  Copyright 2006 by Daniel Kuebrich
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.multilab.sim.models.slsystem;
//import sim.app.lsystem.*;
import java.io.*;

// This keeps track of each rule... 

public class Rule implements Serializable
    {
    // Use exactly this serialVersionUID: the lss classes were serialized under it
    private static final long serialVersionUID = 6750008059574576396L;

    // what to replace
    public byte pattern;
    
    // what to replace it with
    public ByteList replace;
    
    public Rule()
        {
        replace = new ByteList();
        }
    
    public Rule( byte pattern, String replace)
        {
        this.pattern = pattern;
        this.replace = new ByteList();
        SLSystemData.setVector( this.replace, replace);
        }
    }
