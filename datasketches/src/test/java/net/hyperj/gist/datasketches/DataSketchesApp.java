package net.hyperj.gist.datasketches;

import com.yahoo.sketches.frequencies.ErrorType;
import com.yahoo.sketches.frequencies.ItemsSketch;
import com.yahoo.sketches.quantiles.DoublesSketch;
import com.yahoo.sketches.quantiles.DoublesUnion;
import com.yahoo.sketches.quantiles.UpdateDoublesSketch;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class DataSketchesApp {

    @Test
    public void frequentItems() {
        ItemsSketch<String> sketch = new ItemsSketch<>(256);
        new Random().ints(1 << 20, 1, 3)
                .forEach(i -> sketch.update(String.valueOf(i)));
        ItemsSketch.Row<String>[] items = sketch.getFrequentItems(ErrorType.NO_FALSE_POSITIVES);
        System.out.println("Frequent Items: " + items.length);
        System.out.println(ItemsSketch.Row.getRowHeader());
        for (ItemsSketch.Row<String> row : items) {
            System.out.println(row.toString());
        }
    }

    @Test
    public void quantiles() {
        Random rand = new Random();
        UpdateDoublesSketch sketch = DoublesSketch.builder().build();
        for (int i = 0; i < 10000; i++) {
            sketch.update(rand.nextGaussian());
        }
        DoublesUnion union = DoublesUnion.builder().build();
        union.update(sketch);
        DoublesSketch result = union.getResult();
        System.out.println(result.toString());
        System.out.println("Min, Median, Max values");
        System.out.println(Arrays.toString(result.getQuantiles(new double[]{0, 0.5, 1})));
        System.out.println("Probability Histogram: estimated probability mass in 4 bins: (-inf, -2), [-2, 0), [0, 2), [2, +inf)");
        System.out.println(Arrays.toString(result.getPMF(new double[]{-2, 0, 2})));
        System.out.println("Frequency Histogram: estimated number of original values in the same bins");
        double[] histogram = result.getPMF(new double[]{-2, 0, 2});
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] *= result.getN();
        }
        System.out.println(Arrays.toString(histogram));
    }

}
