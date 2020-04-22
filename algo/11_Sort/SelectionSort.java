class Solution {
  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
      int min = i;
      for (int j = i+1; j < n; j++) {
        if (arr[j] < arr[min]) {
          min = j;
        }
      }
      //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
      if (i != min) {
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
      }

    }
  }

  public static void print(int[] arr) {
    System.out.println("Print array:");
    for (int x : arr) {
      System.out.println(x + "\t");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    int[] arr = {3, 2, 6, 4, 5, 1, 9, 20, 13, 16};
    System.out.println("Selection Sort");
    selectionSort(arr);
    print(arr);
  }
}
