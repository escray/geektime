public class MinimumDistance {
    // 全局变量或者成员变量
    private int minDist = Integer.MAX_VALUE;

    // 回溯算法
    // 调用方式：minDistBacktracing(0, 0, 0, w, n);
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了 n-1, n-1 这个位置了，这里看着有点奇怪哈，你自己举个例子看下
        if (i == n-1 && j == n-1) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        // 往下走，更新 i=i+1, j=j
        if (i < n-1) {
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        // 往右走，更新 i=i, j=j+1
        if (j < n-1) {
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        // 初始化 states 的第一行数据
        for (int j = 0; j < n; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        // 初始化 states 的第一列数据
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; j++) {
                states[i][j] = matrix[i][j]+Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }

    private int[][] matrix = {
            {1, 3, 5, 9},
            {2, 1, 3, 4},
            {5, 2, 6, 7},
            {6, 8, 4, 3}
    };
    private int n = 4;
    private int[][] mem = new int[4][4];

    // 调用 minDist(n-1, n-1);
    public int minDist(int i, int j) {
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

}
