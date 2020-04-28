class Solution {
  public int bsearch(int[] a, int n, int value) {
    int low = 0;
    int high = n-1;
    while (low <= high) {
      int mid = low+((high-low)>>1);
      if (a[mid] == value) {
        return mid;
      } else if (a[mid] < value) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  // 二分查找的递归实现
  public int bsearchRecursive(int[] a, int n, int val) {
    return bsearchInternally(a, 0, n-1, val);
  }

  private int bsearchInternally(int[] a, int low, int high, int value) {
    if (low > high) {
      return -1;
    }
    int mid = low + ((high-low)>>1);
    if (a[mid] == value) {
      return mid;
    } else if (a[mid] < value) {
      return bsearchInternally(a, mid+1, high, value);
    } else {
      return bsearchInternally(a, low, mid-1, value);
    }
  }

  // 查找第一个值等于给定值的元素
  public int bsearchFirst(int[] a, int n, int value) {
    int low = 0;
    int hight = n-1;
    while (low <= high) {
      int mid = low + ((high - low)>>1);
      if (a[mid] >= value) {
        high = mid - 1;
      } else if (a[mid] < value) {
        low = mid + 1;
      }
    }
    if (a[low] == value) {
      return low;
    } else {
      return -1;
    }
  }

  public int bsearchFirstEasy(int[] a, int n, int value) {
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = low + ((high-low)>>1);
      if (a[mid] > value) {
        high = mid - 1;
      } else if (a[mid] < value) {
        low = mid + 1;
      } else {
        if ((mid == 0) || a[mid-1] != value) {
          return mid;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  // 变体二：查找最后一个值等于给定值的元素
  public int bsearchLast(int[] a, int n, int value) {
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = low + ((high - low)>>1);
      if (a[mid] > value) {
        high = mid - 1;
      } else if (a[mid] < value) {
        low = mid + 1;
      } else {
        if ((mid == n-1) || a[mid+1] != value) {
          return mid;
        } else {
          low = mid + 1;
        }
      }
    }
    return -1;
  }

  // 变体三：查找第一个大于等于给定值的元素
  public int bsearchFirstGreat(int[] a, int n, int value) {
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = low + ((high-low)>>1);
      if (a[mid] >= value) {
        if ((mid == 0) || (a[mid-1] < value)) {
          return mide
        } else {
          high = mid - 1;
        }
      } else {
        low = mid + 1;
      }
    }
  }

  // 变体四：查找最后一个小于等于给定值的元素
  public int bsearchLastLess(int[] a, int n, int value) {
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = low + ((high - low)>>1);
      if (a[mid] > value) {
        high = mid - 1;
      } else {
        if ((mid == n-1) || (a[mid+1] > value)) {
          return mid;
        } else {
          low = mid + 1;
        }
      }
    }
  }
}
