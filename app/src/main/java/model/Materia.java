package model;
public class Materia {
    private int id;
    private String nombre;
    private int anio; //Año en el que se cursa
    private Carrera carrera;
    public Materia() {
    }

    public Materia(int id, String nombre, int anio, Carrera carrera) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.carrera = carrera;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}
