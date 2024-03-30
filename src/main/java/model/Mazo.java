package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.EfectoEspecial;
import model.ColoresCartas;
import model.PilaDinamica;


public class Mazo <T extends Carta> {
    private PilaDinamica<T> cartas;
    private PilaDinamica<T> cartasMonton;
    private int totalCartas;
    private int cartasPorColor;

    public Mazo() {
        this.cartas = new PilaDinamica<>();
        this.cartasMonton = new PilaDinamica<>();
        this.totalCartas = 108;
        this.cartasPorColor = 13;

        this.crearMazo();

        barajar();
    }

    private <T> void barajar() {
        int posAleatoria = 0;
        Carta c;

        while (!this.cartasMonton.isEmpty()) {
            this.cartas.push(this.cartasMonton.pop());
        }

        Carta cartasBarajar[] = new Carta[this.totalCartas];

        int indiceCarta = 0;
        while (!this.cartas.isEmpty()) {
            cartasBarajar[indiceCarta] = this.cartas.pop();
            indiceCarta++;
        }

        for (int i = 0; i < cartasBarajar.length; i++) {

            do {
                posAleatoria = generaNumeroEnteroAleatorio(0, totalCartas-1);
                c = cartasBarajar[posAleatoria];
            } while (c == null);

            this.cartas.push(T);
            cartasBarajar[posAleatoria] = null;

        }
    }

    //metodo para crear el mazo de cartas
    // opcion 1

    //@Override
    public void crearMazo(){
        ColoresCartas[] colores = ColoresCartas.values();

        for (ColoresCartas color : colores ){
            if (color != ColoresCartas.NEGRO){
                for(int i=0; i<this.cartasPorColor; i++){
                    if(i>9){
                        switch (i){
                            case 10:
                                this.cartas.push(new Carta(color, EfectoEspecial.MAS_DOS));
                                break;
                            case 11:
                                this.cartas.push(new Carta(color, EfectoEspecial.INVERTIR_SENTIDO));
                                break;
                            case 12:
                                this.cartas.push(new Carta(color, EfectoEspecial.SALTAR_TURNO));
                                break;

                        }
                    } else{
                        this.cartas.push(new Carta (i, color));
                    }
                }
            }else {
                for(int i=0; i<4; i++){
                    this.cartas.push(new Carta(color, EfectoEspecial.MAS_CUATRO));
                    this.cartas.push(new Carta(color, EfectoEspecial.CAMBIAR_COLOR));

                }
            }
        }
    }

    public static int generaNumeroEnteroAleatorio(int minimo, int maximo) {
        int num = (int) (Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
        return num;
    }

    public void mezclar() {
        Collections.shuffle(cartas); // Se utiliza el método shuffle de la clase Collections para mezclar la lista de cartas
    }
}
