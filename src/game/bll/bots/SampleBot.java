package game.bll.bots;

import game.bll.interfaces.IBot;
import game.bll.interfaces.IGameState;
import game.bll.interfaces.IMove;

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
