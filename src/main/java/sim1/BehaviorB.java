package sim1;

import util.RandomUtil;


public class BehaviorB
{

	/**
	 * See overview in assignment
	 */
    public void applyBehavior(World world, CreatureB creature)
    {
    	if (!world.isLimPos(creature.getPosition())) { //We check if it's at the border, if yes: it changes nothing.
    		creature.moveForward(world); //It will move depending on if it can or not
//    		RandomUtil.bool();
	    	if (!world.isFree(creature.getPosition())){ //If it wasn't free to move, we take a 50/50 to change it's orientation.
	    		boolean fifty = RandomUtil.bool();
	    		if (fifty) {
	    			creature.turnClockwise();
	    		} else if (!fifty) {
	    			creature.turnCounterclockwise();
	    		}
	    	}
    	}
    }
}
//At each tick, each creature of kind B (blue creatures) moves according to its orientation, only 
//if the target position has no creature in it.
//In that case the target position is computed as TARGET = CURRENT + ORIENTATION.
//If the target position is not free, the creature does not move but its orientation is randomly 
//(50%) turned clockwise or counterclockwise.
//For example, a blue creature that cannot move and that is pointing in
//the N orientation will either be pointing NE or NW at the end of the current tick.
//Once a creature reaches one of borders of the simulation field, it will remain stationary 
//from then on.