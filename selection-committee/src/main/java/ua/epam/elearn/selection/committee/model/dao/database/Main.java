package ua.epam.elearn.selection.committee.model.dao.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put("2","fdshvcbx");
         map.put("3","ewrewfds");
        map.put("4","xcvfd");
        map.put("5","dsaaAfdsf");
        map.put("1","ASdaksdas");
        map.put("6","trefdAsfdsf");

        System.out.println(getList(map).toString());

    }

    static public List<String> getList(Map<String, String> map){
        return map
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .skip(16)
                .filter(e -> e.contains("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    //Implement method that has as a parameter HashMap of strings and return List of sorted values that contains char ‘A’
}
