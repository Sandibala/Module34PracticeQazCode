package task3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheTask {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CacheResult {}
    private final Map<String, Object> cache = new HashMap<>();

    public Object invokeWithCache(String methodName, Object... args) throws Exception {

        Method method = this.getClass()
                .getDeclaredMethod(methodName, int.class, int.class);

        String key = Arrays.toString(args);

        if (method.isAnnotationPresent(CacheResult.class)) {

            if (cache.containsKey(key)) {
                System.out.println("We take it from cache");
                return cache.get(key);
            }

            Object result = method.invoke(this, args);
            cache.put(key, result);
            System.out.println("Saved to cache");
            return result;
        }

        return method.invoke(this, args);
    }

    @CacheResult
    public int sum(int a, int b) {
        System.out.println("Sum : ");
        return a + b;
    }
}
