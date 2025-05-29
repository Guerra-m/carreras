package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Carrera;
import util.ConexionBD; // clase para obtener conexión

public class CarreraDAOImpl implements CarreraDAO {

    @Override
    public void insertar(Carrera carrera) throws Exception {
        String sql = "INSERT INTO carreras(nombre, duracion) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, carrera.getNombre());
            ps.setInt(2, carrera.getDuracion());
            ps.executeUpdate();

            // Obtener id generado y setearlo en el objeto
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    carrera.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Carrera carrera) throws Exception {
        String sql = "UPDATE carreras SET nombre = ?, duracion = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, carrera.getNombre());
            ps.setInt(2, carrera.getDuracion());
            ps.setInt(3, carrera.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM carreras WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Carrera obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM carreras WHERE id = ?";
        Carrera carrera = null;
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    carrera = new Carrera();
                    carrera.setId(rs.getInt("id"));
                    carrera.setNombre(rs.getString("nombre"));
                    carrera.setDuracion(rs.getInt("duracion"));
                    // Aquí no cargo materias para no complicar, podés hacer otro método para eso
                }
            }
        }
        return carrera;
    }

    @Override
    public List<Carrera> obtenerTodos() throws Exception {
        String sql = "SELECT * FROM carreras";
        List<Carrera> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrera c = new Carrera();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDuracion(rs.getInt("duracion"));
                lista.add(c);
            }
        }
        return lista;
    }
}
