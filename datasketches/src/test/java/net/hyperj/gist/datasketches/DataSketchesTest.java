package net.hyperj.gist.datasketches;

import com.yahoo.sketches.frequencies.ErrorType;
import com.yahoo.sketches.frequencies.ItemsSketch;
import com.yahoo.sketches.hll.HllSketch;
import com.yahoo.sketches.quantiles.DoublesSketch;
import com.yahoo.sketches.quantiles.DoublesUnion;
import com.yahoo.sketches.quantiles.UpdateDoublesSketch;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class DataSketchesTest {

    int count = 1 << 20;

    @Test
    public void frequent() {
        ItemsSketch<String> sketch = new ItemsSketch<>(64);
        new Random().ints(count, 1, 20)
                .forEach(i -> sketch.update(String.valueOf(i)));
        ItemsSketch.Row<String>[] items = sketch.getFrequentItems(ErrorType.NO_FALSE_POSITIVES);
        System.out.println("Frequent Items: " + items.length);
        System.out.println(ItemsSketch.Row.getRowHeader());
        for (ItemsSketch.Row<String> row : items) {
            System.out.println(row.toString());
        }
    }

    @Test
    public void hll() {
        HllSketch sketch = new HllSketch(10);
        new Random().ints(count, 1, 20)
                .forEach(i -> sketch.update(String.valueOf(i)));
        System.out.println("Union unique count estimate: " + sketch.getEstimate());
        System.out.println("Union unique count lower bound 95% confidence: " + sketch.getLowerBound(2));
        System.out.println("Union unique count upper bound 95% confidence: " + sketch.getUpperBound(2));
    }

    @Test
    public void quantiles() {
        Random rand = new Random();
        UpdateDoublesSketch sketch = DoublesSketch.builder().setK(128).build();
        for (int i = 0; i < count; i++) {
            sketch.update(rand.nextGaussian());
        }
        DoublesUnion union = DoublesUnion.builder().setMaxK(128).build();
        union.update(sketch);
        DoublesSketch result = union.getResult();
        double maxValue = result.getMaxValue();
        double minValue = result.getMinValue();
        if (doublesValid(maxValue, minValue)) {
            System.out.println(result.toString());
            System.out.println("Min, Median, Max values");
            System.out.println(Arrays.toString(result.getQuantiles(new double[]{0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0})));
            System.out.println("Probability Histogram: estimated probability mass in 4 bins: (-inf, -2), [-2, 0), [0, 2), [2, +inf)");
            System.out.println(Arrays.toString(result.getPMF(result.getQuantiles(new double[]{0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0}))));
            System.out.println("Frequency Histogram: estimated number of original values in the same bins");
            double[] histogram = result.getPMF(result.getQuantiles(new double[]{0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0}));
            for (int i = 0; i < histogram.length; i++) {
                histogram[i] *= result.getN();
            }
            System.out.println(Arrays.toString(histogram));
        }
    }

    public static boolean doublesValid(double... doubles) {
        AtomicBoolean flag = new AtomicBoolean(true);
        if (doubles != null && doubles.length > 0) {
            Arrays.stream(doubles).forEach(dou -> {
                if (Double.isNaN(dou) || Double.isInfinite(dou)) {
                    flag.set(false);
                }
            });
        } else {
            flag.set(false);
        }
        return flag.get();
    }

}
