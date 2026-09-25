package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades = new ArrayList<>();

    public String getId() {
        return this.Id;
    }

    public String getTitulo() {
        return this.titulo;
    }


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
        if (tipo.equalsIgnoreCase("modelo.actividades.Charla")) {
            this.actividades.add(new Charla(id, titulo, cupo, orador));
        } else if (tipo.equalsIgnoreCase("modelo.actividades.Taller")) {
            this.actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public void crearActividad(int id, String titulo, int cupo, int nivel) {
        this.actividades.add(new modelo.actividades.Curso(id, titulo, cupo, nivel));
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



    public boolean persistirEvento() throws IOException {
        String nombreArchivo = this.Id + ".dat";
        FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo);
        ObjectOutputStream traductorObjetos = new ObjectOutputStream(archivoSalida);
        traductorObjetos.writeObject(this);
        traductorObjetos.close();
        archivoSalida.close();
        return true;
    }

    public static EventoUniversitario recuperarEvento(String Id) throws IOException, ClassNotFoundException{
       String nombreArchivo = Id + ".dat";
       FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
       ObjectInputStream traductorObjetos = new ObjectInputStream(archivoEntrada);
       EventoUniversitario eventoRecuperado = (EventoUniversitario) traductorObjetos.readObject();
       traductorObjetos.close();
       archivoEntrada.close();
       return eventoRecuperado;
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> listaFiltrada = new ArrayList<>();

        for (Actividad act : this.actividades) {

            if (tipo.isInstance(act)) {

                listaFiltrada.add(tipo.cast(act));
            }
        }
        return listaFiltrada;
    }

    public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {

        double costoTotal = 0;

        // 2. Recorremos la lista que recibimos por parámetro
        for (Actividad act : listaActividades) {

            // 3. Usamos el polimorfismo: cada actividad sabrá cómo calcular su propio costo
            costoTotal += act.calcularCostoMateriales();
        }

        return costoTotal;
    }
}
