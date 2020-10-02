public class DoorBruteforce {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Door door = new Door();
        timer.register(10, door);
    }
}

class Timer {
    public Timer() {
    }

    void register(int timeout, TimerClient client) {
    }
}

interface TimerClient {
    public void timeout();
}

class Door implements TimerClient {
    public Door() {
    }
    void lock() {
    }
    void unlock() {
    }
    boolean isDoorOpen() {
        return false;
    }
    public void timeout() {
        lock();
    }
}
