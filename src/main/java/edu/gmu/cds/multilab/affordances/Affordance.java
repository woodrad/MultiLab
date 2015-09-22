/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.cds.multilab.affordances;

/**
 *
 * @author Russell Thomas
 *
 * An Affordance is an interaction and manipulation relationship between an
 * artifact's Characteristics and an agent's Capabilities. This can be seen as a
 *
 *
 */
import sim.util.Bag;
import capabilities.*;

public abstract class Affordance {

    private static final long serialVersionUID = 11;
    Bag relevantCharacteristics = new Bag();
    Bag relevantCapabilities = new Bag();
    Bag relevantSituations = new Bag();

    public boolean isCompatible(Affordance a) {
        // two affordances are compatible (but not necessarily identical) if they match
        //   characteristics,  capabilities, and situations
        boolean test = doesMatchCharacteristics(a.getRelevantCharacteristics())
                && doesMatchCapabilities(a.getRelevantCapabilities())
                && doesMatchSituations(a.getRelevantSituations());
        return test;
    }

    public Bag getRelevantCharacteristics() {
        return relevantCharacteristics;
    }

    public void setRelevantCharacteristics(Bag c) {
        relevantCharacteristics.clear();
        relevantCharacteristics.addAll(c);
    }

    public Bag getRelevantCapabilities() {
        return relevantCapabilities;
    }

    public void setRelevantCapabilities(Bag c) {
        relevantCapabilities.clear();
        relevantCapabilities.addAll(c);
    }

    public Bag getRelevantSituations() {
        return relevantSituations;
    }

    public void setRelevantSituations(Bag c) {
        relevantSituations.clear();
        relevantSituations.addAll(c);

    }

    public boolean doesMatchCharacteristics(Bag c) {
        boolean result = false;
        // if both are null or empty, then return TRUE
        if ((c == null || c.numObjs == 0)
                && (relevantCharacteristics == null || relevantCharacteristics.numObjs == 0)) {
            result = true;
        } else {

            for (Object o1 : c) {
                for (Object o2 : relevantCharacteristics) {
                    if (o1.getClass() == o2.getClass()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean doesMatchSituations(Bag s) {
        boolean result = false;
        // if both are null or empty, then return TRUE
        if ((s == null || s.numObjs == 0)
                && (relevantSituations == null || relevantSituations.numObjs == 0)) {
            result = true;
        } else {
            for (Object o1 : s) {
                for (Object o2 : relevantSituations) {
                    if (o1.getClass() == o2.getClass()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean doesMatchCapabilities(Bag c) {
        boolean result = false;
        // if both are null or empty, then return TRUE
        if ((c == null || c.numObjs == 0)
                && (relevantCapabilities == null || relevantCapabilities.numObjs == 0)) {
            result = true;
        } else {
            for (Object o1 : c) {
                for (Object o2 : relevantCapabilities) {
                    if (o1.getClass() == o2.getClass()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

}
