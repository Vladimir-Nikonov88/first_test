package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Класс для работы с базами данных.
 */
public class JDBC {

    private static Logger log = Logger.getLogger(JDBC.class.getName());
    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;

    private  String url = "jdbc:MySQL://vm-autotest06t:3306/sandbox";
    private  String user = "test";
    private  String password = "test123";
    private  String pathDriver = "com.mysql.jdbc.Driver";

    /**
     * Метод ля получения значений из таблицы tabletest.
     * @param name
     * @return
     */
    public String executeValue(final String name) {
        connect();
        try {
            rs = stmt.executeQuery("select * from tabletest where NAME = '" + name + "'");
            while (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException e) {
            log.info("Incorrect name variable");
            e.printStackTrace();

        }

        disconnect();
        return null;
    }


    private void connect() {
        try {
            Class.forName(pathDriver);
        } catch (ClassNotFoundException e1) {
            log.info("Incorrect path Driver jdbc");
            e1.printStackTrace();
        }
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement();
        } catch (SQLException e2) {
            log.info(String.format("Failed to connect to BD"));
            e2.printStackTrace();
            try {
                con.close();
            } catch (SQLException e3) {
                log.info("Error Connection close");
                e3.printStackTrace();
            }
        }

    }

    private void disconnect() {
        try {
            con.close();
        } catch (SQLException e3) {
            log.info("Error Connection close");
            e3.printStackTrace();
        }
    }
}
