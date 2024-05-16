package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoCollection implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoCollection.class);
    private List<Odontologo> odontologos = new ArrayList<>();
    private int currentId = 1;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(currentId++);
        odontologos.add(odontologo);
        logger.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) {
        for (Odontologo odontologo : odontologos) {
            if (odontologo.getId() == id) {
                logger.info("Odontólogo encontrado: " + odontologo);
                return odontologo;
            }
        }
        logger.info("Odontólogo no encontrado con ID: " + id);
        return null;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Lista de todos los odontólogos: " + odontologos);
        return new ArrayList<>(odontologos);
    }
}
