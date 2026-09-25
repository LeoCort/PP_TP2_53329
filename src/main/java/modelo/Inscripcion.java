package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {

    private LocalDate fecha;
    private String estado;

    private Estudiante estudiante;

    public Inscripcion (String estado, Estudiante estudiante){
        this.estado=estado;
        this.estudiante=estudiante;
        this.fecha= LocalDate.now();
    }

    public LocalDate getFecha(){
        return fecha;
    }
    public String getEstado(){
        return estado;
    }
    public Estudiante getEstudiante(){
        return estudiante;
    }
}
