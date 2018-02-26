package game.bll.interfaces.bot;

import game.bll.interfaces.game.IGameState;
import game.bll.interfaces.move.IMove;

/**
 *
 * @author mjl
 */
public interface IBot {

    /**
     * Makes a turn. Implement this method to make your bot do something.
     *
     * @param state the current game state
     * @return The column where the turn was made.
     */
    IMove doMove(IGameState state);
    
}
