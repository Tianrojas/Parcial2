package org.example;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

public class MthService {
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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4572;
    }

    public static List<Integer> transformList(String linearString){
        String[] linearList = linearString.split(",");
        List<Integer> res = new ArrayList<Integer>();
        for (String s : linearList) {
            res.add(Integer.parseInt(s));
        }
        return res;
    }

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

    public static String formJson(String operation, List<Integer> list, int value, int output){
        JSONObject json = new JSONObject();
        json.append("operation", operation);
        json.append("list", list);
        json.append("value", value);
        json.append("output", output);
        String response = json.toString();
        return response;
    }

}