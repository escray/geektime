import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
public class BagQuestion {
    // 存储背包中物品总重量的最大值
    public static int maxW = Integer.MIN_VALUE;

    // 下标表示物品序号，值表示是否放进背包
    // 1：放
    // 0：不放
    private int[] currentAnswer;
    // 存储所有解(map key表示放进去的重量，value表示对应重量的物品放法)，
    // 最终所有最优解为bestAnswerMap.get(maxW)
    private Map<Integer, List<int[]>> bestAnswerMap = new HashMap();

    // cw 表示当前已经装进去的物品的重量和；
    // i 表示考察到哪个物品了；
    // w 背包重量；
    // items 表示每个物品的重量；
    // n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，
    // 那可以这样调用函数：f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (currentAnswer == null) {
            currentAnswer = new int[n];
        }
        // cw==w 表示装满了
        // i==n 表示已经考察完所有的物品
        // if 分支表明递归结束条件，并保证 maxW 跟踪所有选择中的最大值
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
                int[] bestAnswer = new int[currentAnswer.length];
                for (int j = 0; j < currentAnswer.length; j++) {
                    bestAnswer[j] = currentAnswer[j];
                }
                if (bestAnswerMap.containsKey(cw)) {
                    bestAnswerMap.get(cw).add(bestAnswer);
                } else {
                    List<int[]> list = new ArrayList<int[]>();
                    list.add(bestAnswer);
                    bestAnswerMap.put(cw, list);
                }
            }
            return;
        }
        currentAnswer[i] = 0;
        // 递归调用表示不选择当前物品
        // 直接考虑下一个（第 i+1 个），故 cw 不更新
        f(i + 1, cw, items, n, w);
        // 已经超过可以背包承受的重量的时候，就不要再装了
        if (cw + items[i] <= w) {
            currentAnswer[i] = 1;
            // 递归调用表示选择了当前物品
            // 故考虑下一个时，cw 通过入参更新为 cw + items[i]
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {20, 30, 10, 5, 15, 16, 24, 7, 21, 13};
        BagQuestion bq = new BagQuestion();
        bq.f(0, 0, a, 10, 100);
        System.out.println(bq.maxW);

        List<int[]> answer = bq.bestAnswerMap.get(maxW);
        for (int[] ints : answer) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
        }

    }
}
