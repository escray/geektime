public class LongestCommonSubstringLength {
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        // 初始化第 0 行：a[0..0] 与 b[0..j] 的 maxlcs
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) {
                maxlcs[0][j] = 1;
            } else if (j != 0) {
                maxlcs[0][j] = maxlcs[0][j - 1];
            } else {
                maxlcs[0][j] = 0;
            }
        }
        // 初始化第 0 列：a[0..i] 与 b[0..0] 的 maxlcs
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                maxlcs[i][0] = 1;
            } else if (i != 0) {
                maxlcs[i][0] = maxcs[i - 1][0];
            } else {
                maxlcs[i][0] = 0;
            }
        }
        // 填表
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
                } else {
                    maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], max[i - 1][j - 1]);
                }
            }
        }
    }
}
