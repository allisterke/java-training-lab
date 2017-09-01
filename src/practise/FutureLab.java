package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FutureLab {
    static class SquareAdder implements Callable<Long> {
        long begin, end;
        public SquareAdder(long begin, long end) {
            this.begin = begin;
            this.end = end;
//            System.out.printf("%d %d\n", begin, end);
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            while (begin <= end) {
                sum += begin * begin;
                ++ begin;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.stream(args).collect(Collectors.toList()));
        ExecutorService service = Executors.newCachedThreadPool();
        long begin = 1, end = 1000L * 1000 * 1000 * 100;
        List<Future<Long>> list = new ArrayList<>();
        int N = args.length >= 1 ? Integer.valueOf(args[0]) : 20;
        for(int i = 0; i < N; ++ i) {
            list.add(service.submit(
                new SquareAdder(begin + i * (end - begin + 1) / N, begin + (i+1) * (end - begin + 1) / N - 1)));
        }
        long result = 0;
        for(Future<Long> future : list) {
            try {
                result += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        System.out.println(result);
    }
}
