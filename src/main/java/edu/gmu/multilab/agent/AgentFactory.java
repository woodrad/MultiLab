/*
 * This is used to create classes of agents at run time.
 */

package edu.gmu.multilab.agent;

/**
 *
 * @author Russell Thomas
 */

import capabilities.*;
import sim.engine.*;
import ec.util.MersenneTwisterFast;
import sim.util.Bag;
import sim.models.simpleConsumption.*;
import space.*;

public class AgentFactory {

    private static final long serialVersionUID = 100;
    private MersenneTwisterFast rng;

    SimpleConsumption sim;

    public AgentFactory(final SimState state) {
        this.sim = (SimpleConsumption)state;
        this.rng = state.random; // just in case this needs to be a separate argument to a constructor

    }

    // build arbitrary agent from capability class list
    public Agent build(Bag capabilityClasses, int agentId, Class agentType) {

        Agent newAgent = new Agent(sim);
        Bag c = buildCapabilities(capabilityClasses);
        newAgent.addToMyCapabilities(c);
        newAgent.setID(agentId);
        newAgent.setType(agentType);

        return newAgent;

    }

    // build arbitrary agent from capability class list
    public Agent build(Bag capabilityClasses, int agentId, Class agentType, String agentState) {

        Agent newAgent = new Agent(sim);
        Bag c = buildCapabilities(capabilityClasses);
        newAgent.addToMyCapabilities(c);
        newAgent.setType(agentType);
        newAgent.setState(agentState);
        newAgent.setID(agentId);

        return newAgent;

    }

    // build arbitrary agent from capability class list
    public Agent build(Bag capabilityClasses, int agentId, Class agentType, String agentState, String agentDesc) {

        Agent newAgent = new Agent(sim);
        Bag c = buildCapabilities(capabilityClasses);
        newAgent.addToMyCapabilities(c);
        newAgent.setID(agentId);
        newAgent.setType(agentType);
        newAgent.setState(agentState);
        newAgent.setShortDesc(agentDesc);

        return newAgent;

    }
    
    // build pre-defined agent types
    //  ... from objects
    public Agent build(PredefinedAgent a, int agentId) {   // CAREFUL -- THIS MAY NOT WORK THE WAY YOU THINK IT DOES

        Agent newAgent = new Agent(sim);  // PROBABLY SHOULDN'T CONSTRUCT A **NEW** AGENT WHEN PREDEFINED AGENT EXISTS
        a.setActor(newAgent);
        a.setID(agentId);
        newAgent.setMyCapabilities(a.getMyCapabilities());
        newAgent.setID(agentId);
        newAgent.setType(a.getType());
        newAgent.setShortDesc(a.getShortDesc());
        newAgent.setState(a.getInitialState());
        newAgent.setActionStep(a.action);
        newAgent.setColor(a.getColor());
        newAgent.setFill(a.getFill());
        return newAgent;

    }
    // ... from class names
    public Agent build(Class c, int agentId) {
        PredefinedAgent predefinedAgent = null;
        if (c == Consumer.class){
            TypedBag args = new TypedBag(int.class);
            args.add(sim.consumptionPeriod);
            predefinedAgent = new Consumer(sim,agentId, args,  sim.valueSpace,sim.maxConsumptionDistance, sim.consumerGrid);
        } else if (c == Designer.class){
            Bag content = new Bag();
            content.add("random");
            Bag labels = new Bag();
            labels.add("algorithm");
            LabeledBag args = new LabeledBag(String.class,content, labels);
            predefinedAgent = new Designer(sim,agentId, args,  sim.valueSpace,sim.consumerGrid, sim.maxConsumptionDistance);
        }
        
                
        //return build(predefinedAgent,  agentId);
        return predefinedAgent;

    }
    
        // ... from class names
    public Agent build(Class c, int agentId, Bag obj, LabeledBag args) {
        PredefinedAgent predefinedAgent = null;
        if (c == Consumer.class){
            predefinedAgent = new Consumer(sim,agentId,args,  sim.valueSpace,sim.maxConsumptionDistance, sim.consumerGrid);
        } else if (c == Designer.class){
            predefinedAgent = new Designer(sim,agentId,args, sim.valueSpace,sim.consumerGrid,sim.maxConsumptionDistance);
        }
        
        predefinedAgent.setID(agentId);
                
        return predefinedAgent;     

    }
    
    // general method for constructing a bag of capabilities from their class names
    public Bag buildCapabilities(Bag cClasses) {
        Bag initialCapabilities = new Bag();
        // construct the list of capabilities that match the classes
        for (Object o : cClasses) {
            if (o.getClass() != Class.class) {
                System.err.println(
                        "Agent Factory ERROR: capabilityClasses are not of type Class.  Instead: "
                        + o.getClass());
            } else {  // expand this to be the full list of capability classes
                Class c = (Class) o;
                if (c == Designing.class) {
                    Designing cap = new Designing(rng);
                    initialCapabilities.add(cap);
                } else if (c == Consuming.class) {
                    Consuming cap = new Consuming(rng);
                    initialCapabilities.add(cap);
                }
            }
        }
        return initialCapabilities;
    }

}
