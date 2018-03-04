package game.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Axl
 */
public class GameModeController implements Initializable
{

    @FXML
    private Label lblPlayerOneName;
    @FXML
    private Label lblPlayerTwoColor;
    @FXML
    private Label lblPlayerTwoName;
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
    @FXML
    private JFXColorPicker playerOneColorPicker;
    @FXML
    private JFXColorPicker playerTwoColorPicker;
    @FXML
    private TextField txtPlayerOneName;
    @FXML
    private TextField txtPlayerTwoName;

    private String playerOneColor;
    private String playerOneName;
    private String playerTwoColor;
    private String playerTwoName;

    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();

        playerOneColorPicker.setValue(Color.BLUE);
        playerTwoColorPicker.setValue(Color.RED);

        setupNameListeners();
    }

    private void setPlayerOneColor(ActionEvent event)
    {
        Color p1Color = playerOneColorPicker.getValue();
        playerOneColor = p1Color.toString();
        System.out.println(playerOneColor);
    }

    @FXML
    private void setPlayerTwoColor(ActionEvent event)
    {
        Color p2Color = playerOneColorPicker.getValue();
        playerTwoColor = p2Color.toString();
        System.out.println(playerTwoColor);
    }

    @FXML
    private void PlayVsComputer(ActionEvent event)
    {
        if (btnPvC.isSelected())
        {
            btnPvC.setDisable(true);
            btnPvP.setDisable(false);
            lblPlayerTwoColor.setText("Color for Bot");
            lblPlayerTwoName.setText("Name for Bot");
        }
    }

    @FXML
    private void PlayVsPlayer(ActionEvent event)
    {
        if (btnPvP.isSelected())
        {
            btnPvC.setDisable(false);
            btnPvP.setDisable(true);
            lblPlayerTwoColor.setText("Color for Player 2");
            lblPlayerTwoName.setText("Name for Player 2");
        }
    }

    @FXML
    private void StartGame(ActionEvent event)
    {
        model.changeScene(2);
        System.out.print(txtPlayerOneName.getText().trim() + " & " + txtPlayerTwoName.getText().trim());
    }

    public String getPlayerOneColor()
    {
        return playerOneColor;
    }

    public String getPlayerOneName()
    {
        return playerOneName;
    }

    public String getPlayerTwoName()
    {
        return playerTwoName;
    }

    public String getPlayerTwoColor()
    {
        return playerTwoColor;
    }

    private void setupNameListeners()
    {
        txtPlayerOneName.textProperty().addListener((observable) ->
        {
            playerOneName = txtPlayerOneName.getText().trim();
        });

        txtPlayerTwoName.textProperty().addListener((observable) ->
        {
            playerTwoName = txtPlayerTwoName.getText().trim();
        });

    }
}
