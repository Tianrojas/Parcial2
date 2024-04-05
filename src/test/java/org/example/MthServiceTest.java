package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

/**
 * Esta clase contiene pruebas unitarias para la clase MthService.
 */
public class MthServiceTest {

    /**
     * Prueba el método transformList de la clase MthService.
     * Se espera que el método transforme una cadena de texto en una lista de enteros correctamente.
     */
    @Test
    public void testTransformList() {
        String linearString = "1,2,3,4,5";
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = MthService.transformList(linearString);
        assertEquals(expected, result);
    }

    /**
     * Prueba el método linearSearch de la clase MthService.
     * Se espera que el método realice una búsqueda lineal y devuelva la posición del valor buscado en la lista.
     */
    @Test
    public void testLinearSearch() {
        List<Integer> linearList = Arrays.asList(1, 2, 3, 4, 5);
        int number = 3;
        int expected = 2;
        int result = MthService.linearSearch(linearList, number);
        assertEquals(expected, result);
    }

    /**
     * Prueba el método binarySearch de la clase MthService.
     * Se espera que el método realice una búsqueda binaria y devuelva la posición del valor buscado en la lista.
     */
    @Test
    public void testBinarySearch() {
        List<Integer> linearList = Arrays.asList(1, 2, 3, 4, 5);
        int number = 3;
        int expected = 2;
        int result = MthService.binarySearch(linearList, number);
        assertEquals(expected, result);
    }
}
