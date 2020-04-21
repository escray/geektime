import java.util.Stack;

class Solution {
  public static int trap(int[] height) {
    int sum = 0;
    Stack<Integer> stack = new Stack<>();
    int current = 0;
    while (current < height.length) {
      // 如果栈不空并且当前指向的高度大于栈顶高度就一直循环
      // 找到木桶，并计算水量
      while (!stack.empty() && height[current] > height[stack.peek()]) {
        // 栈顶元素的高度
        int bottomWater = height[stack.peek()];
        // 弹出栈顶元素
        stack.pop();
        if (stack.empty()) {
          break;
        }
        // stack.peek() 为左边比刚刚弹出元素大的最近一个数(k)
        // distance 为新增元素与 k 的中间距离
        int distance = current - stack.peek() - 1;
        int topWater = Math.min(height[stack.peek()], height[current]);

        // min - h 为新弹出层的高度
        sum += distance * (topWater - bottomWater);
      }
      stack.push(current);
      current++;
    }
    return sum;
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
