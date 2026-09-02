public class App {
    public static void main(String[] args) {

        EventoUniversitario eventoOriginal = new EventoUniversitario("EV-001", "Aprnde Java", 1500, false);
        EventoUniversitario eventoCopia = new EventoUniversitario(eventoOriginal);

        System.out.println("COPIA");
        eventoCopia.mostrarDatos();

        Estudiante alumno1 = new Estudiante("55123", "Leonel Cortez");
        Estudiante alumno2 = new Estudiante("55124", "Maria Carrasco");

        Sala salaPrincipal = new Sala("Sala B", 10);
        eventoOriginal.asignarSala(salaPrincipal);


        eventoOriginal.crearActividad(1, "Taller de Java", 30, "Taller", true, "");
        eventoOriginal.crearActividad(2, "Charla de Inteligencia Artificial", 50, "Charla", false, "Julian Fernandez");

        Actividad tallerJava = eventoOriginal.getActividades().get(0);
        tallerJava.inscribir(alumno1);
        tallerJava.inscribir(alumno2);

        System.out.println("RESUMEN Y COSTOS ACTUALIZADOS");
        eventoOriginal.mostrarDatos();
        System.out.println("Sala asignada: " + salaPrincipal.getNombre());

        System.out.println("LISTA DE INSCRIPCIONES");
        tallerJava.mostrarInscripciones();


        System.out.println("IDENTIFICACIÓN POLIMÓRFICA");
        for (Actividad act : eventoOriginal.getActividades()) {
            act.mostrarIdentificacion();
        }

        System.out.println("ESTADISTICAS TOTALES");
        System.out.println("Cantidad total de eventos: " + EventoUniversitario.getCantidadEventos());
    }
}