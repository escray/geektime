public class Triangle {
    static int[][] matrix = {{5}, {7, 8}, {2, 3, 4}, {4, 9, 6, 1}, {2, 7, 9, 4, 5}};

    public static int PascalTriangle(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    state[i][j] = state[i - 1][j] + matrix[i][j];
                } else if (j == matrix[i].length - 1) {
                    state[i][j] = state[i - 1][j - 1] + matrix[i][j];
                } else {
                    state[i][j] = Math.min(state[i-1][j-1], state[i - 1][j]) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            int distance = state[matrix.length - 1][i];
            if (distance < minDis) {
                minDis = distance;
            }
        }
        return minDis;
    }

    public static void main(String[] args) {
        System.out.println(PascalTriangle(matrix));
    }
}
