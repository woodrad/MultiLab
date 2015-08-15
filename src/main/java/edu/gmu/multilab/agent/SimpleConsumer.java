/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.multilab.agent;

import artifact.SimpleProduct;
import ec.util.MersenneTwisterFast;
import sim.field.grid.*;
import sim.portrayal.*;
import sim.portrayal.simple.*;
import sim.util.*;
import sim.engine.*;
import java.awt.*;
import utilities.*;
import sim.models.simpleConsumption.Constants;
import sim.models.simpleConsumption.SimpleConsumption;
import artifact.*;

public class SimpleConsumer extends Agent {
    private static final long serialVersionUID = 1;
    MersenneTwisterFast rng;
    private int id = 0;

    public boolean getHasProduct() { return hasProduct; }
    public void setHasProduct(boolean val) { hasProduct = val; }
    public boolean hasProduct = false;
    private int consumptionTimer = 0;
    private Color noProductColor = Color.gray;
    private Color productColor = Color.green;
    
    
    private PlanarGraphProduct myProduct;
   
        
    int x;
    int y;
        

     
    // constructors
    public SimpleConsumer() { 
            setType(SimpleConsumer.class);
    }
    
    public SimpleConsumer(SimState state) {
        rng = state.random;
        setColor( noProductColor );
        clearFill();
        setType(SimpleConsumer.class);

    }
    
    
    public SimpleConsumer(int x1, int y1) {
        super.setLoc(x1, y1);
        setColor( noProductColor );
        clearFill();
        setType(SimpleConsumer.class);

    }
    
    public SimpleConsumer(int x1, int y1,SimState state) {
        super.setLoc(x1, y1);
        rng = state.random;
        setColor( noProductColor );
        clearFill();
        setType(SimpleConsumer.class);
    }

    // at present we have only one algorithm: value iteration.  I might
    // revise this and add our alternate (TD) algorithm.  See the papers.
    /*
    public void setLoc(Int2D l) {
        super.setLoc(l);
    }
    public void setLoc(int x1, int y1) {
        super.setLoc(x1, y1);
    }

    public void setID(int i) {
        id = i;
    }
    public int getID() {
        return (id);
    }
    */
    
    public void setMyProduct(PlanarGraphProduct p){
        myProduct = p;
    }
    public PlanarGraphProduct getMyProduct(){
        return(myProduct);
    }

    public void act(final SimState state) {
        boolean consumedThisCycle = false;
        int deltaX = 0;
        int deltaY = 0;

// SPACE = METRIC SPACE
        final SimpleConsumption conspace = (SimpleConsumption) state;
        SparseGrid2D metricSpace = conspace.valueSpace;

        
// SITUATION
        if (hasProduct) {
            consumptionTimer--;
            if (consumptionTimer <= 0){
                hasProduct = false;
                consumptionTimer = 0;
            }
        } else {

//SCAN
            // scan for products in within the maximium range
            Bag availableProducts = new Bag();
            IntBag xPos = new IntBag();
            IntBag yPos = new IntBag();
            metricSpace.getRadialNeighborsAndLocations(
                    loc.x,
                    loc.y,
                    conspace.getMaxConsumptionDistance(),
                    Grid2D.BOUNDED, true,  availableProducts, xPos, yPos);


// decide to consume, with probability proportional to distance
            if (availableProducts.numObjs > 0) {
                IntBag sortPositions = new IntBag();
                DoubleBag distances = new DoubleBag(availableProducts.numObjs);
//PRIORITIZE
                // sort products by distance
                int[] myXY = {loc.x, loc.y};
                for (int i = 0; i < availableProducts.numObjs; i++) {
                    EuclideanDistance dist = new EuclideanDistance();
                    PlanarGraphProduct prod = (PlanarGraphProduct) availableProducts.get(i);
                    Int2D otherLoc = prod.getLoc();
                    int[] otherXY = {otherLoc.x, otherLoc.y};
                    double thisDist = dist.getDistance(otherXY, myXY);
                    distances.add(thisDist);

                }
                if (availableProducts.numObjs == 1) {
                    sortPositions.add(0);
                } else {

                    DoubleBag sortedDistances = new DoubleBag();
                    try {
                        sortedDistances = (DoubleBag) distances.clone();
                    } catch (CloneNotSupportedException e) {
                        System.err.println("Exception: " + e);
                    }
                    sortedDistances.sort();
                    IntBag positionsList = new IntBag(sortedDistances.numObjs);
                    // initialize position indexes
                    for (int i = 0; i < sortedDistances.numObjs; i++) {
                        positionsList.add(i);
                    }

                    for (int i = 0; i < sortedDistances.numObjs; i++) {
                        int pos = -1;
                        for (int j = 0; j < positionsList.numObjs; j++) {
                            int thisPos = positionsList.get(j);
                            if (distances.get(thisPos) == sortedDistances.get(i)) {
                                positionsList.remove(j);
                                pos = thisPos;
                                break;
                            }
                        }
                        sortPositions.add(pos);
                    }
                }
// ACT_PROBABILISTICALLY    
            // now choose a product to consume, with probability inverse to distance
                // stop when a consumption choice is made.
                boolean notYetConsuming = true;
                int i = 0;
                double maxDistance = conspace.getMaxConsumptionDistance();
                while (notYetConsuming && i < sortPositions.numObjs) {
                    int thisIndex = sortPositions.get(i);
                    PlanarGraphProduct p = (PlanarGraphProduct)availableProducts.get(i);
                    //System.out.println(id + ": Product #" + p.id + "; distance = " + distances.get(thisIndex) / maxDistance);
                    
                    double prConsume = 
                            Math.max(
                                    conspace.prConsumeScaling  
                                            * (distances.get(thisIndex) / maxDistance), 
                                    conspace.prConsumeMin); // Pr(Consume) >= 0.1
                    double draw = conspace.random.nextDouble();
                    //System.out.println(id + ": prConsume = " + prConsume + "; draw = " + draw + "; consume = " + (draw <= prConsume));
                    if (draw <= prConsume) {
                        
// CONSUME
                        // consume this product
                        setHasProduct(true);
                        notYetConsuming = false;
                        consumptionTimer = conspace.consumptionPeriod;
                        setMyProduct((PlanarGraphProduct)availableProducts.get(i));
                        
                        consumedThisCycle = true;
                        int targetX = myProduct.getLoc().x;
                        int targetY = myProduct.getLoc().y; 
                        deltaX = (int)Math.round(((double)targetX - (double)loc.x) * conspace.adjustmentRate);
                        deltaY = (int)Math.round(((double)targetY - (double)loc.y) * conspace.adjustmentRate);
                    }
                    i++;
                }

            }

        // share the consumption experience socially
        // based on consumption experience and social feedback, adjust the ideal product (position in value space)
        
// MOVE
        if(consumedThisCycle){
            conspace.consumerGrid.setObjectLocation(this, loc.x + deltaX, loc.y + deltaY);
            setLoc(loc.x + deltaX, loc.y + deltaY);
        }
        
        }
    }

    public void step( final SimState state )
        {
        act(state);
        if( hasProduct ){
            setColor( productColor );
            setFill();
        } else {
            setColor( noProductColor );
            clearFill();
        }
        }

   
    
        
        
    }
