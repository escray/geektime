public class RemoveKDigits {
    /**
     * 删除整数的 k 个数字，获得删除后的最小值
     * @param num 原整数
     * @param k 删除数量
     * @return 删除后的得到的数字
     */
    public static String removeKDigits(String num, int k) {
        // 新整数的最终长度 = 原整数长度 - k
        int newLength = num.length() - k;
        // 创建一个栈，用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); ++i) {
            // 遍历当前数字
            char c = num.charAt(i);
            // 当栈顶数字大于遍历到的当前数字
            // 栈顶数字出栈（相当于删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            // 遍历到的当前数字入栈
            stack[top++] = c;
        }
        // 找到栈中第 1 个非领数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("30200", 1));
        System.out.println(removeKDigits("10", 2));
        System.out.println(removeKDigits("541270936", 3));
    }
}
