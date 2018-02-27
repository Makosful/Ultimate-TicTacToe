package game.gui.controller;

import com.github.makosful.stage.entities.Docking;
import game.be.Move;
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
    @FXML // Used in handleSizeChange
    private RadioMenuItem size1;
    @FXML // Used in handleSizeChange
    private RadioMenuItem size2;
    @FXML // Used in handleSizeChange
    private RadioMenuItem size3;
    @FXML
    private GridPane gridMacro;

    private Move[][] moves;

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

        moves = new Move[9][9];

        int acc = 0;
        int dec = 0;
        for (int i = 0; i < 9; i++)
        {

            GridPane grid = (GridPane) gridMacro.getChildren().get(i);
            for (int j = 0; j < 3; j++)
                acc = doTheThing(grid, j, acc);
            acc += 6;
            for (int j = 0; j < 3; j++)
                acc = doTheThing(grid, j + 3, acc);
            acc += 6;
            for (int j = 0; j < 3; j++)
                acc = doTheThing(grid, j + 6, acc);

            // Corrects (a) every 3 rows
            dec++;
            if (dec <= 2)
                acc -= (6 * 3);
            else
                dec = 0;
        }
    }

    /**
     * Calculates the XY position of the give field
     *
     * @param grid  The GridPan containing the field
     * @param field The field to calculate
     * @param acc   The position of the field in the grid, as a line
     *
     * @return Increases the acc once
     */
    private int doTheThing(GridPane grid, int field, int acc)
    {
        int y;
        int x;
        Button button = (Button) grid.getChildren().get(field);
        button.setFont(Font.font(24));
        x = calcX(acc);
        y = calcY(acc);
        button.setText(x + ";" + y);
        moves[x][y] = new Move(x, y);
        acc++;
        return acc;
    }

    /**
     * Calculates the X position of the field
     *
     * @param a The position of the field in a line going from left to right,
     *          wrapping around every 9 fields
     *
     * @return Returns the X position of the field
     */
    private int calcX(double a)
    {
        return (int) (a % 9);
    }

    /**
     * Calculates the y position of the field
     *
     * @param a The position of the field in a line of fields, wraaping around
     *          at every 9 fields
     *
     * @return Returns the y position of the field
     */
    private int calcY(double a)
    {
        double t = a / 9;
        return (int) Math.floor(t);
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
