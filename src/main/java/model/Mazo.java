package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    private void barajar() {
        List<T> listaCartas = new ArrayList<>();
        while (!this.cartasMonton.isEmpty()) {
            this.cartas.push(this.cartasMonton.pop());
        }

        while (!this.cartas.isEmpty()) {
            listaCartas.add(this.cartas.pop());
        }

        Collections.shuffle(listaCartas);

        for (T carta : listaCartas) {
            this.cartas.push(carta);
        }
    }

    //metodo para crear el mazo de cartas
    // opcion 1

    public void crearMazo(){
        ColoresCartas[] colores = ColoresCartas.values();

        for (ColoresCartas color : colores ){
            if (color != ColoresCartas.NEGRO){
                for(int i=0; i<this.cartasPorColor; i++){
                    if(i>9){
                        switch (i){
                            case 10:
                                this.cartas.push((T) new Carta(color, EfectoEspecial.MAS_DOS));
                                break;
                            case 11:
                                this.cartas.push((T)new Carta(color, EfectoEspecial.INVERTIR_SENTIDO));
                                break;
                            case 12:
                                this.cartas.push((T)new Carta(color, EfectoEspecial.SALTAR_TURNO));
                                break;

                        }
                    } else{
                        this.cartas.push((T)new Carta (i, color));
                    }
                }
            }else {
                for(int i=0; i<4; i++){
                    this.cartas.push((T)new Carta(color, EfectoEspecial.MAS_CUATRO));
                    this.cartas.push((T)new Carta(color, EfectoEspecial.CAMBIAR_COLOR));

                }
            }
        }
    }

    public Carta robarCarta(){
        return this.cartas.pop();
    }
    public void mezclar() {
        List<T> listaCartas = new ArrayList<>();
        while (!this.cartas.isEmpty()) {
            listaCartas.add(this.cartas.pop());
        }
        Collections.shuffle(listaCartas);
        for (T carta : listaCartas) {
            this.cartas.push(carta);
        }    }

    public void agregarCarta(Carta cartaInicial) {
        this.cartasMonton.push((T) cartaInicial);
    }


}
