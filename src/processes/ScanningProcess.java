/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import active.Results;
import ec.util.MersenneTwisterFast;
import space.*;
import sim.util.*;
import utilities.*;
import sim.field.grid.*;

/**
 *
 * @author Russell Thomas
 */
public class ScanningProcess  extends ImplementationProcess {
    ObjectGrid2DGeneric space ;
    int scanLimit = 0;
    Int2D focus;
    
    public ScanningProcess(MersenneTwisterFast r) {
        this.rng = r;
    }
    
    public ScanningProcess(MersenneTwisterFast r, ObjectGrid2DGeneric s) {
        this.rng = r;
        this.space = s;
    }
    
    public ScanningProcess(MersenneTwisterFast r, ObjectGrid2DGeneric s, double l) {
        this.rng = r;
        this.space = s;
        this.scanLimit = (int)l;
    }
    
    public ScanningProcess(MersenneTwisterFast r, ObjectGrid2DGeneric s, int l) {
        this.rng = r;
        this.space = s;
        this.scanLimit = l;
    }
    
    public ScanningProcess(MersenneTwisterFast r, ObjectGrid2DGeneric s, int l, Int2D f) {
        this.rng = r;
        this.space = s;
        this.scanLimit = l;
        this.focus = new Int2D(f.x, f.y);
    }
        
    public void setSpace(ObjectGrid2DGeneric s){
        space = s;
    }
    
    public void setScanLimit(int l){
        scanLimit = l;
    }
    
    public void setScanLimit(double l){
        scanLimit = (int)l;
    }
    
    public void setFocus(Int2D f){
        focus = new Int2D(f.x,f.y);
        if (space != null){
            space.setFocus(focus);
        }
    }
    
    @Override
    public Results execute(){  // this is a stub for now.  Default to random design
//        System.out.println("...> Starting Scanning with results #" + results.hashCode() + "; output # " + results.getOutputs().hashCode());
        results.desc = "Scanning...";
        if (arguments.getNumObjs() < 2){
            results.status.setError();
        } else {
            // the target is the space to be scanned
           ObjectGrid2DGeneric s =  new ObjectGrid2DGeneric(); 
           SparseGrid2D sp = (SparseGrid2D)targets.get(0);   // TEMPORARY -- Make this code more general and robust 
           space = s;
           space.setSpaceObject(sp);
           space.setFocus(focus);
           
            //setSpace(s);
            // first parameter is the distance
            Double dist =0.0;
            Results res1 = arguments.get("distance");
            if (res1.status.isCompleted()){
                Object obj = res1.getOutputs().get(0);
                if (obj.getClass() == Integer.class){
                    dist = Double.valueOf((Integer)obj);
                } else{
                    dist = (double)obj;
                }
                
                setScanLimit(dist);
            }

            
            // second parmaeter is focal point
            Results res2 = arguments.get("focus");
            if (res2.status.isCompleted()){
                MutableInt2D foc = (MutableInt2D)res2.getOutputs().get(0);
                Int2D newFocus = new Int2D(foc.x, foc.y);
                setFocus( newFocus );
                space.setFocus(newFocus);
                results.desc = results.desc + " from (" + focus.x + ", " + focus.y + ") radius " + scanLimit + "...";
            }
            if ((res1.status.isCompleted() || res1.status.isCompletedByStopCondition()) 
                   &&
                    (res2.status.isCompleted() || res2.status.isCompletedByStopCondition()) ) {
                    
                    DoubleBag objDistances = new DoubleBag();
                    Bag foundObj = space.getObjectsWithinDistance(scanLimit,objDistances);
                    //results.addToOutputs(foundObj);
                    results.setLabeledOutput("found objects", foundObj);
                    results.setLabeledOutput("distances", objDistances);
                    results.status.setCompleted();

                    String semicolon = "";
                    if (results.getOutputsLabels().numObjs > 0){
                        Bag labels = results.getOutputsLabels();
                        for (int i = 0; i < labels.numObjs; i++){
                            String lab = (String)labels.get(i);
                            results.desc = results.desc + semicolon + lab + ": ";
                            semicolon = "; ";
                            Bag theseResults = results.getLabeledOutput(lab);
                            results.desc = results.desc + " " + theseResults.numObjs + " of type ";

                            String className = "<null>";
                            Bag foundTypes = new Bag();

                            if (theseResults.numObjs > 0){
                                for (Object o: theseResults){
                                    Class c = o.getClass();
                                    className = c.getSimpleName();
                                    if (!foundTypes.contains(className) ){
                                        foundTypes.add(className);
                                    }
                                }
                            }
                            String comma = "";
                            for (Object o: foundTypes){
                                String str = (String)o;
                                results.desc = results.desc + comma + str;
                                comma = ", ";
                            }
                        }
                    } else {
                        results.desc = results.desc + "found " + results.getOutputs().numObjs + " objects of type ";
                        String comma = "";
                        String className = "<null>";
                        Bag foundTypes = new Bag();

                        if (results.getOutputs().numObjs > 0){
                            for (Object o: results.getOutputs()){
                                Class c = o.getClass();
                                className = c.getSimpleName();
                                if (!foundTypes.contains(className) ){
                                    foundTypes.add(className);
                                }
                            }
                        }
                        for (Object o: foundTypes){
                            String str = (String)o;
                            results.desc = results.desc + comma + str;
                            comma = ", ";
                        }
                    }
                } else {
                results.status.setError();
            }
        }
        return results;
    }
}
