package algorithm.offer;

public class Algorithm12 {

    private int[][] steps = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    private int rows;
    private int cols;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (cols == 0 || rows == 0) {
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        boolean[][] isUsed = new boolean[rows][cols];
        char[][] newMatrix = this.buildMatrix(matrix, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.backtracking(newMatrix, str, isUsed, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean backtracking(char[][] matrix, char[] str, boolean[][] isUsed, int length, int row, int col) {
        if (length == str.length) {
            return true;
        }
        if (row < 0 || row > rows || col < 0 || col > cols || isUsed[row][col] || matrix[row][col] != str[length]) {
            return false;
        }
        isUsed[row][col] = true;
        for (int[] n : steps) {
            if (backtracking(matrix, str, isUsed, length + 1, row + n[0], col + n[1])) {
                return true;
            }
        }
        isUsed[row][col] = false;
        return false;
    }

    private char[][] buildMatrix(char[] matrix, int rows, int cols) {
        char[][] newMatrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrix[i][j] = matrix[index++];
            }
        }
        return newMatrix;
    }

    public static void main(String[] arg) {
        char[] arr = { 'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h' };
        Algorithm12 algorithm12 = new Algorithm12();
        char[] taget = { 'b', 'f', 'c', 'e' };
        System.out.println((algorithm12.hasPath(arr, 3, 4, taget)));
    }
}