package sudoku;

public class SudokuSolver {
	public static void main(String[] args) {

		// sudoku board to be solved

		int[][] board = { { 0, 9, 0, 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 9, 6, 0, 4, 8, 5 },

				{ 0, 0, 0, 5, 8, 1, 0, 0, 0 }, { 0, 0, 4, 0, 0, 0, 0, 0, 0 }, { 5, 1, 7, 2, 0, 0, 9, 0, 0 },

				{ 6, 0, 2, 0, 0, 0, 3, 7, 0 }, { 1, 0, 0, 8, 0, 4, 0, 2, 0 }, { 7, 0, 6, 0, 0, 0, 8, 1, 0 },

				{ 3, 0, 0, 0, 9, 0, 0, 0, 0 } };

		// checking if board can be solved
		// System.out.println(SudokuValidator.isValid(board));
		System.out.println(solve(board));

	}

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
							// printing the solved board
							// (but only after it's fully complete)
							if (i == 0 && j == 0) {
								printBoard(solution);
							}
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
		return true;
	}

	public static void printBoard(int[][] board) {
		// System.out.println("\n\n\n");
		for (int i = 0; i < board.length; i++) {
			System.out.println("");
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + "  ");

			}
		}
	}

}
