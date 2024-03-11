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
     * @post | newArray.length == count
	 */
    public static Chromosome[] createRandom(int count)
    {   
        int[] newArray = new int[count]; 
        for (int i = 0; i < count; i++){
            newArray[i] = createRandom()
        }
    	return Chromosome(newArray);
        //return null;
    }

    /**
     * index should be 0 <= index < Cst.CHROM_SIZE
     * UP: In the specification (comment) of Chromosome.getGene, an inequality has been made strict 
     * Correct version: index < Cst.CHROM_SIZE.
     * @pre | index < Cst.CHROM_SIZE 
     * @inspects | this
     * @post | result == this.weights[index]
     */
    public int getGene(int index)
    {
    	return this.weights[index]; //not sure if clone needed

    }
    
    /**
     * @pre | index < Cst.CHROM_SIZE
     * @post | this.weights[index] == val
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

        for ( int i = index ; i < Constants.CHROM_SIZE / 2 ; ++i )
        {
            offspringGenes[i] = this.getGene(i);
        }

        for ( int i = Constants.CHROM_SIZE / 2 ; i != Constants.CHROM_SIZE; ++i )
        {
            offspringGenes[i] = other.getGene(i);
        }

        return new Chromosome(offspringGenes);
    }

    /**
     * Gene #index is set to gene + delta if that modification remains within gene bounds.
     * @pre | index < weights.length
     *      | -200 < delta < 200
     * @post | GENE_MIN < this.weights[index] < GENE_MAX
     */
    // public static int CHROM_SIZE = 6; // should be >=3
	// public static int GENE_MIN = 0;
	// public static int GENE_MAX = 1000; //min and max should be different.
	// public static int GENE_DELTA = 200; //typical "mutation" value. should be < (GENE_MAX - GENE_MIN)/2
    public void mutate(int index, int delta)
    {
        int mutationValue = this.weights[index] + delta;
        if (mutationValue < ((GENE_MAX - GENE_MIN) / 2)){
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
    	return this.clone();
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
}
