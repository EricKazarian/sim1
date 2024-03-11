package sim1;

import util.Orientation;
import util.Point;

// CreatureB: instances of this class represent creatures of kind B. CreatureB’s
//     have a position field (of type Point), an orientation field (of type Orientation) and
//     a behavior field (of type BehaviorB) which is used to decide how the creature will move/reorient at each step
//     of the simulation.
public class CreatureB
{

    private Point position;

    private Orientation orientation;

    private final BehaviorB behavior;


    public CreatureB(BehaviorB behavior, Point position, Orientation orientation)
    {

        this.behavior = behavior;
        this.position = position;
        this.orientation = orientation;
    }


    public Point getPosition()
    {
        return this.position;
    }


    public Orientation getOrientation() { return this.orientation; }
    

    /**
     * Changes the position of `this` if `world` is free at the target pos.
     * The orientation remains unchanged in any case.
     * 
	 */
    public void moveForward(World world)
    {
            int x_value = getX(getPosition())
    }

    /**
     * @post | getOrientation().isEqual(old(getOrientation().turnClockwise(1)))
     */
    public void turnClockwise()
    {
        
    }


    public void turnCounterclockwise()
    {
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
    	return null;
    }
    
}
