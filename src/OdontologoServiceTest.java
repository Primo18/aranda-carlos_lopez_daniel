import dao.BD;
import dao.OdontologoDaoH2;
import model.Odontologo;
import org.junit.Before;
import org.junit.Test;
import service.OdontologoService;
import dao.OdontologoDaoCollection;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Before
    public void setUp() {
//        odontologoService = new OdontologoService(new OdontologoDaoH2());
         odontologoService = new OdontologoService(new OdontologoDaoCollection());
        limpiarTablaOdontologos();
    }

    private void limpiarTablaOdontologos() {
        Connection connection = BD.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM ODONTOLOGOS");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGuardarYBuscarOdontologo() {
        Odontologo odontologo = new Odontologo("123", "John", "Doe");
        odontologoService.guardarOdontologo(odontologo);

        Odontologo odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        assertEquals("John", odontologoBuscado.getNombre());
    }

    @Test
    public void testListarTodosLosOdontologos() {
        odontologoService.guardarOdontologo(new Odontologo("123", "John", "Doe"));
        odontologoService.guardarOdontologo(new Odontologo("456", "Jane", "Smith"));

        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(2, odontologos.size());
    }
}
