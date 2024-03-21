import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuSolver extends JFrame implements ActionListener {
    private JTextField[][] grid;
    private JButton solveButton;

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(9, 9));
        grid = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField();
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuPanel.add(grid[i][j]);
            }
        }
        add(sudokuPanel, BorderLayout.CENTER);

        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        add(solveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            int[][] puzzle = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String value = grid[i][j].getText();
                    puzzle[i][j] = value.isEmpty() ? 0 : Integer.parseInt(value);
                }
            }
            if (solveSudoku(puzzle)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        grid[i][j].setText(Integer.toString(puzzle[i][j]));
                    }
                }
                JOptionPane.showMessageDialog(this, "Sudoku Solved!");
            } else {
                JOptionPane.showMessageDialog(this, "No solution found.");
            }
        }
    }

    private boolean solveSudoku(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(puzzle, row, col, num)) {
                            puzzle[row][col] = num;
                            if (solveSudoku(puzzle)) {
                                return true;
                            }
                            puzzle[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] puzzle, int row, int col, int num) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == num) {
                return false;
            }
        }
        // Check column
        for (int i = 0; i < 9; i++) {
            if (puzzle[i][col] == num) {
                return false;
            }
        }
        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (puzzle[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new SudokuSolver();
    }
}
