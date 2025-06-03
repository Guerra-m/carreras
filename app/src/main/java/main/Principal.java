package main;

import controlador.MenuCarrera;
import controlador.MenuMateria;
import dao.CarreraDAO;
import dao.CarreraDAOImpl;
import dao.MateriaDAO;
import dao.MateriaDAOImpl;
import java.util.Scanner;
import java.util.logging.Logger;
public class Principal {
    private static final Logger logger = Logger.getLogger(Principal.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final CarreraDAO carreraDAO = new CarreraDAOImpl();
    private static final MateriaDAO materiaDAO = new MateriaDAOImpl();
    
    public static void main(String[] args) {
        logger.info("La aplicación inicio.");
        MenuCarrera menuCarrera = new MenuCarrera(scanner);
        MenuMateria menuMateria = new MenuMateria(scanner, carreraDAO);

        int opcion;
        do {
            System.out.println("--- Menu Principal ---");
            System.out.println("(1) Ingresar o modificar Carreras");
            System.out.println("(2) Ingresar o modificar Materias");
            System.out.println("(3) Salir");
            System.out.print("Elija una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    menuCarrera.mostrarMenu();
                    break;
                case 2:
                    menuMateria.mostrarMenu();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        } while (opcion != 3);
    }
}
