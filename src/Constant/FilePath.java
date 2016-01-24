package Constant;

/**
 * This class stores file path.
 */
public enum FilePath {
    PROJECT_DIR(System.getProperty("user.dir")),
    REACTION_FILE_PATH(PROJECT_DIR.toString() + "\\assets\\reactions.dat"),
    REACT_FILE_PATH(PROJECT_DIR.toString() + "\\assets\\react.txt"),
    DIFFERENT_FILE_PATH(PROJECT_DIR.toString() + "\\assets\\pairparsertest.txt"),
    LOG_FILE_PATH(PROJECT_DIR.toString() + "\\assets\\logging.txt");

    private String PATH;

    private FilePath(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public String toString() {
        return PATH;
    }
}
