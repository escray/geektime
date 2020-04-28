
default void sort(Comparator<? super E> c) {
  Object[] a = this.toArray();
  Arrays.sort(a, (Comparator) c);
  ListIterator<E> i = this.listIterator();
  for (Object e : a) {
    i.next();
    i.set((E) e);
  }
}


public static void sort(int[] a) {
  DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
 }

public static void sort(long[] a) {
  DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
}

public static void sort(Object[] a) {
  if (LegacyMergeSort.userRequested)
    legacyMergeSort(a);
  else
    ComparableTimSort.sort(a, 0, a.length, null, 0, 0);
}
