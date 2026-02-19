package week5.service;

import week5.model.Result;
import week5.model.HttpResult;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class AggregatorService {

    private static final Logger logger = Logger.getLogger(AggregatorService.class.getName());

    private final HttpClientService httpClientService;
    private final ExecutorService executorService;

    public AggregatorService(HttpClientService httpClientService, ExecutorService executorService) {
        this.httpClientService = httpClientService;
        this.executorService = executorService;
    }

    public Result aggregate(List<String> urls) {
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger errorCount = new AtomicInteger();
        AtomicLong totalTime = new AtomicLong();

        List<Callable<Void>> tasks = urls.stream()
                .map(url -> (Callable<Void>) () -> {

                    HttpResult result = httpClientService.sendGet(url);

                    if (result.isSuccess()) {
                        successCount.incrementAndGet();
                    } else {
                        errorCount.incrementAndGet();
                    }

                    totalTime.addAndGet(result.getDuration());
                    return null;
                })
                .toList();

        try {

            List<Future<Void>> futures = executorService.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.severe("Error during aggregation: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        double average = urls.isEmpty() ? 0.0 :
                (double) totalTime.get() / urls.size();

        return new Result(
                successCount.get(),
                errorCount.get(),
                average);
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
