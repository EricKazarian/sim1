 package sim1;

import util.Orientation;
import util.Point;
import util.RandomUtil;

// CreatureB: instances of this class represent creatures of kind B. CreatureB’s
//     have a position field (of type Point), an orientation field (of type Orientation) and
//     a behavior field (of type BehaviorB) which is used to decide how the creature will move/reorient at each step
//     of the simulation.
public class CreatureB
{

    private Point position;

    private Orientation orientation;

    private final BehaviorB behavior;
//    /**
//     * @representationObject
//     */
//    private Chromosome chrom;



    public CreatureB(BehaviorB behavior, Point position, Orientation orientation)
    {

        this.behavior = behavior;
        this.position = position;
        this.orientation = orientation;
        
        //this was not given (the param and private too)
//        this.chrom = chrom;

    }


    public Point getPosition()
    {
        return this.position;
    }


    public Orientation getOrientation() { return this.orientation; }
    
    //this function was not given
    public BehaviorB getBehavior() {
    	return this.behavior;
    }
    
//    public Chromosome getChromosome() {
//    	return chrom;
//    }
    /**
     * Changes the position of `this` if `world` is free at the target pos.
     * The orientation remains unchanged in any case.
     * 
	 */
    public void moveForward(World world)
    {	
    	if (world.isFree(this.getPosition())) {
//    		Point pos = this.getPosition();
        	position = position.move(orientation.toVector()); //<=> TARGET = CURRENT + ORIENTATION
    	} 
    }

    /**
     * @post | getOrientation().isEqual(old(getOrientation().turnClockwise(1)))
     */
    public void turnClockwise()
    {
        this.orientation = this.orientation.turnClockwise(1);
    }


    public void turnCounterclockwise()
    {
    	//this was given
        this.orientation = this.orientation.turnCounterclockwise(1);
    }

    /**
     * LEGIT
     * applies behavior from `this`
     */
    public void performAction(World world)
    {
        this.behavior.applyBehavior(world, this);
    }

    /**
     * LEGIT
     * true iff same position and orient.
     */
    public boolean isEqual(CreatureB other) {
    	return (other != null) && (this.position.equals(other.getPosition())) && (this.orientation.isEqual(other.getOrientation()));
    }
    
    /**
     * @creates | result
     */
    public CreatureB giveCopy() {
    	CreatureB newCreature = new CreatureB(this.getBehavior(), this.getPosition(), this.getOrientation());
    	//CHECK COULD BE THAT IT NEEDS TO BE WITH A FOR LOOP(: the chromosomes)
    	return newCreature;
//    	return null;
    }
    
}
