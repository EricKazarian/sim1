package sim1;


import util.Orientation;
import util.RandomUtil;

/**
 * @immutable
 *
 */
public class BehaviorA
{

	/**
	 * @inspects | world
	 * @mutates | creature
	 */
    public void applyBehavior(World world, CreatureA creature)
    {   
        var drift = computeFavoriteOrientation(creature.getChromosome()).toVector();
//        RandomUtil.bool();
        if (!world.isLimPos(creature.getPosition())) { //We check if it's at the border, if yes: it changes nothing.
    		creature.moveForward(world, drift); //It will move depending on if it can or not
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
//    Creatures of kind A (red creatures) have a similar behavior with a crucial difference.
//    The target position of each creature of kind A is computed as TARGET = CURRENT + ORIENTATION + DRIFT.
//    The last summand DRIFT is a cardinal direction that is solely determined by the chromosome 
//    (in fact a list of integers) that the creature carries.
//    Similar to creatures of kind B, if a creature of kind A has not moved, its orientation is 
//    randomly (50%) turned clockwise or counterclockwise.
    
	/**
	 * LEGIT
	 * 
	 * The fav. orientation of a creature depends on its 3 first genes.
	 * 
	 */
    private static Orientation computeFavoriteOrientation(Chromosome chrom) {
    	
    	int mid = Constants.GENE_MIN + ((Constants.GENE_MAX - Constants.GENE_MIN)/2);
    	
    	int bit0 = (chrom.getGene(0) <= mid) ? 0 : 1;
    	int bit1 = (chrom.getGene(1) <= mid) ? 0 : 1;
    	int bit2 = (chrom.getGene(2) <= mid) ? 0 : 1;
    	
    	
    	int res = (bit2 << 2) | (bit1 << 1) | bit0;
    	if (! (0 <= res && res  <= 7)) {
    		throw new ArithmeticException("Average abs. gene value not in the expected range");
    	}
    	
    	return Orientation.orientations().get(res);
    	
    }
    
    
    
    
}