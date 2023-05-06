package configuration;

import java.sql.*;

public class MySQLConfig {
    public String host = "localhost";
    public String port = "3306";
    public String user = "root";
    public String password = "root";
    public String db = "develop";
    public String classDriver = "com.mysql.cj.jdbc.Driver";

    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName(classDriver);
        String url = "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password;
        return DriverManager.getConnection(url);
    }

    public static ResultSet getMySQLResultSet(CallableStatement callableStatement) throws ClassNotFoundException, SQLException {
        return callableStatement.executeQuery();
    }

    public ResultSet getMySQLResultSet(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public ResultSet getMySQLResultSet(PreparedStatement preparedStatement ) throws SQLException {
        return preparedStatement.executeQuery();
    }
}
