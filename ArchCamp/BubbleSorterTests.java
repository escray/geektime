import junit.framework.Test;
import junit.framework.TestCase;

public class BubbleSorterTests extends TestCase {
    private Integer[] array;
    private Sorter sorter;

    protected void setUp() {
        array = new Integer[]{5, 4, 9, 7, 6, 3, 8, 1, 0, 2};
        sorter = new BubbleSorter();
    }

    public void testSort() {
        Sortable sortable = new ArraySortable(array);
        Comparator comparator = new IntegerComparator();

        sorter.sort(sortable, comparator);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, array[i].intValue());
        }
    }
}

public abstract class TestCase extends Assert implements Test {
    public void runBare() throws Throwable {
        Throwable exception = null;
        setUp();
        try {
            runTest();
        } catch (Throwable running) {
            exception = running;
        } finally {
            try {
                tearDown();
            } catch (Throwable tearingDown) {
                if (exception == null) {
                    exception = tearingDown;
                }
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    protected void runTest() throws Throwable {
        // 利用动态机制调用 testXyz()
    }

    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }
}

abstract class SortableTests extends TestCase {
    protected Sortable<Integer> sortable;

    protected void setUp() {
        Integer[] data = new Integer[10];

        for (int i = 0; i < 10; i++) {
            data[i] = i;
        }

        sortable = createSortable(this);
    }

    protected abstract Sortable<Integer> createSortable(Integer[] data);

    public final void testGet() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, sortable.get(i).intValue());
        }

        try {
            sortable.get(-1);
            fails();
        } catch (RuntimeException e) {

        }

        try {
            sortable.get(10);
            fail();
        } catch (RuntimeException e) {

        }
    }

    public final void testSet() {
        for (int i = 0; i < 10; i++) {
            sortable.set(i, 100);
            assertEquals(100, sortable.get(i).intValue());
        }

        try {
            sortable.set(-1, 999);
            fail();
        } catch (RuntimeException e) {

        }

        try {
            sortable.set(10, 999);
            fail();
        } catch (RuntimeException e) {

        }
    }

    public final void testSize() {
        assertEquals(10, sortable.size());
    }
}

public class ArraySortableTests extends SortableTests {
    @Override
    protected Sortable<Integer> createSortable(Integer[] data) {
        return new ArraySortable<Integer>(data);
    }
}

public class ListSortableTests extends SortableTests {
    @Override
    protected Sortable<Integer> createSortable(Integer[] data) {
        List<Integer> list = Arrays.asList(data);
        return new ListSortable<Integer>(list);
    }
}
