import configuration.MySQLConfig;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        borrarCliente(30);
    }

    public static void borrarCliente(int id) throws SQLException, ClassNotFoundException {
        MySQLConfig mySQLConfig = new MySQLConfig();
        Connection connection = mySQLConfig.getMySQLConnection();

        String sqlBorrarQuery = "{call delete_cliente(?,?,?)}";

        CallableStatement callableStatement =connection.prepareCall(sqlBorrarQuery);
        callableStatement.setInt(1, id);
        // Para recuperar variables de salida podemos usar el sgte comando
        callableStatement.registerOutParameter(2, Types.INTEGER);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);

        // ejecutamos y asignamos variables para los resultados de salida
        ResultSet rs = MySQLConfig.getMySQLResultSet(callableStatement);
        int filasAfectadas = callableStatement.getInt(2);
        boolean clienteExistente = callableStatement.getBoolean(3);

        // con la salida podemos confirmar si el cliente fue borrado o no
        if(clienteExistente == true){
            System.out.println("El cliente fue borrado de la existencia exitosamente");
            System.out.println(filasAfectadas + " columna(s) fueron afectadas");
        }
        else {
            System.out.println("El cliente no fue encontrado");
            System.out.println(filasAfectadas + " columna(s) fueron afectadas");
        }



    }
}
