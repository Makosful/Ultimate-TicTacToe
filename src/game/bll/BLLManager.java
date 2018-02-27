package game.bll;

import game.be.Move;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Axl
 */
public class BLLManager
{

    public BLLManager()
    {
    }

    public Move[][] m(GridPane gridPane)
    {
        Move[][] moves = new Move[9][9];
        int acc = 0;
        int dec = 0;
        for (int i = 0; i < 9; i++)
        {

            GridPane grid = (GridPane) gridPane.getChildren().get(i);
            for (int j = 0; j < 3; j++)
            {
                Button button = (Button) grid.getChildren().get(j);
                int x = calcX(acc);
                int y = calcY(acc);
                button.setText("O");
                moves[x][y] = new Move(x, y);
                acc++;
            }
            acc += 6;
            for (int j = 0; j < 3; j++)
            {
                Button button = (Button) grid.getChildren().get(j + 3);
                int x = calcX(acc);
                int y = calcY(acc);
                button.setText("O");
                moves[x][y] = new Move(x, y);
                acc++;
            }
            acc += 6;
            for (int j = 0; j < 3; j++)
            {
                Button button = (Button) grid.getChildren().get(j + 6);
                int x = calcX(acc);
                int y = calcY(acc);
                button.setText("O");
                moves[x][y] = new Move(x, y);
                acc++;
            }

            // Corrects (a) every 3 rows
            dec++;
            if (dec <= 2)
                acc -= (6 * 3);
            else
                dec = 0;
        }
        return moves;
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
}
