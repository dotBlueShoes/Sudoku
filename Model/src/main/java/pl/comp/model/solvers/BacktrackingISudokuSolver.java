package pl.comp.model.solvers;


import java.util.Random;
import pl.comp.model.SudokuBoard;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public class BacktrackingISudokuSolver implements ISudokuSolver {

    public BacktrackingISudokuSolver() {

    }

    @Override
    public void solve(SudokuBoard sudokuBoard) {

        Random random = new Random();

        // As simple as it gets we don't need it as a list.
        int[] startNumbers = new int[SudokuBoard.NUMBER_OF_CELLS];
        int rowNumber;
        int columnNumber;
        boolean isOK;

        for (int cell = 0; cell < SudokuBoard.NUMBER_OF_CELLS; cell++) {

            rowNumber = cell / SudokuBoard.SIZE;
            columnNumber = cell % SudokuBoard.SIZE;
            isOK = false;

            if (startNumbers[cell] == 0) {
                // If (step forward) then we set new random (1-9) value.
                startNumbers[cell] = random.nextInt(9) + 1;
                sudokuBoard.set(rowNumber, columnNumber, startNumbers[cell]);
                do {
                    if (sudokuBoard.isValid(cell)) {
                        isOK = true;
                        break;
                    }
                    sudokuBoard.set(
                        rowNumber, columnNumber,
                        sudokuBoard.get(rowNumber, columnNumber) % 9 + 1
                    );
                } while (sudokuBoard.get(rowNumber, columnNumber) != startNumbers[cell]);
            } else {
                // If step backwards then we use previous value in array.
                sudokuBoard.set(
                    rowNumber, columnNumber,
                    sudokuBoard.get(rowNumber, columnNumber) % 9 + 1
                );
                while (sudokuBoard.get(rowNumber, columnNumber) != startNumbers[cell]) {
                    if (sudokuBoard.isValid(cell)) {
                        isOK = true;
                        break;
                    }
                    sudokuBoard.set(
                        rowNumber, columnNumber,
                        sudokuBoard.get(rowNumber, columnNumber) % 9 + 1
                    );
                }
            }

            // If non-fit then we're getting back 2 steps in cell array
            if (!isOK) {
                startNumbers[cell] = 0;
                sudokuBoard.set(rowNumber, columnNumber, 0);
                cell -= 2;
            }
        }
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public BacktrackingISudokuSolver clone() throws CloneNotSupportedException {
        try {
            return (BacktrackingISudokuSolver) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }

}
