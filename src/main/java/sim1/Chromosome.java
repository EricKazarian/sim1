package sim1;

import java.util.Arrays;
import java.util.stream.IntStream;

import util.RandomUtil;


public class Chromosome
{

    /**
     * @representationObject
     */
    private int[] weights;

    /**
     * @pre | weights != null
     * @post | weights != null 
     */
	public Chromosome(int[] weights)
	{
	     // use this later on to check index per index....@post | Arrays.stream(result).allMatch(row -> row != null && row.length == result[0].length && 1 <= row.length)
		this.weights = weights.clone(); //not sure about the .clone
	}

	/**
	 * LEGIT
	 * Gives 1 randomly generated chromosome
	 * @creates | result
	 */
	public static Chromosome createRandom()
    {

        int[] genes =
        		IntStream.generate(() -> RandomUtil.integer(Constants.GENE_MIN, Constants.GENE_MAX + 1))
        		.limit(Constants.CHROM_SIZE).toArray();

        return new Chromosome(genes);
    }

	/**
	 * Gives `count` randomly generated chromosomes
     * @pre | 0 <= count
     * @post | result.length == count
	 */
    public static Chromosome[] createRandom(int count)
    {   
        Chromosome[] newArray = new Chromosome[count]; 
        for (int i = 0; i < count; i++){
            newArray[i] = createRandom();
        }
    	return newArray;
        //return null;
    }

    /**
     * index should be 0 <= index < Cst.CHROM_SIZE
     * UP: In the specification (comment) of Chromosome.getGene, an inequality has been made strict 
     * Correct version: index < Cst.CHROM_SIZE.
     * @pre | index   < Constants.CHROM_SIZE 
     * @inspects | this
     */
    public int getGene(int index)
    {
    	return this.weights[index]; //not sure if clone needed
    }
    
    /**
     * @pre | index < Constants.CHROM_SIZE
     * @post | getGene(index) == val && old(getGene(index)) != val
     */
    public void setGene(int index, int val) {
    	this.weights[index] = val;
    }

    /**
     * Returns a Chromosome whose weights are derived from `this` and `other` weights
     *  
     * 
     * @post The result starts with the first `index` genes from `this`
     * and finishes with (0 <=) genes picked in `other`.  
     * 
     */
    public Chromosome crossover(Chromosome other, int index)
    {
        int[] offspringGenes = new int[Constants.CHROM_SIZE];

        for (int i = 0; i < index ; ++i )
        {
            offspringGenes[i] = this.getGene(i);
        }

        for (int i = index; i < Constants.CHROM_SIZE; ++i )
        {
            offspringGenes[i] = other.getGene(i);
        }

        return new Chromosome(offspringGenes);
    }

    /**
     * Gene #index is set to gene + delta if that modification remains within gene bounds.
     * pre | index < weights.length
     *      | -200 < delta < 200
     * post | Constants.GENE_MIN < getGene(index) < Constants.GENE_MAX
     */
    // public static int CHROM_SIZE = 6; // should be >=3
	// public static int GENE_MIN = 0;
	// public static int GENE_MAX = 1000; //min and max should be different.
	// public static int GENE_DELTA = 200; //typical "mutation" value. should be < (GENE_MAX - GENE_MIN)/2
    public void mutate(int index, int delta)
    {
        int mutationValue = this.weights[index] + delta;
        if (mutationValue < ((Constants.GENE_MAX - Constants.GENE_MIN) / 2)){
            this.weights[index] = mutationValue;
        }
    }


    public void randomlyMutate()
    {
        var index = RandomUtil.integer(weights.length);
        var delta = RandomUtil.integer(-Constants.GENE_DELTA, Constants.GENE_DELTA);

        this.mutate(index, delta);
    }
    

    public Chromosome giveCopy() {
    	int[] array = new int[this.weights.length];
    	for (int i = 0; i < this.weights.length; i++) {
    		array[i] = this.getGene(i); // I have a feeling this will cause REP.EXP. at some point.
    	}
    	Chromosome newChrom = new Chromosome(array);
    	if ( this.isEqual(newChrom) ) {
        	return newChrom;
    	}
    	throw new IllegalArgumentException("'giveCopy()' in chromosome didn't work it seems lil' bro");
    }
    
    /**
     * LEGIT
     */
    public boolean isEqual(Chromosome other) {
    	boolean res = (other != null);
    	for (int i = 0 ; i < weights.length ; i ++) {
    		res = res && (weights[i] == other.getGene(i));
    	}
    	return res;
    }
    
    //self imp for CreatureA for the isequal function 
    public int getLength() {
    	return this.weights.length;
    }
}
