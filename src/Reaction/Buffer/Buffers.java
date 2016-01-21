package Reaction.Buffer;


import Logger.Log;
import Reaction.Reaction;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class represents the buffer
 * This class supports an optional fairness policy for ordering
 * waiting producer and consumer threads.
 * A bounded {@linkplain java.util.concurrent.BlockingQueue blocking queue} backed by an
 * array.  This queue orders elements FIFO (first-in-first-out).
 *
 * @author OGUNDE KEHINDE
 * @see {@link java.util.concurrent.ArrayBlockingQueue}
 * @since 1.7
 */
public class Buffers {

    public static final ArrayBlockingQueue<Reaction> reactionBuffer = new ArrayBlockingQueue<>(1);
    public static final ArrayBlockingQueue<Log> logBuffer = new ArrayBlockingQueue<>(1);

    private static boolean running = true;

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Buffers.running = running;
    }
}
