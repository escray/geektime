public class L04Induction {
  public static void main(String[] args) {
    int grid = 63;
    long start, end = 0;
    long st, en = 0;

    start = System.currentTimeMillis();
    System.out.println(String.format("%d", (long)(Math.pow(2, grid)) - 1));
    end = System.currentTimeMillis();
    System.out.println(String.format("%d", (end - start)));

    st = System.currentTimeMillis();
    System.out.println(String.format("%d", getNumberOfWheat(grid)));
    en  = System.currentTimeMillis();
    System.out.println(String.format("%d", (end - start)));

    grid = 64;
    Result result = new Result();
    System.out.println(prove(grid, result));

    st = System.currentTimeMillis();
    System.out.println(String.format("%d", L03Iterative.getNumberOfWheat(grid)));
    en  = System.currentTimeMillis();
    System.out.println(String.format("%d", (end - start)));
  }

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

  public static boolean prove(int k, Result result) {
    // 证明 n = 1 时，命题是否成立
    if (k == 1) {
      if((Math.pow(2, 1) - 1) == 1) {
        result.wheatNum = 1;
        result.wheatTotalNum = 1;
        return true;
      } else {
        return false;
      }
    }
    // 如果 n = (k-1) 时命题成立，证明 n = k 时命题是否成立
    else {
      boolean proveOfPreviousOne = prove(k-1, result);
      result.wheatNum *= 2;
      result.wheatTotalNum += result.wheatNum;
      boolean proveOfCurrentOne = false;
      if (result.wheatTotalNum == (Math.pow(2, k) - 1)) {
        proveOfCurrentOne = true;
      }
      if (proveOfPreviousOne && proveOfCurrentOne) {
        return true;
      } else {
        return false;
      }
    }
  }
}

class Result {
    // 当前格的麦粒数
    public long wheatNum = 0;
    // 目前为止麦粒的总数
    public long wheatTotalNum = 0;
}
