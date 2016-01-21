package Constant;

/**
 * This class stores file path.
 */
public enum FilePath {
    REACTION_FILE_PATH(System.getProperty("user.dir") + "\\assets\\reactions.dat"),
    REACT_FILE_PATH(System.getProperty("user.dir") + "\\assets\\react.txt"),
    DIFFERENT_FILE_PATH(System.getProperty("user.dir") + "\\assets\\pairparsertest.txt"),
    LOG_FILE_PATH(System.getProperty("user.dir") + "\\assets\\logging.txt");

    private String PATH;

    private FilePath(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public String toString() {
        return PATH;
    }
}
