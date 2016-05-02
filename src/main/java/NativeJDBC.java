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

//            int a = 100;
//            while (a-- != 0) {
//                String sql = "INSERT INTO MyORM VALUES (%s,'ABCX%s')";
//                sql = String.format(sql, String.valueOf(a), String.valueOf(a));
//                stat.execute(sql);
//            }
            String sql = String.format("SELECT * FROM MyORM WHERE %s", new Object2PrepareSQLCondition(MyObject.class));
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(sql);
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, "ABCX1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NativeJDBC();
        } catch (Exception e) {
            System.out.print("Woops Something Wrong " + e);

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
}
