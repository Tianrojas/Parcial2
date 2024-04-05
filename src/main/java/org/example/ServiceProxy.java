package org.example;

import static spark.Spark.*;

/**
 * Esta clase actúa como un proxy de servicio para enrutar solicitudes de búsqueda a diferentes servidores.
 * Configura un servidor Spark que escucha en un puerto específico y define las rutas '/linearsearch' y '/binarysearch' para las solicitudes de búsqueda.
 * Utiliza un balanceo de carga round-robin para alternar entre los servidores disponibles para distribuir la carga de manera uniforme.
 */
public class ServiceProxy {
    /** Indica el índice del servidor actualmente seleccionado. */
    public static int loadInt = 0;

    /** Lista de URLs de los servidores disponibles. */
    private static final String[] SERVERS = new String[] { "http://34.226.209.7:4572/", "http://18.205.17.70:4572/" };

    /** Número total de servidores disponibles. */
    private static final int SERVER_COUNT = SERVERS.length;

    /**
     * Método principal que inicia el proxy de servicio.
     * Configura el puerto en el que se ejecutará el servidor Spark y define las rutas para las solicitudes de búsqueda.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("linearsearch", (req,res) -> {
            String serverUrl = getNextServerUrl();
            HttpConnectionExample.setURL(serverUrl + "linearsearch?list=" + req.queryParams("list") + "&value=" + req.queryParams("value"));
            return HttpConnectionExample.getOutput();
        });
        get("binarysearch", (req,res) -> {
            String serverUrl = getNextServerUrl();
            HttpConnectionExample.setURL(serverUrl + "binarysearch?list=" + req.queryParams("list") + "&value=" + req.queryParams("value"));
            return HttpConnectionExample.getOutput();
        });
    }

    /**
     * Obtiene el puerto del entorno o utiliza un puerto predeterminado si no se especifica uno.
     * @return El puerto en el que se ejecutará el servidor Spark.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4573;
    }

    /**
     * Obtiene la URL del siguiente servidor para enrutar la solicitud.
     * Utiliza un balanceo de carga round-robin para alternar entre los servidores disponibles.
     * @return La URL del siguiente servidor.
     */
    private static String getNextServerUrl() {
        String serverUrl = SERVERS[loadInt];
        toggleLoadInt();
        return serverUrl;
    }

    /**
     * Alterna el índice del servidor actualmente seleccionado para distribuir la carga uniformemente.
     */
    static synchronized void toggleLoadInt() {
        loadInt = (loadInt + 1) % SERVER_COUNT;
    }
}
