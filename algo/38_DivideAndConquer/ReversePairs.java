public class ReversePairs {
    // 全局变量或者成员变量
    private int num = 0;

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p+r)/2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                // 统计p-q之间，比a[j]大的元素个数
                num += (q - i + 1);
                temp[k++] = a[j++];
            }
        }
        // 处理剩下的
        while (i <= q) {
            temp[k++] = a[i++];
        }
        // 处理剩下的
        while (j <= r) {
            temp[k++] = a[j++];
        }
        // 从tmp拷贝回a
        for (i = 0; i <= r - p; ++i) {
            a[p+i] = temp[i];
        }
    }
}
