package modelo.actividades;

public class Charla extends Actividad {
    private String orador;

    public Charla(int id, String titulo, int cupoMaximo, String orador) {
            super(id, titulo, cupoMaximo);
            this.orador = orador;
    }

    @Override
        public double calcularCostoMateriales() {
            return 0;
    }

    @Override
        public String getTipo() {
            return "modelo.actividades.Charla";
    }
}
