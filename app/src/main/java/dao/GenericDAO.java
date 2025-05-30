package dao;

import java.util.List;

public interface GenericDAO<T> {
    void insertar(T entidad) throws Exception;
    void actualizar(T entidad) throws Exception;
    void eliminar(int id) throws Exception;
    T obtenerPorId(int id) throws Exception;
    List<T> obtenerTodos() throws Exception;
}
