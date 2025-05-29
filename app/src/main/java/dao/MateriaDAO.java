package dao;

import java.util.List;
import model.Materia;

public interface MateriaDAO {
    void insertar(Materia materia) throws Exception;
    void actualizar(Materia materia) throws Exception;
    void eliminar(int id) throws Exception;
    Materia obtenerPorId(int id) throws Exception;
    List<Materia> obtenerPorCarreraId(int carreraId) throws Exception;
    List<Materia> obtenerTodos() throws Exception;
}
