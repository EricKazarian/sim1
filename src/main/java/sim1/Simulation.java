package sim1;


import java.util.ArrayList;

import util.Orientation;
import util.Point;
import util.RandomUtil;
import util.Vector;

public class Simulation
{
	
	/**
	 * To remain efficient this reference can freely be accessed by the client
	 * @invar | world != null
	 */
	private World world;

    private final int populationSize;

    /**
     * Creates a simulation having a getWorld() with:
     * - A square simulation field of side `size`
     * - A population size (resp. population A size) equal to `populationSize` (resp. `numA`)
     * - Randomly chosen orientations for all creatures
     * - Random positions for all creatures, within a central square whose points (x,y) are
     *   such that size/4 <= x < size/2 + size/4 (same for y)
     * - Randomly assigned chromosomes for creatures of kind A
     *    
     */
    public Simulation(int size, int populationSize, int numA)
    {
    	int width = size/2;
    	int height = size/2;
    	
    	this.populationSize = populationSize;
    	
        CreatureA[] popA = new CreatureA[numA];
    	for (int i = 0; i < numA; i++) {
    		Orientation ranO = Orientation.createRandom();
    		BehaviorA beh = new BehaviorA();
    		Point ranP = Point.createRandomWithMin(size / 4, size / 4, size / 2 + size / 4, size / 2 + size / 4);
    		popA[i] = new CreatureA(beh, ranP, ranO, Chromosome.createRandom());	
    	}
    	
    	CreatureB[] popB = new CreatureB[populationSize - numA];
    	for (int i = 0; i < populationSize - numA; i++) {
    		Orientation ranO = Orientation.createRandom();
    		BehaviorB beh = new BehaviorB();
    		Point ranP = Point.createRandomWithMin(size / 4, size / 4, size / 2 + size / 4, size / 2 + size / 4);
    		popB[i] = new CreatureB(beh, ranP, ranO);	
    	}
    	this.world = new World(width, height, popA, popB);
    }
    
    public int getPopulationSize() {
    	return this.populationSize;
    }
    


    /**
     * Creates a World with:
     * - A square simulation field of side `size`
     * - A population size (resp. population A size) equal to `popuSize` (resp. `numA`)
     * - A population B size equal to `numB`
     * - Randomly chosen orientations for all creatures
     * - Random positions for all creatures, within a central square whose points (x,y) are
     *   such that size/4 <= x < size/2 + size/4 (same for y)
     * - Chromosomes for creatures of kind A as in `chromsA`
     * 
     * @pre | chromsA.length == numA
     */
    public static World createRandWorldWith(int size, int popuSize, int numA, int numB, Chromosome[] chromsA) {
    	int width = size/2;
    	int height = size/2;
    	    	
        CreatureA[] popA = new CreatureA[numA];
    	for (int i = 0; i < numA; i++) {
    		Orientation ranO = Orientation.createRandom();
    		BehaviorA beh = new BehaviorA();
    		Point ranP = Point.createRandomWithMin(size / 4, size / 4, size / 2 + size / 4, size / 2 + size / 4);
    		Chromosome newChrom =  chromsA[i];
    		popA[i] = new CreatureA(beh, ranP, ranO, newChrom);	
    	}
    	
    	CreatureB[] popB = new CreatureB[numB];
    	for (int i = 0; i < numB; i++) {
    		Orientation ranO = Orientation.createRandom();
    		BehaviorB beh = new BehaviorB();
    		Point ranP = Point.createRandomWithMin(size / 4, size / 4, size / 2 + size / 4, size / 2 + size / 4);
    		popB[i] = new CreatureB(beh, ranP, ranO);	
    	}
    	return new World(width, height, popA, popB);
    }

    
    public World getWorld() {
    	if (world == null) {
            throw new IllegalArgumentException("the 'World' cannot be null.");
    	}
    	CreatureA[] popA = world.getPopulationA();
    	CreatureB[] popB = world.getPopulationB();
    	return new World(world.getWidth(), world.getHeight(), popA, popB);
    }


    /**
     * Replaces getWorld() with a new one. The latter is populated according to the same ratio
     * CreatureA/(CreatureA + CreatureB) of creatures of kind A,B that survived.
     * 
     * The DNA of CreatureA's of the new world  is derived from the DNA
     * of CreatureA's that survived. Specifically, Each new chromosome
     * is obtained by crossing over some surviving parent chromosomes and by maybe
     * applying a random mutation.
     * 
     * 
     */
    public void nextGeneration()
    {
    	int survA = countSurvivingCreatureA();
    	int survB = countSurvivingCreatureB();
    	//Formula from assignment
    	int nextCountA = (int) Math.floor( ((double) survA / (survA + survB)) * populationSize );
    	for (int i = 0; i < nextCountA; i++) {
    		
    	}
    	CreatureA[] popA = world.getPopulationA()/(world.getPopulationA() + world.getPopulationB();
    	CreatureB[] popB = world.getPopulationB();
    }
    
    
    /**
     * Returns the number of CreatureB that survive (see this.survives)
     */
    private int countSurvivingCreatureB() {
    	int count = 0;
    	CreatureB[] CreaturesB =  this.world.getPopulationB();
    	for (int i = 0; i < this.world.getPopulationB().length; i++) {
        	if (survives(CreaturesB[i].getPosition())){
        		count += 1;
        	}
    	}
        return count;    
    }
    
    /**
     * Returns the number of CreatureA that survive (see this.survives)
     */
    private int countSurvivingCreatureA() {
    	int count = 0;
    	CreatureA[] CreaturesA =  this.world.getPopulationA();
    	for (int i = 0; i < this.world.getPopulationA().length; i++) {
        	if (survives(CreaturesA[i].getPosition())){
        		count += 1;
        	}
    	}
        return count;
    }
    
    /**
     * The list of Chromosome's of creatures of kind A that survive (according to this.survives)
     */
    private ArrayList<Chromosome> selectSurvivingDNA()
    {
        ArrayList<Chromosome> result = new ArrayList<Chromosome>();
        CreatureA[] CreaturesA =  this.world.getPopulationA();
    	for (int i = 0; i < this.world.getPopulationA().length; i++) {
        	if (survives(CreaturesA[i].getPosition())){
        		result.add(CreaturesA[i].getChromosome());
        	}
    	}
        return result;
    }
    
    /**

     * Returns an array of `count` Chromosomes.
     * 
     * Each such "offpsring" chromosome is obtained as a (1) random crossover (see Chromosome.crossover)
     * of 2 chromosomes called "parents" randomly picked in the input list.
     * and (2) some offpsring chromosomes are further subject to a mutation
     * 
     * if parentGeneration is empty, the result array consists of `count` random chromosomes. 

     */
    private Chromosome[] computeOffspring(ArrayList<Chromosome> parentGeneration, int count)
    {
    	if (parentGeneration == null) {
    		Chromosome[] chrom = Chromosome.createRandom(count);
    		return chrom;
    	}
    	int lengthOG = parentGeneration.size();
    	Chromosome[] chrom = new Chromosome[count];
    	for (int i = 0; i < lengthOG; i++) {    	
        	int len = parentGeneration.size();
    		int i1 = RandomUtil.integer(len);
    		int i2 = RandomUtil.integer(len);
    		while (i1 == i2) {
    			i1 = RandomUtil.integer(len);
    		}
    		if ( RandomUtil.integer(100) < Constants.MUT_RATE )
            {
                offspring.randomlyMutate();
            }
    	}
        
    	
    	return null;

    }
    
    /**
     * LEGIT
     * 
     * @pre | pos != null
     * Survive zone = Lower right ninth of the field 
     */
    public boolean survives(Point pos) {
    	return isInSouthEastZone(pos);
    }
    
    /**
     * LEGIT 
     * 
     * @param pos
     * @return
     */
    private boolean isInSouthEastZone(Point pos) {
    	return (pos.getX() >= 2 * world.getWidth() / 3) &&
    			(pos.getY() >= 2* world.getHeight() / 3);
    }
    
    
}
