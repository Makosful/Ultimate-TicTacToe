package game.gui.model;

import com.github.makosful.stage.utils.StageManager;

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

    private StageManager sm;

    /**
     * Singleton Constructor
     */
    private Model()
    {
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
}
