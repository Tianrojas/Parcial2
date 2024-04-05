package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Esta clase proporciona métodos para realizar solicitudes HTTP GET y manejar las respuestas.
 */
public class HttpConnectionExample {

    /** Agente de usuario para las solicitudes HTTP. */
    private static final String USER_AGENT = "Mozilla/5.0";

    /** URL de la solicitud HTTP GET. */
    private static String GET_URL = "";

    /**
     * Realiza una solicitud HTTP GET a la URL especificada y devuelve la respuesta como una cadena de texto.
     * @param urlString La URL a la que se enviará la solicitud HTTP GET.
     * @return La respuesta del servidor como una cadena de texto.
     */
    public static String sendGetRequest(String urlString) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
            System.out.println("Error sending GET request: " + e.getMessage());
        }

        System.out.println("GET DONE");
        return response.toString();
    }

    /**
     * Devuelve la salida de la solicitud HTTP GET realizada anteriormente.
     * @return La salida de la solicitud HTTP GET como una cadena de texto.
     */
    public static String getOutput() {
        return sendGetRequest(GET_URL);
    }

    /**
     * Establece la URL para la próxima solicitud HTTP GET.
     * @param urlString La URL para la próxima solicitud HTTP GET.
     */
    public static void setURL(String urlString) {
        GET_URL = urlString;
    }
}
