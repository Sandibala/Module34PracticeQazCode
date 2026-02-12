package task3;

public class Main {
    public static void main(String[] args) throws Exception {
        CacheTask example = new CacheTask();
        System.out.println(example.invokeWithCache("sum", 5, 10));
        System.out.println(example.invokeWithCache("sum", 5, 10));
        System.out.println(example.invokeWithCache("sum", 0, 10));

    }
}
