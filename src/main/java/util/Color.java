package util;


public class Color
{
    private final int color;

    public final static Color WHITE = new Color(255, 255, 255);

    public final static Color RED = new Color(255, 0, 0);

    public final static Color GREEN = new Color(0, 255, 0);

    public final static Color BLUE = new Color(0, 0, 255);

    public final static Color BLACK = new Color(0, 0, 0);

    /**
     * r, g, b should be 0 <= _ <= 255.
     * @pre | isValidColorComponent(r) && isValidColorComponent(g) && isValidColorComponent(b)
     */
    public Color(int r, int g, int b)
    {
        this.color = 0xFF000000 | (r << 16) | (g << 8) | b; //this line is LEGIT
    }

    /**
     * @inspects c
     */
    public static boolean isValidColorComponent(int c)
    {
        return 0 <= c && c <= 255;
    }

    public int asInteger()
    {
        return this.color; //No clone required since it's private, I don't have to worry about RE.
    }

    @Override
    /**
     * LEGIT
     */
    public boolean equals(Object obj)
    {
        if ( obj instanceof Color other )
        {
            return this.color == other.color;
        }
        else
        {
            return false;
        }
    }

    @Override
    /**
     * LEGIT
     */
    public int hashCode()
    {
        return Integer.hashCode(this.color);
    }
}
