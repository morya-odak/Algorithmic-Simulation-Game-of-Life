/**
 * The model for John Conway's Game of Life. This class has all needed methods
 * as method stubs. The preceding comments for each are the specification for
 * the method explaining what each method does.
 *
 * @author Rick Mercer and Morya Odak
 */
public class GameOfLife {

	// Use this data structure to represent the existence of cells.
	// A true value means a cell exists at that location.
	private boolean[][] society;

	/*-
	 * - Write the constructor so it takes two integer arguments to represent the
	 * number of rows and columns in the game of life. The constructor creates a
	 * society with no cells but space to store rows*cols cells.
	 *
	 * @param rows The height of the grid that shows the cells.
	 * @param cols The width of the grid that shows the cells.
	 * 
	 *             Precondition rows and cols are in the range of 5 through 50
	 */
	public GameOfLife(int rows, int cols) {
		society = new boolean[rows][cols];
	}

	/**
	 * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfRows() {
		if (society.length == 0) {
			return 0;
		} else {
			return society.length;
		}
	}

	/**
	 * The number of columns, which can be indexed from 0..numberOfColumns()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfColumns() {
		if (society.length == 0) {
			return 0;
		} else {
			return society[0].length;
		}
	}

	/**
	 * Place a new cell in the society.
	 * 
	 * @param row The row to grow the cell.
	 * 
	 * @param col The column to grow the cell.
	 *
	 *            Precondition: row and col are in range of the 2D array.
	 */
	public void growCellAt(int row, int col) {
		society[row][col] = true;

	}

	/*
	 * Return true if there is a cell at the given row and column. Return false if
	 * there is no cell at the specified location.
	 *
	 * @param row The row to check.
	 * 
	 * @param col The column to check.
	 * 
	 * @return True if there is a cell at the given row or false if none
	 *
	 * Precondition: row and col are in range.
	 */
	public boolean cellAt(int row, int col) {
		if (society[row][col] == true) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Note this will not be tested for a grade. But you might find this useful
	 * while inplementing GameOfLife.
	 * 
	 * Return one big string of cells to represent the current state of the society
	 * of cells (see output below where '.' represents an empty space and 'O' is a
	 * live cell. There is no need to test toString. Simply use it to visually
	 * inspect if needed. Here is one sample output from toString:
	 *
	 * GameOfLife society = new GameOfLife(4, 14); society.growCellAt(1, 2);
	 * society.growCellAt(2, 3); society.growCellAt(3, 4);
	 * System.out.println(society.toString());
	 *
	 * @return A textual representation of this society of cells.
	 *
	 * Sample Output: .............. ..O........... ...O.......... ....O.........
	 */
	@Override
	public String toString() {
		String finalString = "";
		for (int i = 0; i < society.length; i++) {
			for (int j = 0; j < society[i].length; j++) {
				if (society[i][j] == true) {
					finalString += "0";
				} else {
					finalString += ".";
				}
			}
			finalString += "\n";
		}
		return finalString;
	}

	/*-
	 * The return values should always be in the range of 0 through 8.
	 *
	 * @return The number of neighbors around any cell using wrap around.
	 * 
	 *
	 * Count the neighbors around the given location. Use wraparound. A cell in row
	 * 0 has neighbors in the last row if a cell is in the same column, or the
	 * column to the left or right. In this example, cell 0,5 has two neighbors in
	 * the last row, cell 2,8 has four neighbors, cell 2,0 has four neighbors, cell
	 * 1,0 has three neighbors. The cell at 3,8 has 3 neighbors. 
	 *
	 * .....O..O
	 * O........
	 * O.......O
	 * O.......O
	 * ....O.O..
	 *
	 * Precondition: row and col are in range of the 2D array
	 *   
	 * @param row
	 * @param col
	 * @return how many neighbors are around the given location, alsways 0..8
	 */
	public int neighborCount(int row, int col) {
		int count = 0;
		for (int i = row - 1; i < row + 2; i++) {
			int indexRow;
			if (i == -1) {
				indexRow = society.length - 1;
			} else if (i == society.length) {
				indexRow = 0;
			} else {
				indexRow = i;
			}
			for (int j = col - 1; j < col + 2; j++) {
				int indexCol;
				if (j == -1) {
					indexCol = society[0].length - 1;
				} else if (j == society[0].length) {
					indexCol = 0;
				} else {
					indexCol = j;
				}
				if (society[indexRow][indexCol] == true && (indexRow != row || indexCol != col)) {

					count++;
				}
			}
		}
		return count;
	}

	/*
	 * Update the state to represent the next society. Typically, some cells will
	 * die off while others are born.
	 */
	public void update() {
		boolean[][] newSociety = new boolean[society.length][society[0].length];

		for (int row = 0; row < society.length; row++) {
			for (int col = 0; col < society[0].length; col++) {
				int count = 0;
				for (int i = row - 1; i < row + 2; i++) {
					int indexRow;
					if (i == -1) {
						indexRow = society.length - 1;
					} else if (i == society.length) {
						indexRow = 0;
					} else {
						indexRow = i;
					}
					for (int j = col - 1; j < col + 2; j++) {
						int indexCol;
						if (j == -1) {
							indexCol = society[0].length - 1;
						} else if (j == society[0].length) {
							indexCol = 0;
						} else {
							indexCol = j;
						}
						if (society[indexRow][indexCol] == true && (indexRow != row || indexCol != col)) {

							count++;
						}
						if (count == 3) {
							newSociety[row][col] = true;
						} else if (count < 2) {
							newSociety[row][col] = false;
						} else if (count > 3) {
							newSociety[row][col] = false;
						} else {
							newSociety[row][col] = society[row][col];
						}
					}
				}

			}
		}
		society = newSociety;
	}

}
