package game.gui.controller;

import com.jfoenix.controls.JFXButton;
import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Axl
 */
public class GameModeController implements Initializable {

    private Model model;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ToggleButton btnPvC;
    @FXML
    private ToggleGroup GameMode;
    @FXML
    private ToggleButton btnPvP;
    @FXML
    private JFXButton btnPlay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();
    }

    @FXML
    private void PlayVsComputer(ActionEvent event) {
        if (btnPvC.isSelected()) {
            btnPvC.setDisable(true);
            btnPvP.setDisable(false);
        }
    }

    @FXML
    private void PlayVsPlayer(ActionEvent event) {
        if (btnPvP.isSelected()) {
            btnPvC.setDisable(false);
            btnPvP.setDisable(true);
        }
    }

    @FXML
    private void StartGame(ActionEvent event) {
        model.changeScene(2);
    }

}
