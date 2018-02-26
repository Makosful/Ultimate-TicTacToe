package game.bll.bots;

import game.bll.interfaces.IGameState;
import game.be.IMove;

/**
 *
 * @author Axl
 */
public class SampleBot implements IBot
{

    public SampleBot()
    {
    }

    @Override
    public IMove doMove(IGameState state)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
