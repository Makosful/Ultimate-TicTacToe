package game.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class GameController implements Initializable
{

    @FXML
    private ToggleGroup windowSize;
    @FXML
    private RadioMenuItem size1;
    @FXML
    private RadioMenuItem size2;
    @FXML
    private RadioMenuItem size3;
    @FXML
    private GridPane gridMacro;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
    }

    @FXML
    private void handleRestart(ActionEvent event)
    {
    }

    @FXML
    private void handleClose(ActionEvent event)
    {
    }

    @FXML
    private void handleSizeChange(ActionEvent event)
    {
        RadioMenuItem rmi = (RadioMenuItem) event.getSource();
        switch (rmi.getId())
        {
            case "size1":
                changeSizeSmall();
                break;
            case "size2":
                changeSizeMedium();
                break;
            case "size3":
                changeSizeBig();
                break;
        }
    }

    private void changeSizeBig()
    {
    }

    private void changeSizeMedium()
    {
    }

    private void changeSizeSmall()
    {
    }

    @FXML
    private void handleButtonPress(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        button.setText("X");
    }

    private void test()
    {
        for (int i = 0; i < 9; i++)
        {
            GridPane grid = (GridPane) gridMacro.getChildren().get(i);
            for (int j = 0; j < 9; j++)
            {
                Button button = (Button) grid.getChildren().get(j);
                button.setPrefSize(60, 60);
            }
        }
    }

}
