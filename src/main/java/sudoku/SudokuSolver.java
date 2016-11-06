package sudoku;

public class SudokuSolver {
	public static void main(String[] args) {

		// sudoku board to be solved

		int[][] board = { { 0, 9, 0, 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 9, 6, 0, 4, 8, 5 },

				{ 0, 0, 0, 5, 8, 1, 0, 0, 0 }, { 0, 0, 4, 0, 0, 0, 0, 0, 0 }, { 5, 1, 7, 2, 0, 0, 9, 0, 0 },

				{ 6, 0, 2, 0, 0, 0, 3, 7, 0 }, { 1, 0, 0, 8, 0, 4, 0, 2, 0 }, { 7, 0, 6, 0, 0, 0, 8, 1, 0 },

				{ 3, 0, 0, 0, 9, 0, 0, 0, 0 } };

		// checking if board can be solved
		System.out.println(SudokuValidator.isValid(board));

	}

	public static int[][] solve(int[][] solution) {

		for (int i = 0; i < solution.length; i++) {

			for (int j = 0; j < 9; j++) {

				
				
			}

		}

		return solution;
	}

}
