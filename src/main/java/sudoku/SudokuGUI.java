package sudoku;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class SudokuGUI extends Application {
	// hashmap w/ textfield location and the textfield
	static HashMap<String, TextField> board = new HashMap<String, TextField>();

	// array of pre-made sudoku puzzles for generate button
	int[][][] generatePuzzleArray = {
			{ { 2, 4, 0, 0, 0, 0, 0, 0, 6 }, { 5, 7, 1, 3, 0, 6, 4, 0, 9 }, { 9, 0, 6, 5, 0, 0, 0, 0, 0 },
					{ 7, 0, 0, 4, 0, 0, 0, 5, 0 }, { 0, 8, 0, 9, 0, 1, 0, 2, 0 }, { 0, 9, 0, 0, 0, 7, 0, 0, 3 },
					{ 0, 0, 0, 0, 0, 8, 2, 0, 1 }, { 1, 0, 7, 2, 0, 3, 8, 4, 5 }, { 8, 0, 0, 0, 0, 0, 0, 6, 7 } },
			{ { 0, 9, 0, 0, 0, 0, 5, 0, 4 }, { 1, 0, 0, 6, 0, 0, 2, 0, 0 }, { 3, 7, 0, 0, 4, 0, 0, 1, 8 },
					{ 7, 1, 0, 4, 2, 6, 8, 0, 0 }, { 0, 0, 9, 0, 0, 0, 3, 0, 0 }, { 0, 0, 8, 3, 5, 9, 0, 2, 1 },
					{ 6, 3, 0, 0, 8, 0, 0, 5, 9 }, { 0, 0, 1, 0, 0, 4, 0, 0, 7 }, { 5, 0, 7, 0, 0, 0, 0, 3, 0 } } };

	// the current index that's being used for the generate puzzle array
	// this exists to help avoid repeats
	int currentGenerateIndex = 0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	private void init(Stage primaryStage) {
		Group root = new Group();
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);

		// VBox to hold tilepane and hbox
		VBox vbox = new VBox(5);

		// Pane to hold tilepane and the grid image
		Pane pane = new Pane();

		// setting tilepane to hold textfields on the GUI
		TilePane tilePane = new TilePane();
		tilePane.setPrefColumns(9);

		// hbox to hold solve + validate buttons
		HBox hbox = new HBox(5);
		hbox.setAlignment(Pos.CENTER);

		// creating buttons for hbox

		Button generateButton = new Button("Generate Puzzle");
		Button validateButton = new Button("Validate Puzzle");
		Button solveButton = new Button("Solve Puzzle");
		Button resetButton = new Button("Reset Board");

		// creating listeners for buttons
		generateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// gets a random puzzle from the array
				// and updates the GUI to show it

				// creating random number for index for generate button
				// needs to be index for puzzle that is not same as last one
				Random random = new Random();

				int randomNum = 0;
				do {
					randomNum = random.nextInt(generatePuzzleArray.length);
				} while (randomNum == currentGenerateIndex);

				currentGenerateIndex = randomNum;

				// going through array and adding it to map
				// also making sure 0s become blank spaces
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						String parsedArray = "[" + i + ", " + j + "]";

						String cellText = "";

						if (generatePuzzleArray[randomNum][i][j] != 0) {
							cellText = Integer.toString(generatePuzzleArray[randomNum][i][j]);
						}

						board.get(parsedArray).setText(cellText);
					}
				}
			}
		});

		validateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				SudokuValidator.isValid(mapToArray(board));
			}
		});

		solveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				SudokuSolver.solve(mapToArray(board));
				SudokuValidator.isValid(mapToArray(board));
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// iterating through map
				for (Entry<String, TextField> entry : board.entrySet()) {
					// getting cell
					TextField cell = board.get(entry.getKey());

					// setting cell background color to white
					Paint paint = Paint.valueOf("#ffffff");
					cell.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));

					// setting cell text to blank
					cell.setText("");
				}

			}
		});

		// adding buttons to hbox
		hbox.getChildren().addAll(generateButton, validateButton, solveButton, resetButton);

		// creating cells
		for (int i = 0; i < 81; i++) {
			// creating cell textField
			TextField cell = new TextField("");

			// setting cell font + size
			cell.setPrefSize(50, 50);
			cell.setFont(new Font(25));

			// setting cell background color to white
			Paint paint = Paint.valueOf("#ffffff");
			cell.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));

			// creating cell listener
			cell.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// getting cell
					StringProperty textProperty = (StringProperty) observable;
					TextField cell = (TextField) textProperty.getBean();

					// resetting cell color to white
					Paint paint = Paint.valueOf("#ffffff");
					cell.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));

					if (!newValue.equals("")) {
						try {
							// getting new value for cell
							int cellValue = Integer.parseInt(newValue.replace(oldValue, ""));

							// setting textField value to cellValue
							cell.setText(Integer.toString(cellValue));

						} catch (NumberFormatException e) {

							// if not number, set textfield back to old value
							cell.setText(oldValue);
						}
					}
				}
			});

			// adding cell to tilePane
			tilePane.getChildren().add(cell);

			// getting x and y for cell location
			int x = i % 9;
			int y = (int) Math.floor(i / 9);

			// adding cell location and textfield to board HashMap
			board.put(Arrays.toString(new int[] { x, y }), cell);
		}

		// creating grid image
		Image gridImg = new Image(getClass().getResource("grid.png").toExternalForm());
		ImageView grid = new ImageView();
		grid.setImage(gridImg);

		// adding tilepane and grid to pane
		// so grid can stack on tilepane
		pane.getChildren().addAll(tilePane, grid);

		// adding tilepane and hbox to vbox
		// and then adding vbox to GUI
		vbox.getChildren().addAll(pane, hbox);
		root.getChildren().add(vbox);
	}

	public int[][] mapToArray(HashMap<String, TextField> boardMap) {
		// converts hashmap to array that works with validator + solver
		// creating board array
		int[][] boardArray = new int[9][9];

		// for each to iterate through map

		for (Entry<String, TextField> entry : boardMap.entrySet()) {

			// getting location and cell value and putting them in array
			int[] location = stringToArray(entry.getKey());
			TextField cellTextField = (TextField) entry.getValue();
			int cellValue = 0;

			// if cell is blank, fill cell with number
			// if not, it stays at default of 0
			if (!cellTextField.getText().equals("")) {
				cellValue = Integer.parseInt(cellTextField.getText());
			}

			// add cell value to board array
			boardArray[location[0]][location[1]] = cellValue;

		}

		return boardArray;
	}

	public static void highlightInvalid(int i, int j) {
		// highlighting the cell that doesn't work red
		Paint paint = Paint.valueOf("#ff9a9a");
		TextField cell = board.get(Arrays.toString(new int[] { i, j }));
		cell.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));

	}

	public static void highlightValid() {
		// highlighting all the cells green if the puzzle is valid
		Paint paint = Paint.valueOf("#adebad");

		for (Entry<String, TextField> entry : board.entrySet()) {
			if (!entry.getValue().getText().equals("")) {
				entry.getValue()
						.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));

			}
		}
	}

	public int[] stringToArray(String coordsString) {
		// converts string to location array for hashmap
		int[] coords = new int[2];
		coords[0] = Character.getNumericValue(coordsString.charAt(1));
		coords[1] = Character.getNumericValue(coordsString.charAt(4));
		return coords;
	}

}