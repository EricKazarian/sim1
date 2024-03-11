package util;


public class Point
{
	
    private int x;

    private int y;
	
	
    /**
     * Gives random point x,y such that 0 <= x < maxX
     * and 0 <= y < maxY.
     * @pre | maxX != null && maxY != null
     * @post 
     *      | 0 <= this.x <= maxX
     *      | 0 <= this.y <= maxY
     */
    public static Point createRandom(int maxX, int maxY)
    {
        //generate random number x between 0 and maxX
        //generate random number y between 0 and maxY
        //check randomUtil file if you don't get where this integer function comes from
        Point randomPoint = new Point(integer(0, maxX), integer(0, maxY));
        // this.x = x;
        // this.y = y;
        return randomPoint;
    	// return null;
    }



    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X-coordinate of the Point.
     * @post | result != null
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Returns the Y-coordinate of the Point.
     * @post | result != null
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * @pre | displacement != null
     * Returns this point, plus `displacement` 
     * @mutates | this
     * @post 
     *      | this.x != null && this.y != null
     *      | this.x >= displacement.x && this.y >= displacement.y
     */
    public Point move(Vector displacement)
    {
        this.x = this.getX() + displacement.getX(); //not sure if it's displacement.x or displacement.getX(), according to how it's used in instancesquared, I assume the second option is better
        this.y = this.getY() + displacement.getY();
        return this;
    //    return null;
    }

    /**
     * LEGIT 
     * @pre | other != null
     * @post | result == (this.getX() - other.getX()) * (this.getX() - other.getX()) + (this.getY() - other.getY()) * (this.getY() - other.getY())
     */
    public int distanceSquared(Point other)
    {
        if ( other == null )
        {
            throw new IllegalArgumentException();
        }

        var dx = other.getX() - this.getX();
        var dy = other.getY() - this.getY();

        return dx * dx + dy * dy;
    }



    @Override
    /**
     * LEGIT
     */
    public boolean equals(Object other)
    {
        if (other instanceof Point p)
        {
            return this.x == p.x && this.y == p.y;
        } else
        {
            return false;
        }
    }

    @Override
    /**
     * LEGIT
     */
    public String toString()
    {
        return String.format("(%d, %d)", this.x, this.y);
    }
    
    /**
     * Determines whether `pos` is a point belonging to a rectangle with sides width, height.
     * I.e. determines if pos has an x component s.t. 0 <= x < width and analogously for y.
     */
    public static boolean isWithin(Point pos, int width, int height) {
        if (0 <= pos.getX() < width && 0 <= pos.getY() < height){
            return true;
        }
    	return false;
    }
    
    
}
