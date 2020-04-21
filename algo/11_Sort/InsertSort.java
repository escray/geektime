class Solution {
  // 插入排序，a 表示数组，n 表示数组大小
  public void insertSort(int[] a, int n) {
    if (n<=1) {
      return;
    }

    for (int i = 1; i < n; ++i) {
      int value = a[i];
      int j = i - 1;
      // 查找插入的位置
      for (; j >= 0; --j) {
        if (a[j]>value) {
          // 数据移动
          a[j+1] = a[j];
        }
        else {
          break;
        }
      }
      // 插入数据
      a[j+1] = value;
    }
  }
}
