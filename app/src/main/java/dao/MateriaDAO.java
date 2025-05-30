package dao;

import java.util.List;
import model.Materia;

public interface MateriaDAO extends GenericDAO<Materia>{
    //Usa los métodos de la interfaz genérica + ...:
    List<Materia> obtenerPorCarreraId(int carreraId) throws Exception;
}
