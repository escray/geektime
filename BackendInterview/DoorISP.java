public class DoorISP {
    public static void main(String[] args) {

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

class Door {
    public Door() {
    }
    void lock() {
    }
    void unlock() {
    }
    boolean isDoorOpen() {
        return false;
    }
}

class TimedDoor extends Door implements TimerClient {
    private int timeout;
    private Timer timer;
    public TimedDoor() {
        this.timer = new Timer();
        this.timeout = 10;
        timer.register(timeout, this);
    }

    public void timeout() {
        lock();
    }
}
