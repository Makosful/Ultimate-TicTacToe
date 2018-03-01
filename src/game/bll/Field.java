package game.bll;

import game.be.IMove;
import game.bll.interfaces.IField;
import java.util.List;

/**
 *
 * @author Axl
 */
public class Field implements IField
{

    private String[][] microBoard;
    private String[][] macroBoard;

    public Field()
    {
        microBoard = new String[9][9];
        macroBoard = new String[3][3];

        setDefaults();
    }

    @Override
    public void clearBoard()
    {
        setDefaults();
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
        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++)
                if (macroBoard[x][y].contains(EMPTY_FIELD))
                    return true;
        return false;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y)
    {
        return macroBoard[x / 3][y / 3].contains(AVAILABLE_FIELD);
    }

    @Override
    public String[][] getBoard()
    {
        return microBoard;
    }

    @Override
    public String[][] getMacroboard()
    {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board)
    {
        this.microBoard = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
        this.macroBoard = macroboard;
    }

    private void setDefaults()
    {
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                macroBoard[x][y] = AVAILABLE_FIELD;

        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++)
                microBoard[x][y] = AVAILABLE_FIELD;
    }

}
