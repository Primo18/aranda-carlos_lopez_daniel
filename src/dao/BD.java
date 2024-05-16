package dao;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
    private static final Logger logger= Logger.getLogger(BD.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/odontologos", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error al obtener la conexión a la base de datos", e);
        }
        return connection;
    }

    public static void crearTablas() {
        Connection connection = getConnection();
        try {
            String sqlCreate = "CREATE TABLE IF NOT EXISTS ODONTOLOGOS ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "NUMERO_MATRICULA VARCHAR(100), "
                    + "NOMBRE VARCHAR(100), "
                    + "APELLIDO VARCHAR(100))";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate);
            logger.info("Tabla ODONTOLOGOS creada o ya existe");
        } catch (SQLException e) {
            logger.error("Error al crear la tabla ODONTOLOGOS", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión a la base de datos", e);
            }
        }
    }

    public static void main(String[] args) {
        crearTablas();
    }
}
