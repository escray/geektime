public class L40Bag {
  // 回溯算法实现。注意：我把输入的变量都定义成了成员变量
  // backtracking
  // 结果放到 maxW 中
  private int maxW = Integer.MIN_VALUE;
  // 物品重量
  private int[] weight = {2,2,4,6,3};
  // 物品个数
  private int n = 5;
  // 背包承受的最大重量
  private int w = 9;
  // 调用 f(0, 0)
  public void fWithBacktracking(int i, int cw) {
    // cw==w 表示装满了，i==n 表示物品都考察完了
    if (cw == w || i == n) {
      if (cw > maxW) {
        maxW = cw;
      }
      return;
    }
    // 选择不装第 i 个物品
    fWithBacktracking(i+1, cw);
    if (cw + weight[i] <= w) {
      // 选择装第 i 个物品
      fWithBacktracking(i+1, cw+weight[i]);
    }
  }

  // 备忘录，默认值 false
  private boolean[][] mem = new boolean[5][10];
  public void fWithMem(int i, int cw) {
    // cw==w 表示装满了，i==n 表示物品都考察完了
    if (cw == w || i == n) {
      if (cw > maxW) {
        maxW = cw;
      }
      return;
    }
    // 重复状态
    if (mem[i][cw]) {
      return;
    }
    // 记录 (i, cw) 这个状态
    mem[i][cw] = true;
    // 选择不装第 i 个物品
    fWithMem(i+1, cw);
    if (cw+weight[i] <= w) {
      // 选择装第 i 个物品
      fWithMem(i+1, cw+weight[i]);
    }
  }

  // 0-1 背包问题升级版
  // 结果放到 maxV 中
  private int maxV = Integer.MIN_VALUE;
  // 物品的价值
  private int[] value = {3, 4, 8, 9, 6};
  // 调用 f(0, 0, 0)
  public void fWithValue(int i, int cw, int cv) {
    // cw==w 表示装满了，i==n 表示物品都考察完了
    if (cw == w || i == n) {
      if (cv > maxV) {
        maxV = cv;
      }
      return;
    }
    // 选择不装第 i 个物品
    fWithValue(i+1, cw, cv);
    if (cw+weight[i] <= w) {
      // 选择装第 i 个物品
      fWithValue(i+1, cw+weight[i], cv+value[i]);
    }
  }

  //weight: 物品重量，n: 物品个数，w: 背包可承载重量
  public int knapsack(int[] weight, int n, int w) {
    // 默认值 false
    boolean[][] states = new boolean[n][w+1];
    // 第一行的数据要特殊处理，可以利用哨兵优化
    states[0][0] = true;
    if (weight[0] <= w) {
      states[0][weight[0]] = true;
    }
    // 动态规划状态转移
    for (int i = 1; i < n; ++i) {
      // 不把第 i 个物品放入背包
      for (int j = 0; j <= w; ++j) {
        if (states[i-1][j] == true) {
          states[i][j] = states[i-1][j];
        }
      }
      // 把第 i 个物品放入背包
      for (int j = 0; j <= w-weight[i]; ++j) {
        if (states[i-1][j] == true) {
          states[i][j+weight[i]] = true;
        }
      }
    }
    // 输出结果
    for (int i = w; i >= 0; --i) {
      if (states[n-1][i] == true) {
        return i;
      }
    }
    return 0;
  }

  public static int knapsack2(int[] items, int n, int w) {
    // 默认值 false
    boolean[] states = new boolean[w+1];
    // 第一行的数据要特殊处理，可以利用哨兵优化
    states[0] = true;
    if (items[0] <= w) {
      states[items[0]] = true;
    }
    // 动态规划
    for (int i = 1; i < n; ++i) {
      // 把第 i 个物品放入背包
      for (int j = w-items[i]; j >= 0; --j) {
        if (states[j] == true) {
          states[j+items[i]] = true;
        }
      }
    }
    // 输出结果
    for (int i = w; i >=0; --i) {
      if (states[i] == true) {
        return i;
      }
    }
    return 0;
  }




  public static void main(String[] args) {
    L40Bag bag = new L40Bag();
    bag.fWithBacktracking(0,0);
    System.out.println(bag.maxW);
    bag.maxW = 0;
    bag.fWithMem(0,0);
    System.out.println(bag.maxW);
    System.out.println(bag.knapsack(bag.weight, 5, 9));
    System.out.println(bag.knapsack2(bag.weight, 5, 9));
    bag.maxW = 0;
    // bag.f(0, 0, 0);
    bag.fWithValue(0, 0, 0);
    System.out.println(bag.maxV);
  }
}
