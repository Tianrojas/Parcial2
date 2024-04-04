package org.example;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class ServiceProxy {

    public static int loadInt = 0;

    public static void main(String[] args) {
        port(getPort());
        get("linearsearch", (req,res) -> {
            toggleINT();
            HttpConnectionExample.setURL("http://mthservice"+loadInt+":6000/linearsearch?list="+req.queryParams("list")+"&value="+req.queryParams("value"));
            return HttpConnectionExample.getOutput();
        });
        get("binarysearch", (req,res) -> {
            toggleINT();
            HttpConnectionExample.setURL("http://mthservice"+loadInt+":6000/binarysearch?list="+req.queryParams("list")+"&value="+req.queryParams("value"));
            return HttpConnectionExample.getOutput();
        });
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }

    private static void toggleINT(){
        loadInt = (loadInt + 1) % 2;
    }

}
