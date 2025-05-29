package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Materia;
import model.Carrera;
import util.ConexionBD;

public class MateriaDAOImpl implements MateriaDAO {

    private CarreraDAO carreraDAO = new CarreraDAOImpl(); // para obtener carrera en obtenerPorId

    @Override
    public void insertar(Materia materia) throws Exception {
        String sql = "INSERT INTO materias(nombre, anio, carrera_id) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setInt(3, materia.getCarrera().getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    materia.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Materia materia) throws Exception {
        String sql = "UPDATE materias SET nombre = ?, anio = ?, carrera_id = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setInt(3, materia.getCarrera().getId());
            ps.setInt(4, materia.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM materias WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Materia obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM materias WHERE id = ?";
        Materia materia = null;
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    materia = new Materia();
                    materia.setId(rs.getInt("id"));
                    materia.setNombre(rs.getString("nombre"));
                    materia.setAnio(rs.getInt("anio"));
                    int carreraId = rs.getInt("carrera_id");
                    materia.setCarrera(carreraDAO.obtenerPorId(carreraId));
                }
            }
        }
        return materia;
    }

    @Override
    public List<Materia> obtenerPorCarreraId(int carreraId) throws Exception {
        String sql = "SELECT * FROM materias WHERE carrera_id = ?";
        List<Materia> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, carreraId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Materia m = new Materia();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setAnio(rs.getInt("anio"));
                    m.setCarrera(carreraDAO.obtenerPorId(carreraId));
                    lista.add(m);
                }
            }
        }
        return lista;
    }

    @Override
    public List<Materia> obtenerTodos() throws Exception {
        String sql = "SELECT * FROM materias";
        List<Materia> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Materia m = new Materia();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                int carreraId = rs.getInt("carrera_id");
                m.setCarrera(carreraDAO.obtenerPorId(carreraId));
                lista.add(m);
            }
        }
        return lista;
    }
}
