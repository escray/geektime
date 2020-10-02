public class DoorAdapter {
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

class DoorTimerAdapter implements TimerClient {
    Door door;
    Timer timer;
    int timeout;
    public DoorTimerAdapter(Door door, Timer timer, int timeout) {
       this.door = door;
       this.timer = timer;
       this.timeout = timeout;
       timer.register(timeout, this);
    }
    public void timeout() {
        door.lock();
    }
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
