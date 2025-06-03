package main;

import java.io.IOException;
import java.util.logging.*;

public class ConfigLogger {
    public static void configurar() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.ALL);

        try {
            // Crea un FileHandler para guardar en archivo.log
            FileHandler fileHandler = new FileHandler("aplicacion.log", true); // true: modo append
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter()); // Formato de texto plano
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("No se pudo crear el archivo de log: " + e.getMessage());
        }
    }
}
