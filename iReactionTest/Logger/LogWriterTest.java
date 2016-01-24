package Logger;

import Constant.FilePath;
import Database.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class LogWriterTest {

    LogWriter logWriter;

    @Before
    public void setUp() throws Exception {
        logWriter = new LogWriter(new File(FilePath.LOG_FILE_PATH.toString()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWrite() throws Exception {
        logWriter.write(Log.p(
                this.getClass().getSimpleName(),
                "wrote to file"));
    }

    @Test
    public void testClose() throws Exception {
        logWriter.close();
    }
}