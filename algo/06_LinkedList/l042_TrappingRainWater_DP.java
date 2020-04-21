class Solution {
  public static int trap(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int n = height.length, ans = 0;
    int[] leftMax = new int[n], rightMax = new int[n];
    leftMax[0] = height[0];
    rightMax[n-1] = height[n-1];

    // 第一次循环，从右向左计算
    for (int i = n-2; i>=0; i--) {
      // 右侧桶壁高度
      rightMax[i] = Math.max(rightMax[i+1], height[i]);
    }
    // 第二次循环，从左往右计算
    for (int i = 1; i<n ; i++) {
      // 左侧桶壁高度
      leftMax[i] = Math.max(leftMax[i-1], height[i]);

      int bucket = Math.min(leftMax[i], rightMax[i]);
      // 当前位置水量 = 木桶高度 - 桶座高度
      ans += bucket - height[i];
    }
    return ans;
  }
  public static void main(String[] args) {
    int[] arr = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
    int result = trap(arr);
    System.out.println(result);
    arr = new int[] { 6,5,2,1,3,5};
    result = trap(arr);
    System.out.println(result);
  }
}
