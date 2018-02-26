package game.gui.controller;

import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Axl
 */
public class GameModeController implements Initializable
{

    private Model model;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        model.changeStage(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
    }

}
