package model;

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
            top = top.getSiguiente();
            this.tamano--;
            return elemento;
        }
    }

    /**
     * Añade un elemento a la pila
     *
     * @param elemento
     * @return
     */
    public T push(T elemento) {
        Nodo<T> aux = new Nodo<T>(elemento, top);
        top = aux;
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
            StringBuilder resultado = new StringBuilder();
            Nodo<T> aux = top;
            // Recorrer la pila
            while (aux != null) {
                resultado.append(aux.toString());
                aux = aux.getSiguiente();
            }
            return resultado.toString();
        }

    }

    public Carta verCartaSuperior() {
        return (Carta) top();
    }

    public void agregarCarta(T cartaSeleccionada) {
        push((cartaSeleccionada));
    }
}



