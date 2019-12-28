import java.util.ArrayList;
public class L05Test {
  public int count = 0;
  public void chosen(int num, ArrayList list) {
    if (null == list) {
      list = new ArrayList();
      list.add(1);
      list.add(num);
    }

    if (num == 1) {
      return;
    }

    for (int i = 1; i<= num; i++ ) {
      if (i == 1) {
        System.out.println(list);
        count++;
        continue;
      }
      if (i == num) {
        // 在 ArrayList 的尾部加 “1”
        list.add(1);
        // 去掉了 ArrayList 头部的 0
        list.remove(0);
        System.out.println(list);
        count++;
        continue;
      }
      if (num % i == 0) {
        int a = num/i;
        ArrayList list2 = new ArrayList(list);
        // 去掉原有队列的末尾元素
        list2.remove(list.size()-1);
        list2.add(i);
        list2.add(a);
        chosen(num/i, list2);
      }
    }
  }
  public static void main(String[] args) {
    L05Test iter = new L05Test();
    iter.chosen(8, null);
    System.out.println(iter.count);
  }
}
