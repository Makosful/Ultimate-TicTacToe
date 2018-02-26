package game.gui.controller;

import com.github.makosful.stage.entities.Docking;
import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class GameController implements Initializable
{

    private Model model;

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
        model = Model.getInstance();
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

    @FXML
    private void handleButtonPress(ActionEvent event)
    {
    }

    /**
     * Changes the field size
     */
    private void changeSizeBig()
    {
        changeSize(90, 40);
    }

    /**
     * Changes the field size to medium
     */
    private void changeSizeMedium()
    {
        changeSize(60, 25);
    }

    /**
     * Change the field size to small
     */
    private void changeSizeSmall()
    {
        changeSize(30, 10);
    }

    /**
     * Changes the size of the field
     *
     * @param p The size of each button
     * @param f The font size
     */
    private void changeSize(int p, int f)
    {
        for (int i = 0; i < gridMacro.getChildren().size(); i++)
        {
            GridPane grid = (GridPane) gridMacro.getChildren().get(i);
            for (int j = 0; j < gridMacro.getChildren().size(); j++)
            {
                Button button = (Button) grid.getChildren().get(j);
                button.setPrefSize(p, p);
                button.setFont(Font.font(f));
            }
        }
        Stage stage = (Stage) gridMacro.getScene().getWindow();
        stage.sizeToScene();
        model.getPlacementUtil().alignStage(stage, Docking.CENTER);
    }

}
