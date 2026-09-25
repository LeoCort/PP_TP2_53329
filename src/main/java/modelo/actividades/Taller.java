package modelo.actividades;

import modelo.Estudiante;
import certificacion.Certificable;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
            super(id, titulo, cupoMaximo);
            this.requiereNotebook = requiereNotebook;
    }

    @Override
        public double calcularCostoMateriales() {
            return requiereNotebook ? 5000 : 2000;
    }

    @Override
        public String getTipo() {
            return "modelo.actividades.Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "El estudiante " + estudiante.getNombre() +
                " (Legajo: " + estudiante.getLegajo() + ") " +
                "ha completado exitosamente el Taller: " + this.getTitulo() + ". " +
                "Emitido por: " + ENTIDAD_EMISORA;
    }
}
