package Reaction.Thread;


import Constant.FilePath;
import Logger.Log;
import Reaction.Buffer.Buffers;
import Reaction.Reaction;
import Reaction.ReactionParser;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * A <i>Reaction Thread</i> is a thread of execution in a program. The Java
 * Virtual Machine allows an application to have multiple threads of
 * execution running concurrently.
 * <p/>
 * This class produces {@link Reaction} and {@link Log}
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
public class ReactionThread implements Runnable {

    private ReactionParser reactionParser;

    /**
     * Construct this class
     *
     * @throws FileNotFoundException if error occurs
     */
    public ReactionThread() throws FileNotFoundException {
        File file = new File(FilePath.REACTION_FILE_PATH.toString());
        reactionParser = new ReactionParser(file);
    }

    @Override
    public void run() {
        produce();
    }

    /**
     * Produce {@link Reaction} to {@link Reaction.Buffer.Buffers#reactionBuffer}
     */
    private void produce() {
        try {
            Reaction reaction;
            while ((reaction = reactionParser.readReaction()) != null) {
                Buffers.reactionBuffer.put(reaction);
                Buffers.logBuffer.put(Log.p(this.getClass().getSimpleName(),
                        "wrote UNIQUE-ID " + reaction.getUniqueId() + " to buffer."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
