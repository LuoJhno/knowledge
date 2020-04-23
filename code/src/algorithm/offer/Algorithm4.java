package algorithm.offer;

public class Algorithm4 {

    public static boolean find(int[][] matrix, int target) {
        if (null == matrix || matrix.length <= 0) {
            return false;
        }
        int xIndex = matrix[0].length - 1;
        int yIndex = 0;
        while (xIndex >= 0 && yIndex < matrix.length) {
            if (matrix[xIndex][yIndex] == target) {
                return true;
            } else if (matrix[xIndex][yIndex] > target) {
                xIndex--;
            } else {
                yIndex++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(find(matrix, 31));
    }
}
