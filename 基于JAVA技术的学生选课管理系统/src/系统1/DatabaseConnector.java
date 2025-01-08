package 系统1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // 数据库连接信息
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";
    private static final String USER_NAME = "sa";
    private static final String USER_PWD = "040517";

    // 静态方法用于获取数据库连接
    public static Connection getConnection() {
        Connection dbConn = null;
        try {
            Class.forName(DRIVER_NAME);
            dbConn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PWD);
            System.out.println("连接数据库成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}