package game.gui.controller;

import com.github.makosful.stage.entities.Docking;
import game.bll.GameManager;
import game.bll.interfaces.IField;
import game.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private GameModeController gmc;

    @FXML // Used in handleSizeChange
    private ToggleGroup windowSize;
    @FXML // Used in handleSizeChange
    private RadioMenuItem size1;
    @FXML // Used in handleSizeChange
    private RadioMenuItem size2;
    @FXML // Used in handleSizeChange
    private RadioMenuItem size3;

    @FXML
    private GridPane gridMacro;
    @FXML
    private AnchorPane botAnchorPane;
    @FXML
    private Label lblAmountOfTurns;

    private boolean isFieldReady;
    private int turnCounter;

    private String playerOneName;
    private String playerOneColor;
    private String playerTwoName;
    private String playerTwoColor;

    private final static String BTN_GREEN_TEXT = "-fx-text-fill: rgba(0, 255, 0, 0.5);";

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

        model.calculateFieldPositions(gridMacro);

        setupListeners();
        saveGameModeControllerInfo();
        System.out.println(playerOneName);
        System.out.println(playerOneColor);

        System.out.println(playerTwoName);
        System.out.println(playerTwoColor);

    }

    private boolean isFieldOccupied(String[][] macro, int x, int y)
    {
        return macro[x][y].equalsIgnoreCase(GameManager.PLAYER_O) || macro[x][y].equalsIgnoreCase(GameManager.PLAYER_X);
    }

    private void setupListeners()
    {
        for (int i = 0; i < gridMacro.getChildren().size(); i++)
        {
            GridPane grid = (GridPane) gridMacro.getChildren().get(i);
            for (int j = 0; j < grid.getChildren().size() - 1; j++)
            {
                Button btn = (Button) grid.getChildren().get(j);

                btn.setOnMouseEntered((MouseEvent me) ->
                {
                    btn.setText(model.getPlayerChar());
                    btn.setStyle(BTN_GREEN_TEXT);
                    isFieldReady = true;
                });

                btn.setOnMouseExited((MouseEvent me) ->
                {
                    if (!btn.isDisabled())
                    {
                        btn.setText("");
                    }
                    else
                    {
                        //Do nothing
                    }
                });
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="FXML Callbacks">
    /**
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {

    }

    private void restartDialog()
    {
        Alert alert = new Alert(AlertType.WARNING, "Do you really wish to restart?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("");
        alert.showAndWait().ifPresent(rs ->
        {
            if (rs == ButtonType.YES)
            {
                for (int i = 0; i < gridMacro.getChildren().size(); i++)
                {
                    GridPane grid = (GridPane) gridMacro.getChildren().get(i);
                    for (int j = 0; j < grid.getChildren().size() - 1; j++)
                    {
                        Button btn = (Button) grid.getChildren().get(j);

                        btn.setText("");
                        btn.setDisable(false);
                        turnCounter = 0;
                    }
                }
            }
        });
    }

    private void shutdownDialog()
    {
        Alert alert = new Alert(AlertType.WARNING, "Do you really wish to shut down the game?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("");
        alert.showAndWait().ifPresent(rs ->
        {
            if (rs == ButtonType.YES)
            {
                System.exit(0);
            }
        });
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleRestart(ActionEvent event)
    {
        restartDialog();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleClose(ActionEvent event)
    {
        shutdownDialog();
    }

    /**
     *
     * @param event
     */
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

    /**
     *
     */
    @FXML
    private void handleButtonPress(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        if (isFieldReady)
        {
            String playerChar = model.getPlayerChar();
            if (model.doMove(btn.getId()))
            {
                btn.setText(playerChar);
                if ("X".equals(playerChar))
                {
//                    gridMacro.getChildren()..setStyle("-fx-background-: black");
                }
                btn.disableProperty().set(true);
                isFieldReady = !isFieldReady;
                turnCounter++;
                updateLblCounter();
            }
        }
        showCurrentField();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Change Size">
    /**
     * Changes the field size
     */
    private void changeSizeBig()
    {
        changeSize(90, 36);
    }

    /**
     * Changes the field size to medium
     */
    private void changeSizeMedium()
    {
        changeSize(60, 24);
    }

    /**
     * Change the field size to small
     */
    private void changeSizeSmall()
    {
        changeSize(30, 12);
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
    //</editor-fold>

    private void showCurrentField()
    {
        String[][] macro = model.getMakro().getField().getMacroboard();
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                GridPane grid = (GridPane) gridMacro.getChildren().get(model.getGridPos(x, y));
                if (!isFieldOccupied(macro, x, y))
                {
                    // If the Macro Field isn't won by either player
                    // AKA: The field is empty
                    grid.setStyle("-fx-background-color: transparent;");
                }
                if (macro[x][y].equalsIgnoreCase(IField.AVAILABLE_FIELD))
                {
                    // If the MAcro Field is available for moves
                    // AKA: The field is Available
                    grid.setStyle("-fx-border-color: salmon;");
                }
                if (macro[x][y].equalsIgnoreCase(GameManager.PLAYER_O))
                {
                    // If player X has won this field
                    grid.setStyle("-fx-background-color: #0000FF;");
                }
                if (macro[x][y].equalsIgnoreCase(GameManager.PLAYER_X))
                {
                    // If player O has won this field
                    grid.setStyle("-fx-background-color: #FF0000;");
                }
            }
        }
        //System.out.print(model.getMakro().getField().AVAILABLE_FIELD);
    }

    private void updateLblCounter()
    {
        // This actually tracks the number of moves.
        // One turn is when both players have had a chance to move
        if (turnCounter == 1)
        {
            lblAmountOfTurns.setText(String.valueOf(turnCounter) + " turn");
        }
        else
        {
            lblAmountOfTurns.setText(String.valueOf(turnCounter) + " turns");
        }
    }

    private void saveGameModeControllerInfo()
    {
        playerOneName = gmc.getPlayerOneName();
        playerOneColor = gmc.getPlayerOneColor();

        playerTwoName = gmc.getPlayerTwoName();
        playerTwoColor = gmc.getPlayerTwoColor();
    }
}
