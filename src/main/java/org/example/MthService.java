package org.example;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

/**
 * Esta clase proporciona servicios para realizar búsquedas lineales y binarias en listas de enteros.
 * Las búsquedas se realizan a través de solicitudes HTTP GET en las rutas '/linearsearch' y '/binarysearch'.
 * Se espera que las listas de enteros se pasen como parámetros de consulta 'list' y el valor a buscar se pase como parámetro de consulta 'value'.
 */
public class MthService {

    /**
     * Método principal que inicia el servicio.
     * Configura el puerto en el que se ejecutará el servicio y define las rutas para las búsquedas lineales y binarias.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        port(getPort());

        get("linearsearch", (req,res) -> {
            String linearString = req.queryParams("list");
            List<Integer> linearList = transformList(linearString);
            int number = Integer.parseInt(req.queryParams("value"));
            int position = linearSearch(linearList,  number);
            return formJson("Linear Search", linearList, number, position);
        });
        get("binarysearch", (req,res) -> {
            String linearString = req.queryParams("list");
            List<Integer> linearList = transformList(linearString);
            int number = Integer.parseInt(req.queryParams("value"));
            int position = binarySearch(linearList,  number);
            return formJson("Binary Search", linearList, number, position);
        });
    }

    /**
     * Obtiene el puerto del entorno o utiliza un puerto predeterminado si no se especifica uno.
     * @return El puerto en el que se ejecutará el servicio.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4572;
    }

    /**
     * Transforma una cadena de texto en una lista de enteros.
     * La cadena de texto debe contener valores separados por comas.
     * @param linearString Cadena de texto que representa la lista de enteros.
     * @return Lista de enteros.
     */
    public static List<Integer> transformList(String linearString){
        String[] linearList = linearString.split(",");
        List<Integer> res = new ArrayList<Integer>();
        for (String s : linearList) {
            res.add(Integer.parseInt(s));
        }
        return res;
    }

    /**
     * Realiza una búsqueda lineal en una lista de enteros para encontrar el valor especificado.
     * @param linearList Lista de enteros en la que se realizará la búsqueda.
     * @param number Valor que se está buscando.
     * @return La posición del valor en la lista si se encuentra, de lo contrario -1.
     */
    public static int linearSearch(List<Integer> linearList, int number) {
        int cont = 0;
        while (cont < linearList.size()) {
            if (linearList.get(cont).equals(number)) {
                return cont;
            }
            cont++;
        }
        return -1;
    }

    /**
     * Realiza una búsqueda binaria en una lista de enteros ordenada para encontrar el valor especificado.
     * @param linearList Lista de enteros ordenada en la que se realizará la búsqueda.
     * @param number Valor que se está buscando.
     * @return La posición del valor en la lista si se encuentra, de lo contrario -1.
     */
    public static int binarySearch(List<Integer> linearList, int number) {
        int inicio = 0;
        int fin = linearList.size() - 1;

        Collections.sort(linearList);

        while (inicio <= fin) {
            int mid = inicio + (fin - inicio) / 2;
            if (linearList.get(mid).equals(number)) {
                return mid;
            } else if (linearList.get(mid) < number) {
                inicio = mid + 1;
            } else {
                fin = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Forma una respuesta JSON con la información de la operación de búsqueda.
     * @param operation Tipo de operación de búsqueda ('Linear Search' o 'Binary Search').
     * @param list Lista de enteros utilizada en la búsqueda.
     * @param value Valor buscado.
     * @param output Posición del valor encontrado en la lista (o -1 si no se encontró).
     * @return Respuesta JSON que contiene la información de la operación de búsqueda.
     */
    public static String formJson(String operation, List<Integer> list, int value, int output){
        JSONObject json = new JSONObject();
        json.append("operation", operation);
        json.append("list", list);
        json.append("value", value);
        json.append("output", output);
        return json.toString();
    }
}
