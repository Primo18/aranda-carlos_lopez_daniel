package service;

import dao.iDao;
import model.Odontologo;
import java.util.List;

public class OdontologoService {

    private final iDao<Odontologo> odontologoIDao;

    public OdontologoService(iDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        Odontologo guardado = odontologoIDao.guardar(odontologo);
        return guardado;
    }

    public Odontologo buscarOdontologo(Integer id) {
        Odontologo encontrado = odontologoIDao.buscar(id);
        return encontrado;
    }

    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = odontologoIDao.buscarTodos();
        return odontologos;
    }
}
