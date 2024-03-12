package sim1;

import util.Orientation;
import util.Point;
import util.Vector;


public class CreatureA
{

	
    private Point position;
    private Orientation orientation;
    private final BehaviorA behavior;
    /**
     * @representationObject
     */
    private Chromosome chrom;


    public CreatureA(BehaviorA behavior, Point position, Orientation orientation, Chromosome chrom)
    {

        this.behavior = behavior;
        this.position = position;
        this.orientation = orientation;
        this.chrom = chrom;
    }


    public Point getPosition()
    {
        return this.position;
    }


    public Orientation getOrientation() { 
    	return this.orientation; 
    	}
    

    public BehaviorA getBehavior() {
    	return this.behavior;
    }
    

    public Chromosome getChromosome() {
    	return chrom;
    }

    /**
     * The orientation remains unchanged.
     * @inspects | world 
     */
    public void moveForward(World world, Vector drift)
    {
    	position = position.move(orientation.toVector()).move(drift);
    }

    
    public void turnClockwise()
    {
        this.orientation = this.orientation.turnClockwise(1);
    }
    

    public void turnCounterclockwise()
    {
        this.orientation = this.orientation.turnCounterclockwise(1);

    }

    /**
     * LEGIT
     * applies behavior of `this`
     */
    public void performAction(World world)
    {
        this.behavior.applyBehavior(world, this);
    }
    
    /**
     * true if same position and orient. and chromosome
     */
    public boolean isEqual(CreatureA other) {
    	if (other.getPosition() != this.getPosition() 
//    			|| other.getChromosome() == this.getChromosome() //not sure if I can do this so I added a whole ass for loop
    			|| other.getOrientation() != this.getOrientation()){
    		return false;
    	}
    	Chromosome curChrom = this.getChromosome();
    	Chromosome otherChrom = other.getChromosome();
    	for (int i = 0; i < curChrom.getLength() ; i++) {
    		if (curChrom.getGene(i) != otherChrom.getGene(i)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * @creates | result
     */
    public CreatureA giveCopy() {
    	CreatureA newCreature = new CreatureA(this.getBehavior(), this.getPosition(), this.getOrientation(), this.getChromosome());
    	//CHECK COULD BE THAT IT NEEDS TO BE WITH A FOR LOOP(: the chroms
    	return newCreature;
    }
}
