public class SudokuSolver {

    public static boolean isSafe(int sudoku[][], int row, int col, int dig) {

        // for column
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == dig) {
                return false;
            }
        }

        // for row
        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == dig) {
                return false;
            }
        }

        // for gride
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; sr++) {
            for (int j = sc; j < sc + 3; sc++) {
                if (sudoku[i][j] == dig) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printSudoku(int sudoku[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean sudokuSolver(int sudoku[][], int row, int col) {

        // base case
        if (row == 9) {
            return true;
        }

        int nextRow = row;
        int nextCol = col + 1;

        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }

        if (sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextRow, nextCol);
        }

        for (int dig = 1; dig <= 9; dig++) {

            if (isSafe(sudoku, row, col, dig)) {
                sudoku[row][col] = dig;
                if (sudokuSolver(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String args[]) {

        int sudoku[][] = { { 0, 4, 0, 1, 0, 2, 6, 5, 7 },
                { 2, 7, 3, 6, 8, 5, 4, 1, 9 },
                { 0, 6, 0, 9, 0, 4, 2, 8, 3 },
                { 0, 9, 0, 3, 2, 8, 7, 0, 5 },
                { 0, 5, 7, 4, 0, 9, 0, 6, 2 },
                { 4, 2, 8, 5, 6, 7, 3, 9, 1 },
                { 0, 3, 2, 0, 0, 1, 0, 7, 4 },
                { 7, 1, 4, 2, 0, 6, 9, 3, 8 },
                { 0, 8, 0, 7, 4, 0, 1, 2, 6 } };

        long startTime = System.nanoTime();

        if (sudokuSolver(sudoku, 0, 0)) {
            System.out.println("Generating solution: ");
            printSudoku(sudoku);
        } else {
            System.out.println("No solution");
        }

        long endTime = System.nanoTime();

        System.out.println("Execution time: " + (endTime - startTime) / 1000 + " microseconds");
    }

}
