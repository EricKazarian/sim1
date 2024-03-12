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
     */
    public void moveForward(World world, Vector drift)
    {
    	position = position.move(orientation.toVector()).move(drift);
    }

    
    public void turnClockwise()
    {
        
    }
    

    public void turnCounterclockwise()
    {
        
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
     * true iff same position and orient. and chromosome
     */
    public boolean isEqual(CreatureA other) {
    	return false;
    }
    
    /**
     * @creates | result
     */
    public CreatureA giveCopy() {
    	return (this).clone();
    }
}
