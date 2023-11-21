package project01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Histogram {
    int[] frequency;
    int min, max;

    public Histogram(List<Integer> data) {
        // TODO: initialize attributes
        if(data == null && !data.isEmpty()){
            System.out.println("Input is empty");
        }else{
            this.min = Collections.min(data);
            this.max = Collections.max(data);
            this.frequency = new int[max-min+1];
            System.out.println("min is " + min);
            System.out.println("max is " + max);

            //initialize frequency first
            for(int item: frequency){
                item = 0;
            }

            //calculate all frequencies
            for(int item: data){
                frequency[item-min]++;
            }

        }

        for (int value : data) {
            // TODO: update frequencies here
            // Hint: look at method count below
        }
    }

    // Note: this constructor is provided as a convenience,
    //       it calls the primary constructor above
    public Histogram(Integer... data) {
        this(Arrays.asList(data));
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int count(int value) {
        int index = value - min;

        //value can be smaller than min
        // frequency will consist of all number between min and max, even though many of them have 0 occurrence
        if (0 <= index && index < frequency.length)
            return frequency[index];
        else
            return 0;
    }
}
