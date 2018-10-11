package jdbc;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Класс для работы с базами данных.
 */
public class JDBC extends ScenarioSteps {

    private static Logger log = Logger.getLogger(JDBC.class.getName());
    private  Connection con;
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
    @Step
    public String executeValue(final String name) {
        connect();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from tabletest where NAME = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("value");
            } else {
                throw new IllegalArgumentException("Incorrect name variable = " + name);
            }
        } catch (SQLException e) {
            log.info("Incorrect SQL Query");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        } catch (SQLException e2) {
            log.info(String.format("Failed to connect to BD"));
            e2.printStackTrace();
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e3) {
                log.info("Error Connection close");
                e3.printStackTrace();
            }
        }

    }

    private void disconnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e3) {
            log.info("Error Connection close");
            e3.printStackTrace();
        }
    }
}
