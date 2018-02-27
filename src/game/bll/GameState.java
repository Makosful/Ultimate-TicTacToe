package game.bll;

import game.bll.interfaces.IField;
import game.bll.interfaces.IGameState;

/**
 *
 * @author Axl
 */
public class GameState implements IGameState
{

    private final IField field;
    private int moveNumber;
    private int roundNumber;

    public GameState(IField field)
    {
        this.field = field;
        this.moveNumber = 0;
        this.roundNumber = 0;
    }

    @Override
    public IField getField()
    {
        return this.field;
    }

    @Override
    public int getMoveNumber()
    {
        return this.moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber)
    {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber()
    {
        return this.roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber)
    {
        this.roundNumber = roundNumber;
    }
}
