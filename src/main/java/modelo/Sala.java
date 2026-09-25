package modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private String nombre;

    public Sala(String nombre, int id){
        this.id=id;
        this.nombre=nombre;
    }

    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
}
