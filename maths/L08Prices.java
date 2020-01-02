import java.util.Arrays;
public class L08Prices {
/**
 * 求组合数 C(m,n)，从 m 个数中取 n 个，有多少组合数
 *
 * @param m
 * @param n
 * @return
 */
 static int getCount(int m, int n) {
   if (n > m) {
     return 0;
   }
   int R = m - n;
   int result = 1;
   while ( m > R) {
     result *= m--;
   }
   while (n > 1) {
     result /= n--;
   }
   return result;
 }
 /**
  * 求组合情况
  * @param origin 原始数组
  * @param num 需要取出的个数
  * @param I 数组es开始取数位置
  * @return
  */
  static int[][] getCombination(int[] origin, int num, int l) {
    int[][] result = new int[getCount(origin.length - l, num)][];
    // 如果只需要取 1 个数
    if (num == 1) {
      for (int rsti = 0; rsti < result.length; rsti++, l++ ) {
        result[rsti] = new int[] { origin[l] };
      }
    } else {
      for (int rsti = 0; l < origin.length; l++) {
        int[][] srst = getCombination(origin, num-1, l+1);
        for (int[] sc : srst ) {
          int[] t = result[rsti] = new int[sc.length+1];
          t[0] = origin[l];
          System.arraycopy(sc, 0, t, 1, sc.length);
          rsti++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] c = getCombination(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3, 0);
    for (int[] cc : c) {
      System.out.println(Arrays.toString(cc));
    }
  }

}
