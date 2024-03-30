package model;

//import java.util.Stack;

/**
 * Clase Pila Dinamica
 * @param <T>
 */
public class PilaDinamica<T> {
    private Nodo<T> top; //es el ultimo nodo que se añade
    private int tamano;

    // Constructor
    public PilaDinamica() {
        top = null; //sin elementos
        this.tamano = 0;
    }

    // Métodos
    /**
     * Indica si esta vacia o no
     * @return
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Indica el tamaño
     * @return
     */
    public int size(){
        return this.tamano;
    }

    /**
     *Retorna el primero en la pila (el que esta mas arriba)
     * @return
     */
    public T top() {
        if (isEmpty()) {
            return null;
        } else {
            return top.getElemento();
        }
    }

    /**
     * Saca y Retorna el elemento mas arriba de la pila
     * @return
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            T elemento = top.getElemento();
            Nodo<T> aux = top.getSiguiente();
            top = null; // borra
            top = aux; // actualiza el top
            this.tamano--;
            return elemento;
        }
    }

    /**
     * Añade un elemento a la pila
     * @param elemento
     * @return
     */
    public T push(T elemento) {

        Nodo<T> aux = new Nodo<>(elemento, top);
        top = aux; //actualizar el top
        this.tamano++;
        return aux.getElemento();
    }

    /**
     * Retorna el estado de la pila
     * @return
     */
    public String toString() {

        if (isEmpty()) {
            return "La pila esta vacia";
        } else {
            String resultado = "";
            Nodo<T> aux = top;
            //Recorrer la pila
            while (aux != null) {
                resultado += aux.toString();
                aux = aux.getSiguiente();
            }
            return resultado;
        }

    }


    //public <T> void push(T c) {
    //}
}
