import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * 作者： 梁煜铭<p>
 * 创建时间： 16-4-26<p>
 * 类作用：PACKAGE_NAME<p>
 */
public class NativeJDBC {
    public NativeJDBC() throws SQLException, IOException {
        try (Connection connection = getConnection()) {
            Statement stat = connection.createStatement();
            for(int i = 0 ;i<100;i++){
                stat.executeUpdate("INSERT INTO HelloJDBC VALUES ('Hello World By JDBC')");
            }
            try (ResultSet resultSet = stat.executeQuery("SELECT * FROM HelloJDBC")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
            }
        }
    }

    public Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("/home/michaelssss/MyORM/target/classes/database.properties"))) {
            properties.load(in);
        }
        String drivers = properties.getProperty("java.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            new NativeJDBC();
        } catch (Exception e) {
            System.out.print("Woops Something Wrong " + e);

        }
    }
}
