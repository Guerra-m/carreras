package controlador;

import dao.CarreraDAO;
import dao.CarreraDAOImpl;
import model.Carrera;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
public class MenuCarrera {
    private static final Logger logger = Logger.getLogger(MenuCarrera.class.getName());
    private final CarreraDAO carreraDAO = new CarreraDAOImpl();
    private final Scanner scanner;

    public MenuCarrera(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("---Menu de  Carreras---");
            System.out.println("(1) Crear Carrera");
            System.out.println("(2) Listar Carreras");
            System.out.println("(3) Buscar Carrera por ID");
            System.out.println("(4) Actualizar Carrera");
            System.out.println("(5) Eliminar Carrera");
            System.out.println("(6) Volver al menu principal");
            System.out.print("Elija una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearCarrera();
                    break;
                case 2:
                    mostrarCarreras();
                    break;
                case 3:
                    buscarCarreraPorId();
                    break;
                case 4:
                    actualizarCarrera();
                    break;
                case 5:
                    eliminarCarrera();
                    break;
                case 6:
                    System.out.println("Volviendo...");
                    break;
                default:
                    logger.warning("Opción inválida.");
            }
        } while (opcion != 6);
    }
//Metodos CRUD de mi menu

    private void crearCarrera() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Duracion (anios): ");
            int duracion = Integer.parseInt(scanner.nextLine());

            Carrera carrera = new Carrera();
            carrera.setNombre(nombre);
            carrera.setDuracion(duracion);
            carrera.setMaterias(new ArrayList<>());

            carreraDAO.insertar(carrera);
            System.out.println("Carrera creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear carrera: " + e.getMessage());
        }
    }

    private void mostrarCarreras() {
        try {
            List<Carrera> carreras = carreraDAO.obtenerTodos();
            if (carreras.isEmpty()) {
                System.out.println("No hay carreras registradas.");
            }
            for (Carrera c : carreras) {
                System.out.println("ID: " + c.getId() + ", Nombre: " + c.getNombre() + ", Duracion: " + c.getDuracion());
            }
        } catch (Exception e) {
            System.out.println("Error al listar carreras: " + e.getMessage());
        }
    }

    private void buscarCarreraPorId() {
        try {
            System.out.print("Ingrese ID de la carrera: ");
            int id = Integer.parseInt(scanner.nextLine());
            Carrera c = carreraDAO.obtenerPorId(id);
            if (c != null) {
                System.out.println("ID: " + c.getId() + ", Nombre: " + c.getNombre() + ", Duracion: " + c.getDuracion());
            } else {
                System.out.println("Carrera no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar carrera: " + e.getMessage());
        }
    }

    private void actualizarCarrera() {
        try {
            System.out.print("Ingrese ID de la carrera a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Carrera c = carreraDAO.obtenerPorId(id);
            if (c == null) {
                System.out.println("Carrera no encontrada.");
                return;
            }
            System.out.print("Nuevo nombre (actual: " + c.getNombre() + "): ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva duracion (actual: " + c.getDuracion() + "): ");
            int duracion = Integer.parseInt(scanner.nextLine());

            c.setNombre(nombre);
            c.setDuracion(duracion);

            carreraDAO.actualizar(c);
            System.out.println("Carrera actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar carrera: " + e.getMessage());
        }
    }

    private void eliminarCarrera() {
        try {
            System.out.print("Ingrese ID de la carrera a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            carreraDAO.eliminar(id);
            System.out.println("Carrera eliminada.");
        } catch (Exception e) {
            System.out.println("Error al eliminar carrera: " + e.getMessage());
        }
    }
}
