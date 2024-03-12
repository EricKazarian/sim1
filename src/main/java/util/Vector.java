package util;


public class Vector
{
    /**
     * @invar | 0 <= x && 0 <= y
     */
    private int x;
    private int y;

    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns this Vector's x coordinate.
     */
    public int getX() {
    	return x;
    }

    /**
     * Returns this Vector's y coordinate.
     */
    public int getY() {
    	return y;
    }
    
    /**
     * mult. both components by fac
     * @mutates | this
     * @post | result != null
     */
    public Vector scaleWith(int fac) {
        this.x = this.getX() * fac;
        this.y = this.getY() * fac;
    	return this; //not sure what it's supposed to return actually
        // return null;
    }
    
    /**
     * sum of this vector and other
     * @post | getX() == old(getX()) + other.getX() && getY() == old(getY()) + other.getY()
     */
    public Vector plus(Vector other) {
        this.x = this.getX() + other.getX();
        this.y = this.getY() + other.getY();
        return this; //not sure what it's supposed to return actually
    	// return null;
    }


    @Override
    /**
     * LEGIT
     */
    public boolean equals(Object other)
    {
        if (other instanceof Vector v)
        {
            return this.x == v.x && this.y == v.y;
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
        return String.format("Vector(%d, %d)", this.x, this.y);
    }
}
