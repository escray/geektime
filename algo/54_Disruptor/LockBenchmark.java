import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBenchmark {
    public static void Increment() {
        long counter = 0;
        long max = 500000000L;
        long start = System.currentTimeMillis();
        while (counter < max) {
            counter++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start) + "ms without lock");
    }

    public static void IncrementWithLock() {
        Lock lock = new ReentrantLock();
        long counter = 0;
        long max = 500000000L;
        long start = System.currentTimeMillis();
        while (counter < max) {
            if (lock.tryLock()) {
                counter++;
                lock.unlock();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start) + "ms with lock");
    }

    public static void IncrementAtomic() {
        AtomicLong counter = new AtomicLong(0);
        long max = 500000000L;
        long start = System.currentTimeMillis();
        while (counter.incrementAndGet() < max) {
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start) + "ms with cas");
    }

    public static void main(String[] args) {
        Increment();
        IncrementWithLock();
        IncrementAtomic();
    }

//    public boolean compareAndSet(final long expectValue, final long newValue) {
//        return UNSAFE.compareAndSwapLong(this, VALUE_OFFSET, expectedValue, newValue);
//    }
//
//    public long addAndGet(final long increment) {
//        long currentValue;
//        long newValue;
//        do {
//            currentValue = get();
//            newValue = currentValue + increment;
//        } while (!compareAndSet(currentValue, newValue));
//        return newValue;
//    }
}
