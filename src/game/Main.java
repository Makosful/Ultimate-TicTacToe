package game;

import com.github.makosful.stage.entities.Docking;
import com.github.makosful.stage.exception.SceneIdAlreadyTakenException;
import com.github.makosful.stage.utils.StageManager;
import game.gui.model.Model;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Axl
 */
public class Main extends Application {

    private Model model;
    private StageManager sm;

    @Override
    public void start(Stage stage) throws Exception {
        sm = new StageManager(stage);
        model = Model.getInstance();
        model.setStageManager(sm);

        registerScenes(sm);
        sm.setActiveScene(1);

//        stage.setTitle("Tic Tac Troll | Select a Game mode");
        
        
        stage.show();
        stage.setResizable(false);
        sm.getPlacementUtil().setAutoAlign(true);
        sm.getPlacementUtil().setAlignment(Docking.CENTER);
    }

    /**
     * Register new scenes here
     *
     * @param sm
     */
    private void registerScenes(StageManager sm) {
        try {
            sm.registerScene(1, getClass().getResource("gui/view/GameMode.fxml"));
            sm.registerScene(2, getClass().getResource("gui/view/Game.fxml"));
        } catch (SceneIdAlreadyTakenException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
