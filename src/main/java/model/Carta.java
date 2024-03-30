package model;

import model.EfectoEspecial;
import model.ColoresCartas;
public class Carta {

    private ColoresCartas color;
    private int numero;
    private EfectoEspecial efecto;
    //el atributo es null cuando no lo contiene, sea el caso de ser comun


    public Carta (int numero, ColoresCartas color) {
        this.numero = numero;
        this.color = color;
    }
    public  Carta(ColoresCartas colo, EfectoEspecial efecto){
       // Carta(-1, color);
        this.efecto = efecto;
    }

    public ColoresCartas getColor(){
        return color;
    }

    public int getNumero(){
        return numero;
    }

    public EfectoEspecial getEfecto(){
        return efecto;

    }

    public boolean isEspecial(){
        return this.efecto != null;
    }

    @Override
    public String toString(){

        String estado ="";

        if (this.isEspecial()){
            switch (this.efecto){
                case MAS_DOS:
                    estado = "+2 " + "| " + color;
                    break;
                case SALTAR_TURNO:
                    estado = "Saltar Turno " + "| " + color;
                    break;
                case INVERTIR_SENTIDO:
                    estado = "Invertir Sentido " + "| " + color;
                    break;
                case MAS_CUATRO:
                    estado = "+4 "+ "| " + color;
                    break;
                case CAMBIAR_COLOR:
                    estado = "Cambiar Color " + "| " + color;
                    break;

            }

        } else {
            estado = numero + " | " + color;
        }

        return estado;
    }
}
