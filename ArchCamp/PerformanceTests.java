import junit.extensions.TestDecorator;
import junit.extensions.TestSetup;
import junit.framework.Test;

public class PerformanceTests extends TestSetup {
    private long start;
    private int repeat;

    public PerformanceTests(Test test, int repeat) {
        super(new RepeatedTest(test, repeat));
        this.repeat = repeat;
    }

    protected void setUp() throws Exception {
        start = System.currentTimeMillis();
    }

    protected void tearDown() throws Exception {
        long duration = System.currentTimeMillis() - start;
        System.out.print("%s repeated %,d times, takes %,d ms\n",
                getTest(), repeat, duration);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("performance");

        Test bubbleTests = new TestSuite(BubbleSorterTests.class);
        Test insertionTests = new TestSuite(InsertionSorterTests.class);

        suite.addTest(new PerformanceTests(bubbleTests, 10000));
        suite.addTest(new PerformanceTests(insertionTests, 10000));

        return suite;
    }
}

public class RepeatedTest extends TestDecorator {
    @Override
    public void run(TestResult result) {
        for (int i = 0; i < fTimesRepeat; i++) {
            if (result.shouldStop()) {
                break;
            }
            super.run(result);
        }
    }
}

public class TestDecorator extends Assert implements Test {
    protected Test fTest;

    public TestDecorator(Test test) {
        fTest = test;
    }

    public void basicRun(TestResult result) {
        fTest.run(result);
    }

    public int countTestCase() {
        return fTest.countTestCases();
    }

    public void run(TestResult result) {
        basicRun(result);
    }

    @Override
    public String toString() {
        return fTest.toString();
    }

    public Test getTest() {
        return fTest;
    }
}
