package model;

import java.util.ArrayList;

/**
 * @author Martín_Guerra
 */
 
public class Carrera {
    private int id; 
    private String nombre;
    private int duracion;
    private ArrayList<Materia> materias;//Relación 1 a n

    public Carrera() {
    }

    public Carrera(int id, String nombre, int duracion, ArrayList<Materia> materias) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.materias = materias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }
    
}
