package dao;

import java.util.List;
import model.Materia;

public interface MateriaDAO extends GenericDAO<Materia>{
    //Usa los m�todos de la interfaz gen�rica + ...:
    List<Materia> obtenerPorCarreraId(int carreraId) throws Exception;
}
