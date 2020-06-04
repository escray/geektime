/**
 * 动态规划求解0-1背包问题
 * 问题描述
 * 给定n种物品和一个背包，物品i的重量是wi，价值vi，背包容量为C，问如何选择装入背包的物品，
 * 使装入背包中的物品的总价值最大？对于每种物品总能选择完全装入或不装入，一个物品最多装入一次。
 * 定义代价矩阵m与状态方程
 * m[i][j]
 *
 * i：物品编号，取值范围0~n-1，此处表示从i~n-1物品中进行选择
 * j：背包可用容量
 * m[i][j]：背包可用容量为j时，从i~n-1物品中进行选择，问题的最优代价为m[i][j]
 *
 * 状态方程
 * 从编号为n-1的物品开始考虑，直至初始编号为0
 * m[n-1][j] = 0,  0 ≤ j <w[n-1]
 * m[n-1][j] = v[n],   j ≥w[n-1]
 * m[i][j] =  m[i+1][j],         0≤ j< w[i] （物品i无法放入）
 * m[i][j] =  max{m[i+1][j],m[i+1][j-w[i]]+v[i]},      j ≥ w[i]
 * m[i][j] =  max{m[i+1][j],m[i+1][j-w[i]]+v[i]}：当前物品不放入，情况与m[i+1][j]情况相同，背包容量不变；确定当前物品放入背包，新容量=原容量-当前物品重量，当前价值+=当前物品价值
 *
 * 作者：穆童珏
 * 链接：https://juejin.im/post/5d4d50d85188256f672b954e
 */

public class Knapsack {
    /**
     * @param args 输出：第一行：最多价值；第二行：物品编号（从0开始）
     */
    public static void main(Stirng[] args) {
        int n = 5;
        int C = 10;
        int[] w = {2, 2, 6, 4, 5};
        int[] v = {6, 3, 5, 4, 6};

        int[][] m = new int[n][C + 1];

        //第n-1个物品
        for (int j = 0; j <= C; j++) {
            if (j < w[n - 1]) {
                m[n - 1][j] = 0;
            } else {
                m[n - 1][j] = v[n - 1];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= C; j++) {
                //放不下
                if (j < w[i]) {
                    m[i][j] = m[i + 1][j];
                }
                //可放下，在放与不放中选较大
                else {

                    m[i][j] = Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);
                }
            }
        }
        //最大价值
        System.out.println(m[0][C]);
        //放入物品输出：若i放入了则m[i][j]>m[i+1][j]
        int j = C;
        //检查0~n-2个物品
        for (i = 0; i < n - 1; i++) {
            if (m[i][j] > m[i + 1][j]) {
                System.out.print(i + " ");
                j = j-w[i];
            }
        }
        if (m[n - 1][j] != 0) {
            System.out.print(n-1);
        }
    }
}
