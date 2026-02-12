package task1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = List.of(2,4,6,8,10,12,14,16);

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        int partSize = numbers.size() / cores;
        System.out.println(partSize);

        Callable<Integer> task1 = () -> {
            int sum = 0;
            for (int i = 0; i < partSize; i++) {
                sum += numbers.get(i);
            }
            return sum;
        };

        Callable<Integer> task2 = () -> {
            int sum = 0;
            for (int i = partSize; i < 2 * partSize; i++) {
                sum += numbers.get(i);
            }
            return sum;
        };

        Callable<Integer> task3 = () -> {
            int sum = 0;
            for (int i = 2 * partSize; i < numbers.size(); i++) {
                sum += numbers.get(i);
            }
            return sum;
        };

        Future<Integer> f1 = executor.submit(task1);
        Future<Integer> f2 = executor.submit(task2);
        Future<Integer> f3 = executor.submit(task3);

        int totalSum = f1.get() + f2.get() + f3.get();
        System.out.println("Общая сумма = " + totalSum);
        executor.shutdown();
    }
 }
