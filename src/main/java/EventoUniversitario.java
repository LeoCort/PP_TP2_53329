import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades = new ArrayList<>();


    public EventoUniversitario(String Id, String nombre,  double costo, boolean esGratuito) {
        this.Id = Id;
        titulo = nombre;
        this.gratuito = esGratuito;
        this.costoBase = gratuito ? 0 : costo;
         cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otroEvento) {
        this.Id = otroEvento.Id + "-COPIA";
        this.titulo = otroEvento.titulo;
        this.costoBase = otroEvento.costoBase;
        this.gratuito = otroEvento.gratuito;
        cantidadEventos++;
    }


    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }

        double costoTotalActividades = 0;
        for (Actividad act : actividades) {
            costoTotalActividades += act.calcularCostoMateriales();
        }

        return (costoBase + costoTotalActividades) * 1.21;
    }

    public void asignarSala(Sala sala){
        this.sala=sala;

    }
    public void crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook, String orador) {
        if (tipo.equalsIgnoreCase("Charla")) {
            this.actividades.add(new Charla(id, titulo, cupo, orador));
        } else if (tipo.equalsIgnoreCase("Taller")) {
            this.actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public List<Actividad> getActividades() {
        return actividades;
    }
    public void mostrarDatos(){

        System.out.println("nombre del evento:"+ titulo);
        System.out.println("costo estimado:"+ calcularCostoEstimado());
        System.out.println("¿Evento gratuito?:"+ gratuito);
        System.out.println("Evento:"+ Id);
        System.out.println("cantidad de eventos:"+ cantidadEventos);
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }
}
