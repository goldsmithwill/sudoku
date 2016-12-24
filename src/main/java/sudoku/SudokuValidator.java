package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
	public static boolean isValid(int[][] solution) {

		// checking if solution length is 9
		if (solution.length != 9) {
			return false;
		}

		// nested for loops check for valid rows and columns
		// i increments row+column
		for (int i = 0; i < solution.length; i++) {

			// checking if solution nested arrays have length 9
			if (solution[i].length != 9) {
				return false;
			}

			// HashSets for checking repeats
			Set<Integer> solutionSetRow = new HashSet<Integer>();
			Set<Integer> solutionSetColumn = new HashSet<Integer>();
			Set<Integer> solutionSetSquare = new HashSet<Integer>();

			// j increments row+column value
			for (int j = 0; j < solution[0].length; j++) {
				// getting row, column values
				int rowVal = solution[i][j];
				int colVal = solution[j][i];

				// getting subsquare values
				int squareValX = (int) Math.floor((Math.floor(i / 3) * 3) + j / 3);
				int squareValY = (i * 3 % 9 + j % 3);
				int squareVal = solution[squareValX][squareValY];

				// checking if the row, column, subsquare values are 1-9
				if ((rowVal > 9 || rowVal < 0) || (colVal > 9 || colVal < 0) || (squareVal > 9 || squareVal < 0)) {
					return false;
				}
				
				// if hashsets already have row, col, or square vals and they're
				// not 0, then false
				if (!solutionSetRow.add(rowVal) && rowVal != 0) {
					SudokuGUI.highlightInvalid(i, j);
					return false;
				}

				if (!solutionSetColumn.add(colVal) && colVal != 0) {
					SudokuGUI.highlightInvalid(j, i);
					return false;
				}

				if (!solutionSetSquare.add(squareVal) && squareVal != 0) {
					SudokuGUI.highlightInvalid(squareValX, squareValY);
					return false;
				}

			}
		}

		// if solution is valid
		SudokuGUI.highlightValid();
		return true;
	}
}
