/*
 * Pre-built agent
 */

package agent;

/**
 *
 * @author Russell Thomas
 */
import active.StoppingCondition;
import capabilities.*;
import sim.util.*;
import sim.engine.*;
import sim.field.grid.SparseGrid2D;
import active.*;
import ec.util.MersenneTwisterFast;
import processes.*;
import space.*;
import artifact.*;
import utilities.BooleanOrNA;
import sim.util.Int2D;
import sim.models.simpleConsumption.*;
import space.*;
import interaction.*;
import java.awt.Color;

public class Consumer extends PredefinedAgent {
    boolean hasProduct = false;
    private int consumptionTimer = 0;
    Product myProduct;
    private int maxConsumptionTimer = 20;
    private double adjRate = 0;
    Bag availableProducts = new Bag();
    SparseGrid2D consGrid;
    SparseGrid2D prodGrid;   
    
    MutableDouble2D realLoc = new MutableDouble2D();  // for marginal adjustments each step. Avoids getting stuck to due to rounding
    
    Results situationResults = new Results();
    Results scanConsumerResults = new Results();
    Results scanProductResults = new Results();
    Results prioritizingResults = new Results();
    Results actRandomlyResults = new Results();
    Results consumingResults = new Results();
    Results adaptingResults = new Results();
    Results movingSelfResults = new Results();
    
    StoppingCondition updateAndTestAfterSituating;
    StoppingCondition updateAndTestAfterScanning;
    StoppingCondition updateAndTestAfterPrioritizing;
    StoppingCondition updateAndTestAfterActRandomly ;
    StoppingCondition updateAndTestAfterConsuming;
    StoppingCondition updateAndTestAfterAdjusting;
    StoppingCondition updateAndTestAfterMovingSelf;

public Consumer(final SimpleConsumption simState, int cID, TypedBag args, SparseGrid2D pGrid,  int distance, SparseGrid2D cGrid){
    setRNG(simState.random);
    adjRate = simState.adjustmentRate;
    setID(cID);
    if (args.getNumObjs() > 0){
        maxConsumptionTimer = (int)args.get(0);
    }
    setColor(getNeutralColor() );
   super.intializeActionStep();
    super.setLoc(new Int2D((int)cGrid.getWidth()/2, (int)cGrid.getHeight()/2) );
    consGrid = cGrid;
    prodGrid = pGrid;
    setType(Consumer.class);
    setShortDesc("Agent that is able to consume products");
    TypedBag cap = new TypedBag(Capability.class);

    Consuming c = new Consuming(simState.random);
    c.setActor(this);
    cap.add(c);
    ActRandomly a = new ActRandomly(simState.random);
    a.setActor(this);
    cap.add(a);
    Scanning sc = new Scanning(simState.random);
    sc.setActor(this);
    
    
    //sc.getProcess().setArguments(cap);
    cap.add(sc);
    Situating s = new Situating(simState.random);
    s.setActor(this);
    cap.add(s);
    Prioritizing p = new Prioritizing(simState.random);
    p.setActor(this);
    cap.add(p);
    Adapting ad = new Adapting(simState.random);
    ad.setActor(this);
    cap.add(ad);
    MovingSelf mov = new MovingSelf(simState.random);
    mov.setActor(this);
    cap.add(mov);
    
    setMyCapabilities(cap.getBag());
    setInitialState("Searching for products to consume");
 
    final ConditionFactory cFactory = new ConditionFactory();
    SituationTest sTest = new SituationTest();
    SituationUpdate sUpdate = new SituationUpdate();
    Condition confused = cFactory.build("Situating",sTest, sUpdate,"Not ready to consume");
    updateAndTestAfterSituating = new StoppingCondition(confused);
    
    ScanTest scTest = new ScanTest();
    ScanUpdate scUpdate = new ScanUpdate();
    Condition noPossibilities = cFactory.build("Scanning",scTest, scUpdate,"No possibilities found");
    updateAndTestAfterScanning = new StoppingCondition(noPossibilities);
    
    ProritizingTest pTest = new ProritizingTest();
    ProritizingUpdate pUpdate = new ProritizingUpdate();
    Condition notAbleToPrioritize = cFactory.build("Prioritizing",pTest, pUpdate,"Confused. Not able to prioritize");
    updateAndTestAfterPrioritizing = new StoppingCondition(notAbleToPrioritize);

    ActRandomlyTest aTest = new ActRandomlyTest();
    ActRandomlyUpdate aUpdate = new ActRandomlyUpdate();
    Condition noAction = cFactory.build("ActRandomly",aTest, aUpdate,"No action taken");  
    updateAndTestAfterActRandomly = new StoppingCondition(noAction);

    
    ConsumingTest cTest = new ConsumingTest();
    ConsumingUpdate cUpdate = new ConsumingUpdate();
    Condition consumed = cFactory.build("Consuming",cTest,cUpdate, "Product is being consumed. Pause until complete" );
    updateAndTestAfterConsuming = new StoppingCondition(consumed);
    
    AdaptingTest adTest = new AdaptingTest();
    AdaptingUpdate adUpdate = new AdaptingUpdate();
    Condition adjusted = cFactory.build("Adjusting",adTest,adUpdate, "Adjusting position not successful" );
    updateAndTestAfterAdjusting = new StoppingCondition(adjusted);
    
    MovingSelfTest moveTest = new MovingSelfTest();
    MovingSelfUpdate moveUpdate = new MovingSelfUpdate();
    Condition moved = cFactory.build("Adjusting",moveTest,moveUpdate, "Nothing moved" );
    updateAndTestAfterMovingSelf = new StoppingCondition(moved);
    
    // arugments for Scanning
    Bag scanObj1 = new Bag();
    scanObj1.add(cGrid);
    Bag scanObj2 = new Bag();
    scanObj2.add(pGrid);
    
    
    // object of scan is the space
   // scanArgs .add(grid);
    // first parameter is the distance
    Bag sArgs = new Bag();
    
    
    sArgs.add(distance);
    // second parmaeter is focal point
    sArgs.add(loc);
    Bag sArgLabels = new Bag();
    sArgLabels.add("distance");
    sArgLabels.add("focus");
    LabeledBag scanArgs = new LabeledBag(Object.class,sArgs,sArgLabels);
    scanArgs.setShortDesc("Scan parameters");
    realLoc.x = (double)loc.x;
    realLoc.y = (double)loc.y;
    
    // it's a LIFO stack, so put the first task to be executed on last
    // first arg is the capability used in the task - required
    // second arg is a bag of targetsfor the task - optional
    // third arg is a bag of arguments (parameters) for the task - optional
    // forth arg is results for that task, if they need to be passed on - optional
    // fifth arg is the stopping condition -  optional
    
    Bag actRandomlyArgs = new Bag();
    actRandomlyArgs.add(new ResultsQuery(prioritizingResults,"scores" ) );
    actRandomlyArgs.add(simState.maxConsumptionDistance);
    actRandomlyArgs.add(simState.prConsumeScaling);
    actRandomlyArgs.add(simState.prConsumeMin);
    Bag actRandomlyArgsLabels = new Bag();
    actRandomlyArgsLabels.add("scores");
    actRandomlyArgsLabels.add("maxScore");
    actRandomlyArgsLabels.add("prActScaling");
    actRandomlyArgsLabels.add("prActMin");
        
    
    Bag taskBag = new Bag();
    taskBag.push(new Task(Consuming.class,actRandomlyResults.getOutputs(),
            new LabeledBag(),consumingResults,updateAndTestAfterConsuming )); // CONSUME the product
    taskBag.push(new Task(ActRandomly.class,
            new ResultsQuery(prioritizingResults,"prioritized objects" ),
            new LabeledBag (Object.class,actRandomlyArgs,actRandomlyArgsLabels),
            actRandomlyResults,updateAndTestAfterActRandomly) ); // ACT_PROBABILISTICALLY  with Pr(consume) proportional to distance
    taskBag.push(new Task(Prioritizing.class, new ResultsQuery(scanProductResults,"found objects"),
               new ResultsQuery(scanProductResults,"distances"),prioritizingResults,updateAndTestAfterPrioritizing) ); //PRIORITIZE by distance
    taskBag.push(new Task(Scanning.class,scanObj2, scanArgs,scanProductResults, updateAndTestAfterScanning) ); // SCAN for product
    taskBag.push( new Task(Scanning.class,scanObj1, scanArgs,scanConsumerResults) ); // SCAN for other Consumers
    taskBag.push(new Task(Situating.class,new Bag(), new LabeledBag(),situationResults, updateAndTestAfterSituating) ); // SITUATION - do I have product or not

    super.action.initializeTaskList(taskBag);
 // end constructor   
}

    @Override
    public void setLoc(Int2D l){
        super.setLoc(l);
        realLoc.x = (double)l.x;
        realLoc.y = (double)l.y;
        
    }
    
    @Override
    public void setLoc(int x, int y){
        super.setLoc(x,y);
        realLoc.x = (double)x;
        realLoc.y = (double)y;   
    }
    
    public void setRealLoc(double x, double y){
        realLoc.x = x;
        realLoc.y = y;   
    }
    
    public void setRealLoc(Double2D l){
        realLoc.x = l.x;
        realLoc.y = l.y;  
    }
    
    public void setRealLoc(MutableDouble2D l){
        realLoc.x = l.x;
        realLoc.y = l.y;  
    }
    
    public MutableDouble2D getRealLoc (){
        return realLoc;
    }

    public boolean getHasProduct() { return hasProduct; }
    public void setHasProduct(boolean val) { hasProduct = val; }
    
    public Product getMyProduct(){
        return myProduct;
    }
    
    public void setMyProduct(Product p){
         myProduct = p;
         hasProduct = true;
    }
    
    public void clearMyProduct(){
        myProduct = null;
        hasProduct = false;
    }

    
    class SituationUpdate implements UpdateMethods {

        public SituationUpdate() {

        }

    @Override
    public void update() {
            if (!hasProduct) {
                situationResults.desc = situationResults.desc + "doesn't have product";

                clearActive();
                
            } else {
                setActive();
                consumptionTimer--; // MOVE ALL THIS TO "SITUATION_PROCESS"
                situationResults.desc = situationResults.desc + "has product, " + consumptionTimer + " steps remaining";
                if (consumptionTimer <= 0) {
                    situationResults.desc = situationResults.desc + ", done holding";
                    situationResults.status.setCompleted();
        //            myProduct = null;
                    consumptionTimer = 0;
                    // clear tasklist and push ADAPTING task: 
                    action.taskList.clear();
                    Bag args = new Bag();
                    args.add(getRealLoc() );
                    //double adjRate = 0.1; // TEMPORARY -- take this from SimState instead of local
                    args.add(adjRate);
                    Bag argLabels = new Bag();
                    argLabels.add("loc");
                    argLabels.add("adjRate");
                    LabeledBag adaptingArgs = new LabeledBag(Object.class,args, argLabels);
                    Bag consumptionResultsBag = new Bag();
                    consumptionResultsBag.add(myProduct);
                    Bag movingSelfTarget = new Bag();
                    Bag movingSelfArgLabel = new Bag();
                    movingSelfArgLabel.add("newLoc");
                    movingSelfTarget.add(consGrid);
                    action.taskList.clear();
                    action.taskList.push(new Task(MovingSelf.class,
                            movingSelfTarget,
                            new LabeledBag(MutableDouble2D.class,adaptingResults.getOutputs(),movingSelfArgLabel),movingSelfResults,updateAndTestAfterMovingSelf )); // MOVE incrementally closer to the product just consumed
 
                    action.taskList.push(new Task(Adapting.class,
                            consumptionResultsBag,
                            adaptingArgs,adaptingResults,updateAndTestAfterAdjusting )); // ADAPT location incrementally closer to the product just consumed
                    action.taskList.getDoNotClearOnStop(); // Maybe don't need this any more b/c I'm not
                                                            // triggering a stop condition
                }

            }
        }

        @Override
        public void update(Bag b) {
            update();
        }
    }
    
    class SituationTest implements TestConditions{  // THIS IS A STUB
        public SituationTest(){
            
        }
        @Override
        public BooleanOrNA test() {
            BooleanOrNA result = new BooleanOrNA();
            if ( hasProduct == false) {
                result.setFalse();       // no product, so execute the rest of the tasks.
            } else {
                if (consumptionTimer <= 0){
                   result.setFalse();     // have a product but ready to let it go.  keep running tasks
                } else {
                    result.setTrue();     // have product. Stop task execution
                }
            }
            return result;
        }

        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
    class ScanUpdate implements UpdateMethods{    // THIS IS A STUB
        public ScanUpdate(){
            
        }
        @Override
        public void update() {
           
        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class ScanTest implements TestConditions{      // THIS IS A STUB
        public ScanTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            result.setFalse();                          // THIS IS A STUB, ALWAYS RETURNS FALSE
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
    class ProritizingUpdate implements UpdateMethods{ // THIS IS A STUB
        public ProritizingUpdate(){
            
        }
        @Override
        public void update() {

        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class ProritizingTest implements TestConditions{ // THIS IS A STUB
        public ProritizingTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            result.setFalse();
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
    class ActRandomlyUpdate implements UpdateMethods{ // THIS IS A STUB
        public ActRandomlyUpdate(){
            
        }
        @Override
        public void update() {

        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class ActRandomlyTest implements TestConditions{ 
        public ActRandomlyTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            if (!actRandomlyResults.status.isCompleted() ||
                   actRandomlyResults.getOutputs().numObjs == 0){
                result.setTrue();
            } else {
                result.setFalse();
            }
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
    class ConsumingUpdate implements UpdateMethods{ 
        public ConsumingUpdate(){
            
        }
        @Override
        public void update() {
            if (consumingResults.status.isCompleted() 
                && consumingResults.getOutputs().numObjs > 0){
                Product p = (Product)consumingResults.getOutputs().get(0);
                setMyProduct(p);
                consumptionTimer = 20;
            }
        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class ConsumingTest implements TestConditions{ 
        public ConsumingTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            if (hasProduct){
                result.setTrue();
            } else {
                result.setFalse();
            }
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
    class AdaptingUpdate implements UpdateMethods{ // THIS IS A STUB
        public AdaptingUpdate(){
            
        }
        @Override
        public void update() {
            if (adaptingResults.status.isCompleted() 
            && adaptingResults.getOutputs().numObjs > 0){
                setRealLoc( (Double2D)adaptingResults.getOutputs().get(0) );
                hasProduct = false;
                myProduct = null;
            }
        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class AdaptingTest implements TestConditions{ 
        public AdaptingTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            if (hasProduct){
                result.setTrue();
            } else {
                result.setFalse();
            }
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }
    
        class MovingSelfUpdate implements UpdateMethods{ // THIS IS A STUB
        public MovingSelfUpdate(){
            
        }
        @Override
        public void update() {
            
            if (movingSelfResults.status.isCompleted() && 
                    movingSelfResults.getOutputs().numObjs > 0){
                Int2D newLoc = (Int2D)movingSelfResults.getOutputs().get(0);
                if (getLoc().x != newLoc.x && getLoc().y !=newLoc.y){
                    setLoc(newLoc.x,newLoc.y);  // update both MutableInt2D loc and realLoc (mutable Double2D)
                }                             
            }
        }
        
        @Override
        public void update(Bag b){
            update();
        }
    }
    
    class MovingSelfTest implements TestConditions{ // THIS IS A STUB
        public MovingSelfTest(){
            
        }
        @Override
        public BooleanOrNA test(){
            BooleanOrNA result = new BooleanOrNA();
            result.setFalse();
            return result;            
        }
        @Override
        public BooleanOrNA test(Bag conditions) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }

        @Override
        public BooleanOrNA test(StateVariables stateVars) {
            BooleanOrNA result = new BooleanOrNA();
            return result = test();
        }
    }


}
