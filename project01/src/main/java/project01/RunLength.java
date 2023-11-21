package project01;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class RunLength {
    public static <T> List<Run<T>> encode(List<T> input) {
        // Note: you may assume without checking that input contains no null elements.
        // Java will complain if you try something like this:
        //     if(input.contains(null))

        List<Run<T>> result = new ArrayList<>();

        // TODO: implement this method
        if (input != null) {
            Map<T, Integer> map = new HashMap<>();
            for (T item : input) {
                if (item != null) {
                    map.put(item, map.getOrDefault(item, 0) + 1);
                    continue;
                } else {
                    System.out.println("there is null entry");
                    break;
                }
            }
            map.forEach(
                    (key, value) -> {
                        Run<T> run = new Run(value, key);
                        result.add(run);
                    }
            );
        }else{
            System.out.println("input is empty");
        }
        return result;

    }

    public static <T> List<T> decode(List<Run<T>> runs) {
        List<T> result = new ArrayList<>();

        // TODO: implement this method
        if(runs == null){
            System.out.println("input is empty");
        }else{
            for (Run<T> run : runs){
                for(int i = 0; i<run.count(); i++){
                    result.add(run.elem());
                }
            }
        }
        return result;
    }

    public static Integer sum(List<Run<Integer>> runs) {
        // TODO: implement this method (you may peek)
        Integer sum = 0;
        if(runs == null){
            System.out.println("Input is empty");
        }else{
            for(Run<Integer> run: runs){
                if(run != null){
                    sum = sum + run.elem() * run.count();
                }else {
                    System.out.println("there is null entry in the List");
                }
            }
        }
        return sum;
    }



}
