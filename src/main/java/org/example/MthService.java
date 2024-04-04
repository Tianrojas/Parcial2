package org.example;


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
            return String.valueOf(linearSerch(linearList,  number));
        });
        get("binarysearch", (req,res) -> {
            String linearString = req.queryParams("list");
            List<Integer> linearList = transformList(linearString);
            int number = Integer.parseInt(req.queryParams("value"));
            return String.valueOf(binarySerch(linearList,  number));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }

    public static List<Integer> transformList(String linearString){
        String[] linearList = linearString.split(",");
        List<Integer> res = new ArrayList<Integer>();
        for (String s : linearList) {
            res.add(Integer.parseInt(s));
        }
        return res;
    }

    public static int linearSerch(List<Integer> linearList, int number){
        int cont = 0;
        while (cont <= linearList.size()){
            if (linearList.get(cont).equals(number)){
                return cont;
            }
            cont++;
        }
        return -1;
    }

    public static int binarySerch(List<Integer> linearList, int number){
        int inicio = 0;
        int fin = linearList.size() - 1;

        Collections.sort(linearList);

        while (fin == inicio){
            int mid = (fin - inicio)/2;
            if (linearList.get(mid).equals(number)){
                return mid;
            } else if (linearList.get(mid)>number){
                inicio = mid;
                fin = linearList.size() - 1;
            } else {
                inicio = 0;
                fin = mid;
            }
        }
        return -1;
    }

}