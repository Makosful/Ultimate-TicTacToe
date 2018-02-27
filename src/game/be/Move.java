package game.be;

/**
 *
 * @author Axl
 */
public class Move implements IMove
{

    private final int x, y;

    public Move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "X: " + getX() + ", Y: " + getY();
    }
}
