import java.util.PriorityQueue;

/**
 * 求中位数的问题
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/12/02
 */
public class MediumNumber {
    /** 求中位数的信息 */
    public static final MediumNumber instance = new MediumNumber();

    /**
     * 大顶堆，用来存储前半部分的数据，如果完整为100，那此存储的为0-50
     */
    private PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(
                    51,
                    (a, b) -> {
                        if (a < b) {
                            return 1;
                        } else if (a > b) return -1;
                        else return 0;
                    }
            );

    /**
     * 小顶堆,用来存储后半部分的数据，如果完整为100,那此存储的为51-100
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(51);

    /** 元素的个数 */
    private int count;

    /**
     * 插入数据
     *
     * @param num 当前动态的数据集
     */
    public void insertNumber(int num) {
        count++;

        // 如果堆为空，则插入大顶堆中
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }

        // 如果数据当前元素比大顶堆中的元素大，则插入小顶堆中
        // 如果元素的数据比大顶堆中的元素小，则插入大顶堆中
        if (maxHeap.peek() < num) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }

        int countValue = count / 2;
        // 如果大顶堆中的数据超过了中位数，则需要要移动,
        // 因为大顶堆中存储的数据为n/2+1个当n为奇数的情况下，
        // 所以每次取数，仅返回大顶堆中的数据即可
        if (maxHeap.size() > countValue) {
            move(maxHeap, minHeap, minHeap.size() - countValue);
            return;
        }
        // 如果小顶堆中的数据超过了中位数，也需要移动
        if (minHeap.size() > countValue) {
            move(minHeap, MaxHeap, minHeap.size() - countValue);
            return;
        }
    }

    /**
     * 返回中位数的数据
     *
     * @return
     */
    public int getMediumNumber() {
        return maxHeap.peek();
    }

    /**
     * 从一个堆向另一个堆中移动元素
     *
     * @param source
     * @param target
     */
    private void move(PriorityQueue<Integer> source, PriorityQueue<Integer> target, int number) {
        for (int i = 0; i < number; i++) {
            target.offer(source.poll());
        }
    }
}
