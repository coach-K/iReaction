package Database;


public enum Config {

    USERNAME("root"),
    PASSWORD("codeKenn"),
    URL("jdbc:mysql://localhost:3306"),
    DATABASE("reactiondb"),
    TABLE("reactions");

    private String CONSTANT;

    private Config(String CONSTANT){
        this.CONSTANT = CONSTANT;
    }

    @Override
    public String toString() {
        return CONSTANT;
    }
}
