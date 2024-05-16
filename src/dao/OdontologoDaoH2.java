package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private BD bd = new BD();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = bd.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                odontologo.setId(keys.getInt(1));
                logger.info("Odontólogo guardado: " + odontologo);
            }
        } catch (Exception e) {
            logger.error("Error al guardar el odontólogo", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    logger.info("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexión a la base de datos", e);
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) {
        Connection connection = bd.getConnection();
        Odontologo odontologo = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                odontologo = new Odontologo(resultSet.getString("NUMERO_MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
                odontologo.setId(resultSet.getInt("ID"));
                logger.info("Odontólogo encontrado: " + odontologo);
            }
        } catch (Exception e) {
            logger.error("Error al buscar el odontólogo", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    logger.info("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexión a la base de datos", e);
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = bd.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.info("Odontólogo eliminado con ID: " + id);
        } catch (Exception e) {
            logger.error("Error al eliminar el odontólogo", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    logger.info("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexión a la base de datos", e);
            }
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = bd.getConnection();
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(resultSet.getString("NUMERO_MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
                odontologo.setId(resultSet.getInt("ID"));
                odontologos.add(odontologo);
            }
            logger.info("Lista de todos los odontólogos: " + odontologos);
        } catch (Exception e) {
            logger.error("Error al buscar todos los odontólogos", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    logger.info("Conexión a la base de datos cerrada");
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexión a la base de datos", e);
            }
        }
        return odontologos;
    }
}
