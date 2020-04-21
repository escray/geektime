class Solution {
  public static int trap(int[] height) {
    int i = 0, j = height.length - 1, result = 0, bucket = 0;
    while( i <= j) {
      // 添加新木块：可能被淹没，可能成为新桶壁
      int block = Math.min(height[i], height[j]);
      // 尝试将木块升级为桶壁（更新桶的高度）
      bucket = block > bucket ? block : bucket;
      // 如果被淹没，去累加木块上水量：新木块上面水量 = 桶高 - 新木板高度
      // 注意，如果这里成功升级为桶壁：bucket == Math.min(height[i], height[j])
      result += height[i] < height[j] ? (bucket - height[i++]) : (bucket - height[j--]);
    }
    return result;
  }
  public static void main(String[] args) {
    int[] arr = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
    int result = trap(arr);
    System.out.println(result);
  }
}
