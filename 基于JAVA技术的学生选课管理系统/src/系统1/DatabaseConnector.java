package ϵͳ1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // ���ݿ�������Ϣ
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";
    private static final String USER_NAME = "sa";
    private static final String USER_PWD = "040517";

    // ��̬�������ڻ�ȡ���ݿ�����
    public static Connection getConnection() {
        Connection dbConn = null;
        try {
            Class.forName(DRIVER_NAME);
            dbConn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PWD);
            System.out.println("�������ݿ�ɹ���");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}