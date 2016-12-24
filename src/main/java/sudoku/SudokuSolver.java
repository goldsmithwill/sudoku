package sudoku;

public class SudokuSolver {
	public static boolean solve(int[][] solution) {
		// nested for loops go through sudoku board
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// if the square is empty
				if (solution[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						// putting in values 1-9 in the square
						solution[i][j] = k;
						
						/*
						 * if the board with the new value is valid and if it's
						 * able to be solved (it goes on a crazy recursive thing
						 * that backtracks at the end)
						 */
						if (SudokuValidator.isValid(solution) && solve(solution)) {
							return true;
						}
						
						
						// if the 1-9 value doesn't work, reset square to 0
						solution[i][j] = 0;
					}
					/*
					 * it returns false if the values that were put in earlier
					 * were wrong, helping with the recursive
					 */
					return false;
				}
			}
		}
		// update the GUI to the current board value
		// (but only after it's fully complete)
		updateMap(solution);

		return true;
	}

	public static void updateMap(int[][] solution) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String parsedArray = "[" + i + ", " + j + "]";
				SudokuGUI.board.get(parsedArray).setText(Integer.toString(solution[i][j]));
			}
		}
	}

}
