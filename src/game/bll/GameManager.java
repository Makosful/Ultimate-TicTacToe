package game.bll;

import game.be.IMove;
import game.bll.bots.IBot;
import game.bll.interfaces.IField;
import game.bll.interfaces.IGameState;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager
{

    private boolean isFieldOccupied(String[][] board, int x, int y)
    {
        return !board[x][y].equalsIgnoreCase(player_o)
               && !board[x][y].equalsIgnoreCase(player_x);
    }

    /**
     * Three different game modes.
     */
    public enum GameMode
    {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    public enum Position
    {
        TOP_LEFT,
        TOP_MIDDLE,
        TOP_RIGHT,
        LEFT,
        MIDDLE,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM_MIDDLE,
        BOTTOM_RIGHT
    }

    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private final String player_x = "X";
    private final String player_o = "O";

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     *                     load a saved game.
     */
    public GameManager(IGameState currentState)
    {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
        printDebugField(currentState.getField().getBoard());
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     *                     load a saved game.
     * @param bot          The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot)
    {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     *                     load a saved game.
     * @param bot          The first bot to play.
     * @param bot2         The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2)
    {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame(IMove move)
    {
        //Verify the new move
        if (!VerifyMoveLegality(move))
            return false;

        //Update the currentState
        UpdateBoard(move);
        UpdateMacroboard(move);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        printDebugField(currentState.getField().getBoard());
        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame()
    {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1)
        {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return UpdateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean VerifyMoveLegality(IMove move)
    {
        //Test if the move is legal
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        if (!currentState.getField().isInActiveMicroboard(move.getX(), move.getY()))
            return false;

        String[][] board = currentState.getField().getBoard();
        if (!board[move.getX()][move.getY()].contains(IField.AVAILABLE_FIELD))
            return false;

        System.out.println("Not currently checking move validity actual board");
        return true;
    }

    /**
     * Places the player character on the board
     *
     * @param move
     */
    private void UpdateBoard(IMove move)
    {
        currentState.getField().getBoard()[move.getX()][move.getY()]
        = getCurrentPlayerChar();
        Position pos = getMicroPlacement(move);
        clearAvailableMoves();
        switch (pos)
        {
            case TOP_LEFT:
                setAvailableMoves(0, 2, 0, 2);
                break;
            case TOP_MIDDLE:
                setAvailableMoves(3, 5, 0, 2);
                break;
            case TOP_RIGHT:
                setAvailableMoves(6, 8, 0, 2);
                break;
            case LEFT:
                setAvailableMoves(0, 2, 3, 5);
                break;
            case MIDDLE:
                setAvailableMoves(3, 5, 3, 5);
                break;
            case RIGHT:
                setAvailableMoves(6, 8, 3, 5);
                break;
            case BOTTOM_LEFT:
                setAvailableMoves(0, 2, 6, 8);
                break;
            case BOTTOM_MIDDLE:
                setAvailableMoves(3, 5, 6, 8);
                break;
            case BOTTOM_RIGHT:
                setAvailableMoves(6, 5, 6, 8);
                break;
        }
    }

    /**
     * Clears the available moves off the board
     */
    private void clearAvailableMoves()
    {
        String[][] board = currentState.getField().getBoard();
        for (int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++)
                if (isFieldOccupied(board, x, y))
                    board[x][y] = IField.EMPTY_FIELD;
    }

    /**
     * Changes the fields between the four poinits to be available
     *
     * @param x1 First corner X
     * @param x2 Second corner X
     * @param y1 First corner Y
     * @param y2 Second corner Y
     */
    private void setAvailableMoves(int x1, int x2, int y1, int y2)
    {
        String[][] board = currentState.getField().getBoard();
        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++)
                if (isFieldOccupied(board, x, y))
                    board[x][y] = IField.AVAILABLE_FIELD;
    }

    private Position getMicroPlacement(IMove move)
    {
        int x = move.getX();
        int y = move.getY();

        if (isTopLeft(x, y))
            return Position.TOP_LEFT;
        else if (isTopMiddle(x, y))
            return Position.TOP_MIDDLE;
        else if (isTopRight(x, y))
            return Position.TOP_RIGHT;
        else if (isLeft(x, y))
            return Position.LEFT;
        else if (isMiddle(x, y))
            return Position.MIDDLE;
        else if (isRight(x, y))
            return Position.RIGHT;
        else if (isBottomLeft(x, y))
            return Position.BOTTOM_LEFT;
        else if (isBottomMiddle(x, y))
            return Position.BOTTOM_MIDDLE;
        else if (isBottomRight(x, y))
            return Position.BOTTOM_RIGHT;
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="Poaition Checkers">
    //<editor-fold defaultstate="collapsed" desc="Top">
    private boolean isTopLeft(int x, int y)
    {
        return x == 0 && y == 0
               || x == 3 && y == 0
               || x == 6 && y == 0
               || x == 0 && y == 3
               || x == 3 && y == 3
               || x == 6 && y == 3
               || x == 0 && y == 6
               || x == 3 && y == 6
               || x == 6 && y == 6;
    }

    private boolean isTopMiddle(int x, int y)
    {
        return x == 1 && y == 0
               || x == 4 && y == 0
               || x == 7 && y == 0
               || x == 1 && y == 3
               || x == 4 && y == 3
               || x == 7 && y == 3
               || x == 1 && y == 6
               || x == 4 && y == 6
               || x == 7 && y == 6;
    }

    private boolean isTopRight(int x, int y)
    {
        return x == 2 && y == 0
               || x == 5 && y == 0
               || x == 8 && y == 0
               || x == 2 && y == 3
               || x == 5 && y == 3
               || x == 8 && y == 3
               || x == 2 && y == 6
               || x == 5 && y == 6
               || x == 8 && y == 6;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Middle">
    private boolean isLeft(int x, int y)
    {
        return x == 0 && y == 1
               || x == 3 && y == 1
               || x == 6 && y == 1
               || x == 0 && y == 4
               || x == 3 && y == 4
               || x == 6 && y == 4
               || x == 0 && y == 7
               || x == 3 && y == 7
               || x == 6 && y == 7;
    }

    private boolean isMiddle(int x, int y)
    {
        return x == 1 && y == 1
               || x == 4 && y == 1
               || x == 7 && y == 1
               || x == 1 && y == 4
               || x == 4 && y == 4
               || x == 7 && y == 4
               || x == 1 && y == 7
               || x == 4 && y == 7
               || x == 7 && y == 7;
    }

    private boolean isRight(int x, int y)
    {
        return x == 2 && y == 1
               || x == 5 && y == 1
               || x == 8 && y == 1
               || x == 2 && y == 4
               || x == 5 && y == 4
               || x == 8 && y == 4
               || x == 2 && y == 7
               || x == 5 && y == 7
               || x == 8 && y == 7;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bottom">
    private boolean isBottomLeft(int x, int y)
    {
        return x == 0 && y == 2
               || x == 3 && y == 2
               || x == 6 && y == 2
               || x == 0 && y == 5
               || x == 3 && y == 5
               || x == 6 && y == 5
               || x == 0 && y == 8
               || x == 3 && y == 8
               || x == 6 && y == 8;
    }

    private boolean isBottomMiddle(int x, int y)
    {
        return x == 1 && y == 2
               || x == 4 && y == 2
               || x == 7 && y == 2
               || x == 1 && y == 5
               || x == 4 && y == 5
               || x == 7 && y == 5
               || x == 1 && y == 8
               || x == 4 && y == 8
               || x == 7 && y == 8;
    }

    private boolean isBottomRight(int x, int y)
    {
        return x == 2 && y == 2
               || x == 5 && y == 2
               || x == 8 && y == 2
               || x == 2 && y == 5
               || x == 5 && y == 5
               || x == 8 && y == 5
               || x == 2 && y == 8
               || x == 5 && y == 8
               || x == 8 && y == 8;
    }
    //</editor-fold>
    //</editor-fold>

    private void UpdateMacroboard(IMove move)
    {
        if (checkWinner(0, 0))
            setWinMicro(0, 0);
        else if (checkWinner(3, 0))
            setWinMicro(3, 0);
        else if (checkWinner(6, 0))
            setWinMicro(6, 0);
        else if (checkWinner(0, 3))
            setWinMicro(0, 3);
        else if (checkWinner(3, 3))
            setWinMicro(3, 3);
        else if (checkWinner(6, 3))
            setWinMicro(6, 3);
        else if (checkWinner(0, 6))
            setWinMicro(0, 6);
        else if (checkWinner(3, 6))
            setWinMicro(3, 6);
        else if (checkWinner(6, 6))
            setWinMicro(6, 6);
    }

    /**
     * Checks if a microBoard has a winner
     *
     * @param x1 The x of the top left
     * @param y1 The y of the top left
     */
    private boolean checkWinner(int x, int y)
    {
        String[][] board = currentState.getField().getBoard();
        return checkMatch(board[x][y], board[x + 1][y], board[x + 2][y])
               || checkMatch(board[x][y + 1], board[x + 1][y + 1], board[x + 2][y + 1])
               || checkMatch(board[x][y + 2], board[x + 1][y + 2], board[x + 2][y + 2])
               || checkMatch(board[x][y], board[x][y + 1], board[x][y + 2])
               || checkMatch(board[x + 1][y], board[x + 1][y + 1], board[x + 1][y + 2])
               || checkMatch(board[x + 2][y], board[x + 2][y + 1], board[x + 2][y + 2])
               || checkMatch(board[x][y], board[x + 1][y + 1], board[x + 2][y + 2])
               || checkMatch(board[x + 2][y], board[x + 1][y + 1], board[x][y + 2]);
    }

    private boolean checkMatch(String a, String b, String c)
    {
        if (a.equalsIgnoreCase(IField.AVAILABLE_FIELD)
            || a.equalsIgnoreCase(IField.EMPTY_FIELD))
            return false;

        return a.equalsIgnoreCase(b) && a.equalsIgnoreCase(c);
    }

    private void setWinMicro(int x, int y)
    {
        String[][] macro = currentState.getField().getMacroboard();

        macro[x / 3][y / 3] = getCurrentPlayerChar();
    }

    private void printDebugField(String[][] microBoard)
    {
        System.out.println("Micro Boards");
        for (int y = 0; y < 9; y++)
        {
            if (y % 3 == 0 && y != 0)
                System.out.println("------+-------+------");
            for (int x = 0; x < 9; x++)
            {
                if (x % 3 == 0 && x != 0)
                    System.out.print("| ");
                System.out.print(microBoard[x][y] + " ");
            }
            System.out.println();
        }

        System.out.println("");
        System.out.println("Macro Board");
        String[][] macroboard = currentState.getField().getMacroboard();
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
                System.out.print(macroboard[x][y] + " ");
            System.out.println();
        }
    }

    /**
     *
     * @return
     */
    public String getCurrentPlayerChar()
    {
        if (currentPlayer == 0)
            return player_x;
        else
            return player_o;
    }
}
