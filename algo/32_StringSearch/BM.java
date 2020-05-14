public class BM {
    // 全局变量或成员变量
    private static final int SIZE = 256;

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            // 初始化 bc
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            // 计算 b[i] 的 ASCII 值
            int ascii = (int) b[i];
            bc[ascii] = i;
        }
    }

    // a,b 表示主串和模式串；
    // n，m 表示主串和模式串的长度。
    public  int bm(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建坏字符哈希表
        generateBC(b, m, bc);

        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];

        generateGS(b, m, suffix, prefix);

        // i 表示主串与模式串对齐的第一个字符
        int i = 0;
        while (i <= n - m) {
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 坏字符对应模式串中的下标是 j
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            if (j < 0) {
                return i;
            }

            // 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
            // i = i + (j - bc[(int) a[i + j]]);
            int x = j - bc[(int) a[i + j]];
            int y = 0;
            // 如果有好后缀的话
            if (j < m - 1) {
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j 表示坏字符对应的模式串中的字符下标 ;
    // m 表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1 - j;
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= m - 1; ++r) {
            if (prefix[m - r] == true) {
                return r;
            }
        }
        return m;
    }

    // b 表示模式串，m 表示长度，suffix，prefix 数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        // b[0, i]
        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                // 与 b[0, m-1] 求公共后缀子串
                --j;
                ++k;
                //j+1 表示公共后缀子串在 b[0, i] 中的起始下标
                suffix[k] = j + 1;
            }
            // 如果公共后缀子串也是模式串的前缀子串
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();

        int n = text.length;
        int m = pattern.length;
        int result = new BM().bm(text, n, pattern, m);

        System.out.println(result);

        result = result < 0 ? n : result;

        // print results
        System.out.println("text:    " + txt);
        System.out.print("pattern: ");
        for (int i = 0; i < result; i++)
            System.out.print(" ");
        System.out.println(pat);
    }
}
