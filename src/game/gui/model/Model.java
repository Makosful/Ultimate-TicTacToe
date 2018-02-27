package game.gui.model;

import com.github.makosful.stage.utils.PlacementUtil;
import com.github.makosful.stage.utils.StageManager;
import game.be.Move;
import game.bll.BLLManager;
import game.bll.GameManager;
import game.bll.GameState;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Axl
 */
public class Model
{

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private static final Model INSTANCE = new Model();

    public static Model getInstance()
    {
        return INSTANCE;
    }
    //</editor-fold>

    // Objects
    private final GameManager gm;
    private final BLLManager bll;
    private StageManager sm;

    // Data
    private final Move[][] moves;

    /**
     * Singleton Constructor
     */
    private Model()
    {
        gm = new GameManager(new GameState());
        bll = new BLLManager();

        moves = new Move[9][9];
    }

    /**
     * Gives the Models class a StageManager
     *
     * @param sm
     */
    public void setStageManager(StageManager sm)
    {
        this.sm = sm;
    }

    /**
     * Change the Scene being displayed.
     *
     * @param i The ID if the scene. See Main class for list of registered
     *          Scenes and their ID
     */
    public void changeScene(int i)
    {
        sm.setActiveScene(i);
    }

    /**
     * Hands over the Placement Utility
     *
     * @return
     */
    public PlacementUtil getPlacementUtil()
    {
        return sm.getPlacementUtil();
    }

    public void calculateBoardFields(GridPane grid)
    {
        bll.m(grid);
    }
}
