import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

public class ParaUnitTest {

}

abstract class class ComparatorTests<T> extends TestCase {
    protected T o1;
    protected T o2;
    protected boolean ascending;
    protected boolean isBefore;

    public ComparatorTests(T o1, T o2, boolean ascending, boolean isBefore) {
        super("testIsBefore");

        this.o1 = o1;
        this.o2 = o2;
        this.ascending = ascending;
        this.isBefore = isBefore;
    }

    public void testIsBefore() {
        assertEquals(isBefore, createComparator(ascending).isBefore(o1, o2));
    }

    protected abstract comparator<T> createComparator(boolean ascending);
}

class IntegerComparatorTests extends ComparatorTests<Integer> {
    public static Test suite() {
        TestSuite suite = new TestSuite("IntegerComparatorTests");

        suite.addTest(new IntegerComparatorTests(1, 1, true, false));
        suite.addTest(new IntegerComparatorTests(1, 2, true, true));
        suite.addTest(new IntegerComparatorTests(2, 1, true, false));

        suite.addTest(new IntegerComparatorTests(1, 2, false, false));
        suite.addTest(new IntegerComparatorTests(1, 2, false, false));
        suite.addTest(new IntegerComparatorTests(2, 1, false, true));

        return suite;
    }

    public IntegerComparatorTests(Integer o1, Integer o2, boolean ascending, boolean isBefore) {
        super(o1, o2, ascending, isBefore);
    }

    @Override
    protected Comparator<Integer> createComparator(boolean ascending) {
        return new IntegerComparator(ascending);
    }
}

class AllTests {
    public static Test suite() {
        TestSuite suite = new TestSuite("sort");

        suite.addTestSuite(BubbleSortedTests.class);
        suite.addTestSuite(InsertionSortedTests.class);

        suite.addTestSuite(ArraySortableTests.class);
        suite.addTestSuite(ListSortableTests.class);

        suite.addTest(IntegerComparatorTests.suite());
        suite.addTest(ComparableComparatorTests.suite());

        return suite;
    }
}
