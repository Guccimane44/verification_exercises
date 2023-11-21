package project01;

import java.util.List;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Property;

public class HistogramTests {
    // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
    public static void main(String[] args) {
        HistogramTests h = new HistogramTests();
        h.example();
    }

    @Test
    void example() {
        Histogram histogram = new Histogram(2, 3, 0, 5, 5, 6, -2, 9, 0, 3, 5);
        Assertions.assertEquals(-2, histogram.min());
        Assertions.assertEquals(9, histogram.max());

        // TODO: add some more checks here
        Assertions.assertEquals(1, histogram.count(-2));
        Assertions.assertEquals(0, histogram.count(-1));
        Assertions.assertEquals(2, histogram.count(0));
        Assertions.assertEquals(0, histogram.count(1));
        Assertions.assertEquals(1, histogram.count(2));
        Assertions.assertEquals(2, histogram.count(3));
        Assertions.assertEquals(0, histogram.count(4));
        Assertions.assertEquals(3, histogram.count(5));
        Assertions.assertEquals(1, histogram.count(6));
        Assertions.assertEquals(0, histogram.count(7));
        Assertions.assertEquals(0, histogram.count(8));
        Assertions.assertEquals(1, histogram.count(9));
        // TODO: add some more checks here
    }

    int countOccurrences(int value, List<Integer> data) {
        return (int) data.stream().filter(i -> i == value).count();
    }

    @Property // TODO: specify jqwik annotations for the parameters, note we have conveniently marked with ??? where to insert code
    void histogramDoesNotCrash(@ForAll @Size(min = 1,max = 128) List<@IntRange(min=-63, max=64) Integer> data) {
        new Histogram(data);
    }

    @Property // TODO: specify jqwik annotations for the parameters
    void histogramCount(@ForAll @Size(min = 1,max = 128)  List<@IntRange(min=-63, max=64)Integer> data, @ForAll @IntRange(min=-63, max=64) int value) {
        // TODO: check method count of class Histogram against reference implementation countOccurrences
        Histogram histogram = new Histogram(data);
        Assertions.assertEquals(histogram.count(value), countOccurrences(value, data));
    }

    @Property // TODO: specify jqwik annotations for the parameters
    void histogramRange(@ForAll @Size(min =1, max = 128) List<@IntRange(min=-63,max=64)Integer> data, @ForAll @IntRange(min = -63, max=64) int value) {
        // TODO: check that if countOccurrences(value) > 0 then
        //       value is between min and max of the corresponding histogram
        Assume.that(countOccurrences(value, data)>0);

        Histogram histogram = new Histogram(data);
        int min = histogram.min();
        int max = histogram.max();

        if(value < min || value > max){
            Assertions.fail("value is not in the range of min and max");
        }else{
            System.out.println("Hurray!");
        }
    }
}
