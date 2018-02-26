package game.bll;

import game.bll.interfaces.IField;
import game.bll.interfaces.IMove;
import java.util.List;

/**
 *
 * @author Axl
 */
public class Field implements IField
{

    public Field()
    {
    }

    @Override
    public void clearBoard()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IMove> getAvailableMoves()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getPlayerId(int column, int row)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFull()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[][] getBoard()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[][] getMacroboard()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBoard(String[][] board)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
