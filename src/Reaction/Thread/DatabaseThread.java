package Reaction.Thread;


import Logger.Log;
import Reaction.Buffer.Buffers;
import Reaction.Reaction;
import Reaction.ReactionDBWriter;

/**
 * A <i>Database Thread</i> is a thread of execution in a program. The Java
 * Virtual Machine allows an application to have multiple threads of
 * execution running concurrently.
 * <p/>
 * This class consumes {@link Reaction} and produce {@link Log}
 * object while method {@code run} is invoked.
 *
 * @author OGUNDE KEHINDE
 * @see java.lang.Thread
 * @see java.lang.Runnable
 * @see Reaction
 * @see Logger.Log
 * @see java.util.concurrent.ArrayBlockingQueue
 * @since 1.7
 */
public class DatabaseThread implements Runnable {

    private ReactionDBWriter reactionDBWriter;

    /**
     * Construct this class
     *
     * @throws Exception if error occurs
     */
    public DatabaseThread() throws Exception {
        reactionDBWriter = new ReactionDBWriter();
        reactionDBWriter.connectToReactionDatabase();
        reactionDBWriter.createReactionTable();
    }

    @Override
    public void run() {
        while (!Buffers.reactionBuffer.isEmpty())
            try {
                manageReaction(Buffers.reactionBuffer.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        Buffers.setRunning(false);
    }

    /**
     * Manages the specified elements
     * by consuming {@code Reaction} element from {@link Reaction.Buffer.Buffers#reactionBuffer}
     * and producing a {@code Log} element to {@link Reaction.Buffer.Buffers#logBuffer}
     *
     * @param reaction element to manage.
     */
    private void manageReaction(Reaction reaction) {
        consume(reaction);
        produce(reaction);
    }

    /**
     * Consumes the specified element
     *
     * @param reaction element to consume
     */
    private void consume(Reaction reaction) {
        reactionDBWriter.writeReactionToDatabase(reaction);
    }

    /**
     * Produce to {@link Reaction.Buffer.Buffers}
     *
     * @param reaction to produce with
     */
    private void produce(Reaction reaction) {
        try {
            Buffers.logBuffer.put(Log.p(this.getClass().getSimpleName(),
                    "collected UNIQUE-ID " + reaction.getUniqueId() +
                            " from buffer.", System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
