package game.bll;

import game.bll.interfaces.IField;
import game.bll.interfaces.IGameState;

/**
 *
 * @author Axl
 */
public class GameState implements IGameState
{

    public GameState()
    {
    }

    @Override
    public IField getField()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMoveNumber()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMoveNumber(int moveNumber)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getRoundNumber()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRoundNumber(int roundNumber)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
