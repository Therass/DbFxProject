package parameters;

public class DbParameters {

    private static DbParameters instance = new DbParameters();

    private String DB_NAME;
    private boolean IS_SQL;
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_USERPASS;

    private DbParameters(){

    }

    public static DbParameters getInstance() {
        return instance;
    }


    public String getDB_NAME() {
        return DB_NAME;
    }

    public void setDB_NAME(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public boolean getIS_SQL() {
        return IS_SQL;
    }

    public void setIS_SQL(boolean IS_SQL) {
        this.IS_SQL = IS_SQL;
    }

    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    public void setJDBC_DRIVER(String JDBC_DRIVER) {
        this.JDBC_DRIVER = JDBC_DRIVER;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getDB_USERNAME() {
        return DB_USERNAME;
    }

    public void setDB_USERNAME(String DB_USERNAME) {
        this.DB_USERNAME = DB_USERNAME;
    }

    public String getDB_USERPASS() {
        return DB_USERPASS;
    }

    public void setDB_USERPASS(String DB_USERPASS) {
        this.DB_USERPASS = DB_USERPASS;
    }
}
