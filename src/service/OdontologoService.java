package service;

import dao.iDao;
import model.Odontologo;
import org.apache.log4j.Logger;
import java.util.List;

public class OdontologoService {
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    private iDao<Odontologo> odontologoIDao;

    public OdontologoService(iDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        Odontologo guardado = odontologoIDao.guardar(odontologo);
//        logger.info("Odontólogo guardado: " + guardado);
        return guardado;
    }

    public void eliminarOdontologo(Integer id) {
        odontologoIDao.eliminar(id);
//        logger.info("Odontólogo eliminado con ID: " + id);
    }

    public Odontologo buscarOdontologo(Integer id) {
        Odontologo encontrado = odontologoIDao.buscar(id);
//        logger.info("Odontólogo encontrado: " + encontrado);
        return encontrado;
    }

    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = odontologoIDao.buscarTodos();
//        logger.info("Lista de todos los odontólogos: " + odontologos);
        return odontologos;
    }
}
