package modelo.actividades;

import modelo.Estudiante;
import certificacion.Certificable;


public class Curso extends Actividad implements Certificable {


    private int nivel;


    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }


    @Override
    public double calcularCostoMateriales() {
        return 1500.0 * this.nivel;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "El estudiante " + estudiante.getNombre() +
                " (Legajo: " + estudiante.getLegajo() + ") " +
                "ha completado exitosamente el Curso (Nivel " + this.nivel + "): " + this.getTitulo() + ". " +
                "Emitido por: " + ENTIDAD_EMISORA;
    }
}
