package org.example;

import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;

/**
 * Esta clase contiene pruebas unitarias para la clase ServiceProxy.
 */
public class ServiceProxyTest {

    /**
     * Prueba el método getPort de la clase ServiceProxy cuando no se especifica un puerto en el entorno.
     * Se espera que el método devuelva el puerto predeterminado.
     */
    @Test
    public void testGetPort_DefaultPort() {
        int expectedPort = 4573;
        int actualPort = ServiceProxy.getPort();
        assertEquals(expectedPort, actualPort);
    }

    /**
     * Prueba el método toggleLoadInt de la clase ServiceProxy.
     * Se espera que el método alterne el índice del servidor actualmente seleccionado y lo actualice correctamente.
     * Este método utiliza reflexión para acceder al campo privado SERVER_COUNT de la clase ServiceProxy.
     * @throws NoSuchFieldException Si el campo SERVER_COUNT no está presente en la clase ServiceProxy.
     * @throws IllegalAccessException Si no se puede acceder al campo SERVER_COUNT debido a restricciones de acceso.
     */
    @Test
    public void testToggleLoadInt() throws NoSuchFieldException, IllegalAccessException {
        // Accede al campo privado SERVER_COUNT de la clase ServiceProxy utilizando reflexión
        Field serverCountField = ServiceProxy.class.getDeclaredField("SERVER_COUNT");
        serverCountField.setAccessible(true);
        int serverCount = serverCountField.getInt(null); // Obtiene el valor del campo SERVER_COUNT
        int initialLoadInt = ServiceProxy.loadInt; // Obtiene el valor inicial del índice del servidor seleccionado
        ServiceProxy.toggleLoadInt(); // Llama al método para alternar el índice del servidor
        int updatedLoadInt = ServiceProxy.loadInt; // Obtiene el valor actualizado del índice del servidor seleccionado
        // Comprueba que el valor actualizado es el esperado después de alternar el índice del servidor
        assertEquals((initialLoadInt + 1) % serverCount, updatedLoadInt);
    }
}
