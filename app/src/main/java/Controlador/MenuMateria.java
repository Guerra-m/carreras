package controlador;

import dao.CarreraDAO;
import dao.MateriaDAO;
import dao.MateriaDAOImpl;
import model.Carrera;
import model.Materia;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
public class MenuMateria {
    private static final Logger logger = Logger.getLogger(MenuMateria.class.getName());
    private final MateriaDAO materiaDAO = new MateriaDAOImpl();
    private final CarreraDAO carreraDAO;
    private final Scanner scanner;

    public MenuMateria(Scanner scanner, CarreraDAO carreraDAO) {
        this.scanner = scanner;
        this.carreraDAO = carreraDAO;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("---Menu de  Materias---");
            System.out.println("(1) Crear Materia");
            System.out.println("(2) Listar Materias");
            System.out.println("(3) Buscar Materia por ID");
            System.out.println("(4) Actualizar Materia");
            System.out.println("(5) Eliminar Materia");
            System.out.println("(6) Volver al menu principal");
            System.out.print("Elija una  opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearMateria();
                    break;
                case 2:
                    mostrarMaterias();
                    break;
                case 3:
                    buscarMateriaPorId();
                    break;
                case 4:
                    actualizarMateria();
                    break;
                case 5:
                    eliminarMateria();
                    break;
                case 6:
                    logger.info("Volviendo...");
                    break;
                default:
                    logger.warning("Opción inválida");
                    break;
            }
        } while (opcion != 6);
    }
//Metodos CRUD de mi menu

    private void crearMateria() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Anio: ");
            int anio = Integer.parseInt(scanner.nextLine());
            System.out.print("ID de la Carrera a la que pertenece: ");
            int idCarrera = Integer.parseInt(scanner.nextLine());

            Carrera carrera = carreraDAO.obtenerPorId(idCarrera);
            if (carrera == null) {
                System.out.println("Carrera no encontrada. No se puede crear la materia.");
                return;
            }

            Materia materia = new Materia();
            materia.setNombre(nombre);
            materia.setAnio(anio);
            materia.setCarrera(carrera); // ASOCIAMOS LA CARRERA

            materiaDAO.insertar(materia);
            System.out.println("Materia creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear materia: " + e.getMessage());
        }
    }

    private void mostrarMaterias() {
        try {
            List<Materia> materias = materiaDAO.obtenerTodos();
            if (materias.isEmpty()) {
                System.out.println("No hay materias registradas.");
            }
            for (Materia m : materias) {
                System.out.println("ID: " + m.getId() + ", Nombre: " + m.getNombre() + ", Anio: " + m.getAnio());
            }
        } catch (Exception e) {
            System.out.println("Error al listar materias: " + e.getMessage());
        }
    }

    private void buscarMateriaPorId() {
        try {
            System.out.print("Ingrese ID de la materia: ");
            int id = Integer.parseInt(scanner.nextLine());
            Materia m = materiaDAO.obtenerPorId(id);
            if (m != null) {
                System.out.println("ID: " + m.getId() + ", Nombre: " + m.getNombre() + ", Anio: " + m.getAnio());
            } else {
                System.out.println("Materia no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar materia: " + e.getMessage());
        }
    }

    private void actualizarMateria() {
        try {
            System.out.print("Ingrese ID de la materia a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Materia m = materiaDAO.obtenerPorId(id);
            if (m == null) {
                System.out.println("Materia no encontrada.");
                return;
            }
            System.out.print("Nuevo nombre (actual: " + m.getNombre() + "): ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo anio (actual: " + m.getAnio() + "): ");
            int anio = Integer.parseInt(scanner.nextLine());

            m.setNombre(nombre);
            m.setAnio(anio);

            materiaDAO.actualizar(m);
            System.out.println("Materia actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar materia: " + e.getMessage());
        }
    }

    private void eliminarMateria() {
        try {
            System.out.print("Ingrese ID de la materia a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            materiaDAO.eliminar(id);
            System.out.println("Materia eliminada.");
        } catch (Exception e) {
            System.out.println("Error al eliminar materia: " + e.getMessage());
        }
    }
}
