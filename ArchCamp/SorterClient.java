public class SorterClient {
    public static void main(String[] args) {
        Integer[] array = {5, 4, 9, 7, 6, 3, 8, 1, 0, 2};
        Sorter<Integer> sorter = SorterFactory.getSorter();
        Sortable<Integer> sortable = SortableFactory.getSortable(array);
        Comparator<Integer> comparator = ComparatorFactory.getComparator();

        sorter.sort(sortable, comparator);

        Sorter<Integer> sorter_2 = SorterFactory_2.getSorter("demo.sort.impl.BubbleSorter");
    }
}

class SorterFactory {
    public static <T> Sorter<T> getSorter() {
        return new BubbleSorter<T>();
    }
}

class SorterFactory_2 {
    @SuppressWarning("unchecked")
    public static <T> Sorter<T> getSorter(String impClass) {
        try {
            Class impl = Class.forName(implClass);
            return (Sorter<T>) impl.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal class name: " + implClass, e);
        }
    }
}

class SorterFactory_3 {
    private final static Properties IMPLS = loadImpls();

    private static Properties loadImpls() {
        Properties defaultImpls = new Properties();
        Properties impls = new Properties(defaultImpls);

        defaultImpls.setProperty("sorter", "demo.sort.impl.BubbleSorter");

        try {
            impls.load(SorterFactory_3.class.getResourceAsStream("sort.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return impls;
    }

    @SuppressWarnings("unchecked")
    public static <T> Sorter<T> getSorter() {
        String implClassName = IMPLS.getProperty("sorter");
        try {
            Class implClass = class.forName(implClassName);
            return (Sorter<T>) implClass.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal class name: " + implClassName, e);
        }
    }
}
