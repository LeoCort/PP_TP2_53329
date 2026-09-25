package modelo.actividades;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO=5;

    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo){
        this.id=id;
        this.titulo=titulo;
        this.cupoMaximo=cupoMaximo;
        this.inscripciones=new ArrayList<>();

    }

    public Inscripcion inscribir (Estudiante estudiante) throws CupoExcedidoException {
        if(this.inscripciones.size()>= this.cupoMaximo){
            throw new CupoExcedidoException("No hay cupo disponible en la actividad:"+ this.titulo);
        }
        Inscripcion nuevaInscripcion = new Inscripcion("confirmada",estudiante);
        this.inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }

    public void mostrarInscripciones() {
        System.out.println("inscriptos en la actividad" + this.titulo);
        for (Inscripcion insc : inscripciones) {

            System.out.println(insc.getEstudiante().getNombre() + insc.getEstudiante().getLegajo() + insc.getFecha());
        }
    }
    public final void mostrarIdentificacion(){
            System.out.println("modelo.actividades.Actividad: " + titulo + " | Tipo: " + getTipo());
        }


    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public String getTitulo() {
            return titulo;
        }

    public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }
}
