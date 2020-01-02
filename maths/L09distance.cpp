#include <iostream>
using namespace std;

void levenshteinDis(const char* str1, const char* str2, int m, int n,
                    int i, int j, int edist, int &mind) {
  if (i == m || j == n) {
    if (i < m) edist += (m - i);
    if (j < n) edist += (n - j);
    if (edist < mind) mind = edist;
    return;
  }

  if (str1[i] == str2[j]) {
    levenshteinDis(str1, str2, m, n, i+1, j+1, edist, mind);
  } else {
    // 删除或增加
    levenshteinDis(str1, str2, m, n, i+1, j, edist+1, mind);
    levenshteinDis(str1, str2, m, n, i, j+1, edist+1, mind);
    // 替换操作
    levenshteinDis(str1, str2, m, n, i+1, j+1, edist+1, mind);
  }
}

int levenshteinDis(const char* a, const char* b, int m, int n) {
  int mind = 0xfffffff;
  levenshteinDis(a, b, m, n, 0, 0, 0, mind);
  return mind;
}

/*
 * 状态转移方程
 * 1.当a[i] != b[j], min_edist(i,j) = min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1, j-1)+1)
 * 2.当a[i] == b[j], min_edist(i,j) = min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1, j-1))
 */
 int levenshteinDisDP(const char* a, const char* b, int m, int n) {
   // 初始化 dp 数组
   int dp[m][n];
   for (int i = 0; i < m; ++i) {
     for (int j = 0; j < n; ++j) {
      dp[i][j] = 0;
     }
   }
   // 初始化第 0 列
   for (int i = 0; i < m; ++i) {
     if (a[i] == b[0]) dp[i][0] = i;
     else if (i != 0) dp[i][0] = dp[i-1][0] + 1;
     else dp[i][0] = 1;
   }
   // 初始化第 0 行
   for (int i = 0; i < n; ++i) {
     if (a[0] == b[i]) dp[0][i] = i;
     else if (i > 0) dp[0][i] = dp[0][i-1] + 1;
     else dp[0][i] = 1;
   }
   // 填表余下部分
   for (int i = 1; i < m; ++i) {
     for (int j = 1; j < n; ++j) {
       dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, a
                      [i] == b[j] ? dp[i-1][j-1]:dp[i-1][j-1]+1);
     }
   }

   return dp[m-1][n-1];
 }

int main(int argc, char *argv [])
{
  // cout << "hello, world." << endl;
  return 0;
}
