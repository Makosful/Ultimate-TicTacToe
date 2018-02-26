package game.gui.controller;

import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Axl
 */
public class GameModeController implements Initializable
{

    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
    }

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        model.changeScene(2);
    }

}
