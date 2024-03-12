package sim1;

import java.util.Arrays;

import util.Point;



public class World
{

    private final int width;


    private final int height;

    /**
     * @representationObject
     * @representationObjects
     * @invar | populationA != null
     */
    private final CreatureA[] populationA;

    /**
     * @representationObject
     * @representationObjects
     * @invar | populationB != null
     */
    private final CreatureB[] populationB;

    /**
     * @pre pop A and B can't be null, as for height and width I don't think there are limits to what they can be
     * 		 | popA != null && popB != null
     */
    public World(int width, int height, CreatureA[] popA, CreatureB[] popB)
    {
        this.width = width;
        this.height = height;
        this.populationA = popA;
        this.populationB = popB;

    }


    public int getWidth() {
    	return this.width;
    }


    public int getHeight() {
    	return this.height;
    }


    public CreatureA[] getPopulationA()
    {
        return populationA;
    }


    public CreatureB[] getPopulationB()
    {
        return populationB;
    }

    /**
     * @pre | position != null
     */
    public boolean isInside(Point position)
    {
    	return Point.isWithin(position, width, height);
    }
    

    
    /**
     * @pre | pos != null
     * Returns true if pos is 1 unit away from a wall (and inside the world)
     */
    public boolean isLimPos(Point pos) {
        //CHECK

    	if (isInside(pos) == false) { return false;}
    	if (pos.getX() == (width - 1) || pos.getX() == 1 || pos.getY() == (height - 1) || pos.getY() == 1) {
    		return true;
    	}
    	return false;
    }

    
    /**
     * LEGIT 
     * @pre | array1 != null && array2 != null
     * @pre | array1.length == array2.length
     */
    public static boolean areEqualCreatureAArrays(CreatureA[] array1, CreatureA[] array2) {
    	boolean res = true;
    	for (int i = 0 ; i < array1.length ; i++) {
    		res = res && array1[i].isEqual(array2[i]);
    	}
    	return res;
    }
    
    
    /**
     * LEGIT 
     * @pre | array1 != null && array2 != null
     * @pre | array1.length == array2.length
     */
    public static boolean areEqualCreatureBArrays(CreatureB[] array1, CreatureB[] array2) {
    	boolean res = true;
    	for (int i = 0 ; i < array1.length ; i++) {
    		res = res && array1[i].isEqual(array2[i]);
    	}
    	return res;
    }
    

    /**
     * true if position is inside the world and no creature sits there
     * 
     * @pre | position != null
     */
    public boolean isFree(Point position)
    {
    	//Check if out of bounds
    	if (isInside(position) == false) {
    		return false;
    	}
    	//check if the creaturesA sits in position
    	for (int i = 0; i < getPopulationA().length; i++) {
    		CreatureA[] array = getPopulationA();
    		if((array[i].getPosition()).getX() == position.getX() || (array[i].getPosition()).getY() == position.getY()) {
    			return false;
    		}	
    	}
    	//check if the creaturesB sits in position
    	for (int i = 0; i < getPopulationB().length; i++) {
    		CreatureB[] array = getPopulationB();
    		if((array[i].getPosition()).getX() == position.getX() || (array[i].getPosition()).getY() == position.getY()) {
    			return false;
    		}	
    	}
    	return true;
//        return false;
    }

    /**
     * Performs the action of each creature
     */
    public void step()
    {
    	
        for (int i = 0 ; i < populationB.length ; i ++)
        {
            populationB[i].performAction(this);
        }
    	
        for (int i = 0 ; i < populationA.length ; i ++)
        {
            populationA[i].performAction(this);
        }


    }
    

    	
    }
    


