public class Queue {
    private Long[] data;
    private int size = 0, head = 0, tail = 0;

    public Queue(int size) {
        this.data = new Long[size];
        this.size = size;
    }

    public boolean add(Long element) {
        if ((tail + 1) % size == head) {
            return false;
        }
        data[tail] = element;
        tail = (tail + 1) % size;
        return true;
    }

    public Long poll() {
        if (head == tail) {
            return null;
        }
        long ret = data[head];
        head = (head+1) % size;
        return ret;
    }
}

class Producer {
    private Queue queue;

    public Producer(Queue queue) {
        this.queue = queue;
    }

    public void produce(Long data) throws InterruptedException {
        while (!queue.add(data)) {
            Thread.sleep(100);
        }
    }
}

class Consumer {
    private Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
    }

    public void consume() throws InterruptedException {
        while (true) {
            Long data = queue.poll();
            if (data == null) {
                Thread.sleep(100);
            } else {
                // TODO:...消费数据的业务逻辑...
            }
        }
    }
}
