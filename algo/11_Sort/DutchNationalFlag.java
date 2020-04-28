public class DutchNationalFlag {
  private static void swap(int[] arr, int i, int j) {
    if (i == j) {
      return;
    }

    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void dutchNationalFlag(int[] arr) {
    int lo = 0;
    int mid = 0;
    int hi = arr.length - 1;

    while(mid <= hi) {
      if (arr[mid] == 0) {
        swap(arr, lo++, mid++);
      } else if (arr[mid] == 2) {
        swap(arr, mid, hi--);
      } else if (arr[mid] == 1) {
        mid++;
      }
    }
  }

  private static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 0, 0, 0, 1, 1 ,2, 2, 2, 0};
    dutchNationalFlag(arr);
    System.out.println("Dutch National Flag: ");
    print(arr);
  }


}
