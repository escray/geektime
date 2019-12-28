import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class L05Iterative {

  public static long[] rewards = {1, 2, 5, 10};

  /**
  * @Description:    使用函数的递归（嵌套）调用，找出所有可能的奖赏组合
  * @param totalReward- 奖赏总金额，result- 保存当前的解
  * @return void
  */
  public static void get(long totalReward, ArrayList<Long> result) {
    // 当 totalReward = 0 时，证明它是满足条件的解，结束嵌套调用，输出解
    if (totalReward == 0) {
      System.out.println(result);
      return;
    }
    // 当 totalReward < 0 时，证明它不是满足条件的解，不输出
    else if (totalReward < 0) {
      return;
    }
    else {
      for (int i = 0; i < rewards.length; i++ ) {
        // 由于有 4 种情况，需要 clone 当前的解并传入被调用的函数
        ArrayList<Long> newResult = (ArrayList<Long>)(result.clone());
        // 记录当前的选择，解决一点问题
        newResult.add(rewards[i]);
        // 剩下的问题，留给嵌套调用去解决
        get(totalReward - rewards[i], newResult);
      }
    }
  }

  public static void factory(long num, ArrayList<Long> result) {
    if (num == getProduct(result)) {
      System.out.println(result);
      return;
    }
    else if ( num < getProduct(result)) {
      return;
    }
    else {
      for(long i = 1; i < num/2; i++){
        ArrayList<Long> newResult = (ArrayList<Long>)(result.clone());
        newResult.add(i);
        get((long)num/i, newResult);
      }

    }
  }

  public static long getProduct(ArrayList<Long> list) {
    if (list == null || list.size() == 0) {
      return 0;
    }
    long result = 1;
    for (int i = 0; i < list.size() ; i++ ) {
      result *= list.get(i);
    }
    return result;
  }

  public static void main(String[] args) {
    // int totalReward = 10;
    // get(totalReward, new ArrayList<Long>());
    // ArrayList<Long> al = new ArrayList<Long>();
    // System.out.println(getProduct(al));
    // long num = 2;
    // factory(num, new ArrayList<Long>());

    System.out.println(getProductFactors(8));
    ArrayList<Integer> result = new ArrayList<Integer>();
    getMultiply(8, result);
  }

  public static ArrayList<Integer> getProductFactors(int n) {
    if (n <= 0) {
      return new ArrayList<Integer>();
    }
    else {
      ArrayList<Integer> factors = new ArrayList<Integer>();
      for (int i = 1; i <= Math.ceil(Math.sqrt(n)) + 1 ; i++ ) {
        if ( n % i == 0 && !factors.contains(i)) {
          factors.add(i);
          factors.add((n/i));
        }
      }
      Collections.sort(factors);
      return factors;
    }
  }

  public static void getMultiply(int product, ArrayList<Integer> result) {
    ArrayList<Integer> factors = getProductFactors(product);

    if (factors.size() <= 1) {
      return;
    }

    for (int i = 0; i < factors.size() ; i++ ) {
      Integer item = factors.get(i);
      if (item == 1) {
        continue;
      }

      ArrayList<Integer> newResult = (ArrayList<Integer>)(result.clone());

      newResult.add(item);

      if (item == product) {
        printMultiply(newResult);
      }
      Integer divisor = (int)product / i;
      if (divisor != 1) {
        getMultiply(divisor, newResult);
      }
    }
  }

  public static void printMultiply(ArrayList<Integer> result_list) {
    for (int i = 0; i < result_list.size(); i++ ) {
      ArrayList<Integer> printList = (ArrayList<Integer>)(result_list.clone());
      // printList.insert(i, 1);
      System.out.println(printList);
    }
  }
}
