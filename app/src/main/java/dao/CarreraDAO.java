package dao;

import java.util.List;
import model.Carrera;

public interface CarreraDAO {
    void insertar(Carrera carrera) throws Exception;
    void actualizar(Carrera carrera) throws Exception;
    void eliminar(int id) throws Exception;
    Carrera obtenerPorId(int id) throws Exception;
    List<Carrera> obtenerTodos() throws Exception;
}
