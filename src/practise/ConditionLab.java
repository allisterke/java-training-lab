package practise;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLab {
    public static void main(String[] args) {
//        testPark();
        testCondition();
    }

    static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    static void testPark() {
        Unsafe unsafe = getUnsafe();
        if(unsafe == null) {
            System.out.println("cannot get unsafe");
            return;
        }
        Thread t1 = new Thread(() -> {
            unsafe.park(false, 0);
            System.out.println("I'm late.");
        });
        t1.start();
        try {
            for(int i = 0; i < 5; ++ i) {
                System.out.println("sleep " + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("gonna wake up t1");
        unsafe.unpark(t1);
    }

    static void testCondition() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Integer> list = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            lock.lock();
            try {
                while (list.isEmpty()) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(list.get(0));
                }
            } finally {
                lock.unlock();
            }
        });
        service.submit(() -> {
            lock.lock();
            try {
                list.add(new Random().nextInt(1024));
                condition.signal();
            } finally {
                lock.unlock();
            }
        });
        service.shutdown();
    }
}
