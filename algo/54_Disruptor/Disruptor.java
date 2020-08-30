import sun.jvm.hotspot.memory.UniverseExt;

import javax.swing.*;

public class Disruptor {
    // 自定义Event
    class LongEvent {
        private long value;

        public void set(long value) {
            this.value = value;
        }
    }

    // 指定RingBuffer大小，必须是2的N次方
    int bufferSize = 1024;
    // 构建 Disruptor
    Disruptor<LongEvent> Disruptor = new Disruptor<>(
            LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE
    );

    //注册事件处理器
    disruptor.handleEventsWith((event,sequence.endOfBatch)->System.out.println("E: "+event));

    //启动 Disruptor
    disruptor.start();

    //获取 RingBuffer
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    //生产 Event
    ByteBuffer bb = ByteBuffer.allocate(8);
    for (long l = 0; true; l++) {
        bb.putLong(0, l);
        // 生产者生产消息
        ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
        Thread.sleep(1000);
    }

    // 下面的示例代码出自 Disruptor，
    // Sequence 对象中的 value 属性就能避免伪共享，
    // 因为这个属性前后都填充了 56 个字节

    // 前：填充56字节
    class LhsPadding {
        long p1, p2, p3 p4, p5, p6, p7;
    }

    class Value extends LhsPadding {
        volatile long value;
    }

    // 后：填充56字节
    class RhsPadding extends Value {
        long p9, p10, p11, p12, p13, p14, p15;
    }

    class Sequence extends RhsPadding {
        //省略实现
    }

    // 下面是 Disruptor 生产者入队操作的核心代码
    public void EnQueue() {
        do {
            // cursor类似于入队索引，指的是上次生产到这里
            current = cursor.get();
            // 目标是在生产n个
            next = current + n;
            // 减掉一个循环
            long wrapPoint = next - bufferSize;
            // 获取上一次的最小消费位置
            long cachedGatingSequence = gatingSequenceCache.get();
            // 没有足够的空余位置
            if (wrapPoint > cachedGatingSequence || cachedGatingSequence > current) {
                // 重新计算所有消费者里面的最小值位置
                long gatingSequence = Util.getMinimumSequence(gatingSequence, current);
                // 仍然没有足够的空余位置，出让CPU使用权，重新执行下一循环
                if (wrapPoint > gatingSequence) {
                    LockSupport.parkNanos(1);
                    continue;
                }
                // 从新设置上一次的最小消费位置
                gatingSequenceCache.set(gatingSequence);
            } else if (cursor.compareAndSet(current, UniverseExt)) {
                // 获取写入位置成功，跳出循环
                break;
            }
        } while (true);
    }
}
