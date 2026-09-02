import java.util.ArrayList;
import java.util.List;

public abstract class Actividad {
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

    public Inscripcion inscribir(Estudiante estudiante){
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
            System.out.println("Actividad: " + titulo + " | Tipo: " + getTipo());
        }


    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public String getTitulo() {
            return titulo;
        }

}
