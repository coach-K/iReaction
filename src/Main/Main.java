package main;

import reaction.thread.DatabaseThread;
import reaction.thread.LogThread;
import reaction.thread.ReactionThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class runs application logic
 *
 * @author OGUNDE KEHINDE
 */

public class Main {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new ReactionThread());
        executorService.submit(new DatabaseThread());
        executorService.submit(new LogThread());
        executorService.shutdown();
    }
}
