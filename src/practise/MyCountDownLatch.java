package practise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {
    private volatile int full, fill;
    public MyCountDownLatch(int full) {
        this.full = full;
    }

    public synchronized void reach() {
        ++ fill;
        if(fill != full) {
            while (fill != full) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            notifyAll();
        }
    }

    static class MyRunnable implements Runnable {
        MyCountDownLatch latch;
        int index;
        public MyRunnable(MyCountDownLatch latch, int index) {
            this.latch = latch;
            this.index = index;
        }

        @Override
        public void run() {
            System.out.format("hello from %d\n", index);
            latch.reach();
            System.out.format("bye from %d\n", index);
        }
    }

    public static void main(String[] args) throws Exception {
        for(int t = 0; t < 10; ++ t) {
            ExecutorService service = Executors.newCachedThreadPool();
            int n = 5;
            MyCountDownLatch latch = new MyCountDownLatch(n);
            for (int i = 0; i < n; ++i) {
                service.submit(new MyRunnable(latch, t*10 + i));
            }
            service.shutdown();
            service.awaitTermination(100, TimeUnit.SECONDS);
        }
    }
}
