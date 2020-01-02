public class L10StrDistance {
  /**
 * @Description:    使用状态转移方程，计算两个字符串之间的编辑距离
 * @param a- 第一个字符串，b- 第二个字符串
 * @return int- 两者之间的编辑距离
 */
 public static int getStrDistance(String a, String b) {
   if (a == null || b == null) {
     return -1;
   }

   // 初始用于记录化状态转移的二维表
   int[][] d = new int[a.length()+1][b.length()+1];
   // 如果 i 为 0，且 j 大于等于 0，那么 d[i, j] 为 j
   for (int j = 0; j <= b.length(); j++) {
     d[0][j] = j;
   }

   // 如果 i 大于等于 0，且 j 为 0，那么 d[i, j] 为 i
   for (int i = 0; i < a.length(); i++ ) {
     d[i][0] = i;
   }

   // 实现状态转移方程
   // 请注意由于 Java 语言实现的关系，代码里的状态转移是从 d[i, j] 到 d[i+1, j+1]，
   // 而不是从 d[i-1, j-1] 到 d[i, j]。本质上是一样的。
   for (int i = 0; i < a.length(); i++) {
     for (int j = 0; j < b.length(); j++) {
       int r = 0;
       if (a.charAt(i) != b.charAt(j)) {
         r = 1;
       }
       int first_append = d[i][j+1] + 1;
       int second_append = d[i+1][j] + 1;
       int replace = d[i][j] + r;
       int min = Math.min(first_append, second_append);
       min = Math.min(min, replace);
       d[i+1][j+1] = min;
     }
   }
   return d[a.length()][b.length()];
 }

 public static void main(String[] args) {
   System.out.println(L10StrDistance.getStrDistance("mouse", "mouussee"));
 }
}
