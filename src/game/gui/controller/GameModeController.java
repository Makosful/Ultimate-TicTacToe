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
public class GameModeController implements Initializable
{

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
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
    }

    @FXML
    private void PlayVsComputer(ActionEvent event)
    {
    }

    @FXML
    private void PlayVsPlayer(ActionEvent event)
    {
    }

    @FXML
    private void StartGame(ActionEvent event)
    {
        model.changeScene(2);
    }

}
