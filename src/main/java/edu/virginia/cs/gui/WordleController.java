package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.IllegalWordException;
import edu.virginia.cs.wordle.LetterResult;
import edu.virginia.cs.wordle.Wordle;
import edu.virginia.cs.wordle.WordleImplementation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class WordleController {
    @FXML
    public Label RowOneOne;
    @FXML
    public Label RowOneTwo;
    public Label RowOneThree;
    public Label RowOneFour;
    public Label RowOneFive;

    public Wordle wordle = new WordleImplementation();
    public Label RowTwoOne;
    public Label RowTwoTwo;
    public Label RowTwoThree;
    public Label RowTwoFour;
    public Label RowTwoFive;
    public Label RowThreeOne;
    public Label RowThreeTwo;
    public Label RowThreeThree;
    public Label RowThreeFour;
    public Label RowThreeFive;
    public Label RowFourOne;
    public Label RowFourTwo;
    public Label RowFourThree;
    public Label RowFourFour;
    public Label RowFourFive;
    public Label RowFiveOne;
    public Label RowFiveTwo;
    public Label RowFiveThree;
    public Label RowFiveFour;
    public Label RowFiveFive;
    public Label RowSixOne;
    public Label RowSixTwo;
    public Label RowSixThree;
    public Label RowSixFour;
    public Label RowSixFive;
    public Button playAgainButton;
    public Button closeGameButton;
    public GridPane grid;
    boolean isGameOver = false;

    ArrayList<Label> field = new ArrayList<>();


    int index = 0;


    @FXML
    private Label welcomeText;

    protected void setArray() {
        field.add(RowOneOne);
        field.add(RowOneTwo);
        field.add(RowOneThree);
        field.add(RowOneFour);
        field.add(RowOneFive);
        field.add(RowTwoOne);
        field.add(RowTwoTwo);
        field.add(RowTwoThree);
        field.add(RowTwoFour);
        field.add(RowTwoFive);
        field.add(RowThreeOne);
        field.add(RowThreeTwo);
        field.add(RowThreeThree);
        field.add(RowThreeFour);
        field.add(RowThreeFive);
        field.add(RowFourOne);
        field.add(RowFourTwo);
        field.add(RowFourThree);
        field.add(RowFourFour);
        field.add(RowFourFive);
        field.add(RowFiveOne);
        field.add(RowFiveTwo);
        field.add(RowFiveThree);
        field.add(RowFiveFour);
        field.add(RowFiveFive);
        field.add(RowSixOne);
        field.add(RowSixTwo);
        field.add(RowSixThree);
        field.add(RowSixFour);
        field.add(RowSixFive);
    }

    protected void game() {
        wordle.getGame();
    }

    int max = 5;
    int min = 0;

    protected void handleTextInput(KeyCode keyCode) {
        if (index <= max) {
            if (keyCode.isLetterKey() && index < max) {
                welcomeText.setText("Wordle");
                if (field.get(index).getText().length() < 1) {
                    field.get(index).setText(keyCode.toString());
                    field.get(index).setTextFill(Color.WHITE);
                    index++;

                } else {
                    field.get(index).setText(keyCode.toString());
                    field.get(index).setTextFill(Color.WHITE);
                }

            } else if (keyCode == KeyCode.BACK_SPACE && index > min) {
                welcomeText.setText("Wordle");
                field.get(index - 1).setText("");
                if (index > min) {
                    index--;
                }
            } if ((keyCode == KeyCode.ENTER || index == min + 5) && index % 5 == 0 && index > 0 && index > min) {
                String guess = "";
                StringBuilder strBuilder = new StringBuilder(guess);

                for (int i = index - 5; i < index; i++) {
                    strBuilder.append(field.get(i).getText());
                }
                guess = strBuilder.toString();
                try {
                    LetterResult[] result = wordle.submitGuess(guess);
                    setColors(result);
                    max += 5;
                    min += 5;
                    if (wordle.isWin()) {
                        welcomeText.setText("You Win! Play Again?");
                        max = 0;
                        isGameOver = true;
                    } else if (wordle.isLoss()) {
                        welcomeText.setText("You lost! The answer was: " + wordle.getAnswer() + " Play Again?");
                        max = 0;
                        isGameOver = true;

                    }


                } catch (IllegalWordException e) {
                    welcomeText.setText("Invalid Word Try Again");
                }


            }
        }
        if(isGameOver){
            playAgainButton.setVisible(true);
            closeGameButton.setVisible(true);
        }

    }

    protected void setColors(LetterResult[] results) {
        int k = 0;
        for (int i = index - 5; i < index; i++) {
            if (results[k] == LetterResult.GREEN) {
                field.get(i).setStyle("-fx-background-color: green");

            } else if (results[k] == LetterResult.YELLOW) {
                field.get(i).setStyle("-fx-background-color: #E1C16E");
            } else {
                field.get(i).setStyle("-fx-background-color: grey");
            }
            k++;
        }
    }

    @FXML
    protected void onEnterButtonClick() {
        handleTextInput(KeyCode.ENTER);
    }
    @FXML
    protected void onQuitButtonClick() {
        Platform.exit();
    }
    @FXML
    protected void onPlayButtonClick() {
        max = 5;
        min = 0;
        index = 0;
        RowOneOne.requestFocus();
        for (Label label : field) {
            label.setText("");
            label.setStyle("-fx-background-color: black");
        }
        wordle = wordle.getGame();
        isGameOver = false;
        playAgainButton.setVisible(false);
        closeGameButton.setVisible(false);
        welcomeText.setText("Wordle");
    }
}


