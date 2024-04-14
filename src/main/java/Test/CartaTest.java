package Test;

import model.Carta;
import model.ColoresCartas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {

    private Carta carta;

    @BeforeEach
    public void setUp() {
        carta = new Carta(1, ColoresCartas.ROJO);
    }

    @Test
    public void testGetId() {
        assertEquals(0, carta.getId());
    }

    @Test
    public void testGetColor() {
        assertEquals(ColoresCartas.ROJO, carta.getColor());
    }

    @Test
    public void testGetNumero() {
        assertEquals(1, carta.getNumero());
    }

    @Test
    public void testIsEspecial() {
        assertFalse(carta.isEspecial());
    }


}