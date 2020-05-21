package algorithm.offer;

import java.util.ArrayList;
import java.util.List;

import algorithm.structure.TreeNode;

public class Algorithm29 {
    public static List<Integer> printMatrix(int[][] matrix) {
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>((rowEnd + 1) * (colEnd + 1));
        while (rowStart <= rowEnd && colStart < colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }
            for (int j = rowStart; j <= rowEnd; j++) {
                result.add(matrix[j][colEnd]);
            }
            if (rowStart != rowEnd) {
                for (int i = colEnd - 1; i >= colStart; i--) {
                    result.add(matrix[rowEnd][i]);
                }
            }
            if (colStart != colEnd) {
                for (int j = rowEnd - 1; j >= rowStart; j--) {
                    result.add(matrix[j][colStart]);
                }
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return result;
    }

}