package org.example;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class ServiceProxy {
    public static int loadInt = 0;
    private static final String[] SERVERS = new String[] { "http://localhost:4572/"};
    private static final int SERVER_COUNT = SERVERS.length;

    public static void main(String[] args) {
        port(getPort());
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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4573;
    }

    private static String getNextServerUrl() {
        String serverUrl = SERVERS[loadInt];
        toggleLoadInt();
        return serverUrl;
    }

    private static synchronized void toggleLoadInt() {
        loadInt = (loadInt + 1) % SERVER_COUNT;
    }
}
