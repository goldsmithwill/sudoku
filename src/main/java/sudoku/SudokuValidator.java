package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
	public SudokuValidator() {

	}

	public static void main(String[] args) {

		// sudoku solution to be validated

		int[][] solution = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
				{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },

				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },

				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		// printing whether solution is valid or not (boolean return)
		System.out.println(isValid(solution));

	}

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
				// getting row, column, subsquare values
				int rowVal = solution[i][j];
				int colVal = solution[j][i];
				int squareVal = solution[(int) Math.floor((Math.floor(i / 3) * 3) + j / 3)][i * 3 % 9 + j % 3];

				// checking if the row, column, subsquare values are 1-9
				if ((rowVal > 9 || rowVal < 0) || (colVal > 9 || colVal < 0) || (squareVal > 9 || squareVal < 0)) {
					return false;
				}

				// if hashsets already have row, col, or square vals and they're not 0, then false
				if (!solutionSetRow.add(rowVal) && rowVal != 0) {
					return false;
				}

				if (!solutionSetColumn.add(colVal) && colVal != 0) {
					return false;
				}

				if (!solutionSetSquare.add(squareVal) && squareVal != 0) {
					return false;
				}

			}
		}

		// if solution is valid
		return true;
	}
}
