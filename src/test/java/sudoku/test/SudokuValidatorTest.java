package sudoku.test;

import org.junit.Assert;
import org.junit.Test;

import sudoku.SudokuValidator;

public class SudokuValidatorTest {
	@Test
	public void testInvalidColumn() {
		int[][] solution = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
				{ 29, 9, 8, 3, 4, 2, 5, 6, 7 },

				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },

				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		new SudokuValidator();
		Assert.assertEquals(false, SudokuValidator.isValid(solution));
	}
	
	@Test
	public void testInvalidSubSquare() {
		int[][] solution = { { 8, 3, 5, 4, 1, 6, 9, 2, 7 }, { 2, 9, 6, 8, 5, 7, 4, 3, 1 },
				{ 4, 1, 7, 2, 9, 3, 6, 5, 8 },

				{ 5, 6, 9, 1, 3, 4, 2, 8, 2 }, { 1, 2, 3, 6, 7, 8, 5, 4, 9 }, { 7, 4, 8, 4, 2, 9, 1, 6, 3 },

				{ 6, 5, 2, 7, 8, 1, 3, 9, 4 }, { 9, 8, 1, 3, 4, 5, 2, 7, 6 }, { 3, 7, 4, 9, 6, 2, 8, 1, 5 } };

		new SudokuValidator();
		Assert.assertEquals(false, SudokuValidator.isValid(solution));
	}

	@Test
	public void testInvalidBoardSize() {
		int[][] solution = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8, 6 },
				{ 29, 9, 8, 3, 4, 2, 5, 6, 7 },

				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },

				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		new SudokuValidator();
		Assert.assertEquals(false, SudokuValidator.isValid(solution));
	}

	@Test
	public void testIncompleteBoard() {
		int[][] solution = { { 0, 9, 0, 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 9, 6, 0, 4, 8, 5 },

				{ 0, 0, 0, 5, 8, 1, 0, 0, 0 }, { 0, 0, 4, 0, 0, 0, 0, 0, 0 }, { 5, 1, 7, 2, 0, 0, 9, 0, 0 },

				{ 6, 0, 2, 0, 0, 0, 3, 7, 0 }, { 1, 0, 0, 8, 0, 4, 0, 2, 0 }, { 7, 0, 6, 0, 0, 0, 8, 1, 0 },

				{ 3, 0, 0, 0, 9, 0, 0, 0, 0 } };

		new SudokuValidator();
		Assert.assertEquals(true, SudokuValidator.isValid(solution));
	}

	@Test
	public void testValidSolution() {
		int[][] solution = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
				{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },

				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },

				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		new SudokuValidator();
		Assert.assertEquals(true, SudokuValidator.isValid(solution));
	}
}
