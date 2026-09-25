import excepciones.CupoExcedidoException;
import modelo.Inscripcion;
import modelo.actividades.Actividad;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.util.List;

public class App {
    public static void main(String[] args) {

        EventoUniversitario eventoOriginal = new EventoUniversitario("EV-001", "Aprnde Java", 1500, false);
        EventoUniversitario eventoCopia = new EventoUniversitario(eventoOriginal);

        System.out.println("COPIA");
        eventoCopia.mostrarDatos();

        Estudiante alumno1 = new Estudiante("55123", "Leonel Cortez");
        Estudiante alumno2 = new Estudiante("55124", "Maria Carrasco");

        Sala salaPrincipal = new Sala("modelo.Sala B", 10);
        eventoOriginal.asignarSala(salaPrincipal);


        eventoOriginal.crearActividad(1, "modelo.actividades.Taller de Java", 1, "modelo.actividades.Taller", true, "");
        eventoOriginal.crearActividad(2, "modelo.actividades.Charla de Inteligencia Artificial", 50, "modelo.actividades.Charla", false, "Julian Fernandez");

        Actividad tallerJava = eventoOriginal.getActividades().get(0);
        System.out.println ("REALIZANDO INSCRIPCION");
        try {

            tallerJava.inscribir(alumno1);
            System.out.println("Inscripción exitosa para:" + alumno1.getNombre());


            System.out.println("Guardando evento...");
            eventoOriginal.persistirEvento();
            System.out.println("Evento guardado correctamente");


            System.out.println("Recuperando evento desde el disco...");
            EventoUniversitario eventoDesdeDisco = EventoUniversitario.recuperarEvento(eventoOriginal.getId());
            System.out.println("Evento recuperado con éxito" + eventoDesdeDisco.getTitulo());

            System.out.println("Intentando inscribir a otro alumno en el cupo lleno...");
            tallerJava.inscribir(alumno2);


            eventoOriginal.crearActividad(2, "Curso de Spring Boot", 10, 3);
            Actividad cursoSpring = eventoOriginal.getActividades().get(1);

            cursoSpring.inscribir(alumno1);
        }

        catch (CupoExcedidoException e) {
            System.out.println(e.getMessage());
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("No se encontró el archivo del evento");
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("El archivo recuperado no corresponde a un Evento Universitario válido.");
        }

        finally {
            System.out.println("PROCESO FINALIZADO");
        }

        System.out.println("RESUMEN Y COSTOS ACTUALIZADOS");
        eventoOriginal.mostrarDatos();
        System.out.println("modelo.Sala asignada: " + salaPrincipal.getNombre());

        System.out.println("LISTA DE INSCRIPCIONES");
        tallerJava.mostrarInscripciones();


        System.out.println("IDENTIFICACIÓN POLIMÓRFICA");
        for (Actividad act : eventoOriginal.getActividades()) {
            act.mostrarIdentificacion();
        }

        System.out.println("ESTADISTICAS TOTALES");
        System.out.println("Cantidad total de eventos: " + EventoUniversitario.getCantidadEventos());


        System.out.println("EMISIÓN DE CERTIFICADOS");

        for (Actividad act : eventoOriginal.getActividades()) {

            if (act instanceof certificacion.Certificable) {
                certificacion.Certificable actCertificable = (certificacion.Certificable) act;
                for (Inscripcion insc : act.getInscripciones()) {
                    String diploma = actCertificable.generarCertificado(insc.getEstudiante());
                    System.out.println(diploma);
                }

            } else {
                System.out.println("La actividad '" + act.getTitulo() + "' no emite certificados.");
            }
        }

        System.out.println("DATOS DEL EVENTO");
        eventoOriginal.mostrarDatos();

        System.out.println("FILTRADO Y COSTOS CON GENÉRICOS");


        List<Charla> listaCharlas = eventoOriginal.filtrarActividadesPorTipo(Charla.class);
        List<Taller> listaTalleres = eventoOriginal.filtrarActividadesPorTipo(Taller.class);
        List<Curso> listaCursos = eventoOriginal.filtrarActividadesPorTipo(Curso.class);


        System.out.println("Cantidades por tipo de actividad:");
        System.out.println("- Charlas: " + listaCharlas.size());
        System.out.println("- Talleres: " + listaTalleres.size());
        System.out.println("- Cursos: " + listaCursos.size());


        System.out.println("Costos de materiales por tipo:");
        System.out.println("Costo total en Charlas: " + eventoOriginal.calcularCostoMateriales(listaCharlas));
        System.out.println("Costo total en Talleres: " + eventoOriginal.calcularCostoMateriales(listaTalleres));
        System.out.println("Costo total en Cursos: " + eventoOriginal.calcularCostoMateriales(listaCursos));
    }

}