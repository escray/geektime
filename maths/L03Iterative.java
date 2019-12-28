import java.util.Arrays;
public class L03Iterative {
  // public static void main(String[] args) {
  //   System.out.println(String.format(" 舍罕王给了这么多粒：%d", getNumberOfWheat(63)));
  //
  //   int number = 10;
  //   double squareRoot = getSquareRoot(number, 0.000001, 10000);
  //   if (squareRoot == -1.0) {
  //     System.out.println(" 请输入大于 1 的整数 ");
  //   } else if (squareRoot == -2.0) {
  //     System.out.println(" 未能找到解 ");
  //   } else {
  //     System.out.println(String.format("%d 的平方根是 %f", number, squareRoot));
  //   }
  //
  //   String[] dictionary = {"i", "am", "one", "of", "the", "authors", "in", "geekbang"};
  //   Arrays.sort(dictionary);
  //   String wordToFind = "i";
  //   boolean found = search(dictionary, wordToFind);
  //   if (found) {
  //     System.out.println(String.format(" 找到了单词 %s", wordToFind));
  //   } else {
  //     System.out.println(String.format(" 未能找到单词 %s", wordToFind));
  //   }
  // }

  public static long getNumberOfWheat(int grid) {
    long sum = 0;
    long numberOfWheatInGrid = 0;
    numberOfWheatInGrid = 1;
    sum += numberOfWheatInGrid;
    for (int i = 2; i <= grid; i++) {
      numberOfWheatInGrid *= 2;
      sum += numberOfWheatInGrid;
    }
    return sum;
  }

  public static double getSquareRoot(int n, double deltaThreshold, int maxTry) {
    if (n <=1 ) {
      return -1.0;
    }
    double min = 1.0, max = (double)n;
    for (int i = 0; i < maxTry; i++) {
      double middle = (min + max) / 2;
      double square = middle * middle;
      double delta = Math.abs((square / n) - 1);
      if (delta <= deltaThreshold) {
        return middle;
      } else {
        if (square > n) {
          max = middle;
        } else {
          min = middle;
        }
      }
    }

    return -2.0;
  }

  public static boolean search(String[] dictionary, String wordToFind) {
    if (dictionary == null) {
      return false;
    }
    if (dictionary.length == 0) {
      return false;
    }
    int left = 0, right = dictionary.length - 1;
    while (left <= right) {
      int middle = (left + right) / 2;
      if (dictionary[middle].equals(wordToFind)) {
        return true;
      } else {
        if (dictionary[middle].compareTo(wordToFind) > 0) {
          right = middle - 1;
        } else {
          left = middle + 1;
        }
      }
    }
    return false;
  }
}
