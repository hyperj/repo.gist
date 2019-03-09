package net.hyperj.gist.roaringbitmap;

import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.longlong.LongBitmapDataProvider;
import org.roaringbitmap.longlong.LongIterator;
import org.roaringbitmap.longlong.Roaring64NavigableMap;

import java.text.DecimalFormat;

public class Examples {

    @Test
    public void basic() {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 3, 5, 7, 9, 8, 6, 4, 2, 1);
        RoaringBitmap rr2 = new RoaringBitmap();
        rr2.add(8L, 16L);

        println(RoaringBitmap.or(rr, rr2));
        println(RoaringBitmap.xor(rr, rr2));
        println(RoaringBitmap.and(rr, rr2));
        println(RoaringBitmap.andNot(rr, rr2));
    }

    @Test
    public void basic64() {
        LongBitmapDataProvider r = Roaring64NavigableMap.bitmapOf(1, 2, 10, 100);
        r.addLong(123);
        System.out.println(r.contains(1));
        System.out.println(r.contains(3));
        LongIterator i = r.getLongIterator();
        while (i.hasNext()) System.out.println(i.next());
    }

    public static final int universe_size = 262144;
    public static DecimalFormat F = new DecimalFormat("0.000");

    @Test
    public void testSuperSparse() {
        System.out.println("Sparse case... universe = [0," + universe_size + ")");
        RoaringBitmap r = new RoaringBitmap();
        int howmany = 100;
        int gap = universe_size / howmany;
        System.out.println("Adding " + howmany + " values separated by gaps of " + gap + "...");
        System.out.println("As a bitmap it would look like 1000...001000... ");
        for (int i = 1; i < howmany; i++) {
            r.add(i * gap);
        }
        System.out.println("Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / howmany));
        r.runOptimize();
        System.out.println("Bits used per value after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / howmany));
        System.out.println("An uncompressed bitset might use " + F.format(universe_size * 1.0 / howmany) + " bits per value set");
        System.out.println();
    }

    @Test
    public void testSuperDense() {
        System.out.println("Sparse case... universe = [0," + universe_size + ")");
        RoaringBitmap r = new RoaringBitmap();
        int howmany = 100;
        int gap = universe_size / howmany;
        for (int i = 1; i < howmany; i++) {
            r.add(i * gap + 1, ((i + 1) * gap));
        }
        System.out.println("Adding " + r.getCardinality() + " values partionned by " + howmany + " gaps of 1 ...");
        System.out.println("As a bitmap it would look like 01111...11011111... ");

        System.out.println("Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
        r.runOptimize();
        System.out.println("Bits used per value after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
        System.out.println("Bits used per gap after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / howmany));

        System.out.println("An uncompressed bitset might use " + F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
        System.out.println();
    }

    @Test
    public void testAlternating() {
        System.out.println("Alternating case... universe = [0," + universe_size + ")");
        RoaringBitmap r = new RoaringBitmap();
        for (int i = 1; i < universe_size; i++) {
            if (i % 2 == 0)
                r.add(i);
        }
        System.out.println("Adding all even values in the universe");
        System.out.println("As a bitmap it would look like 01010101... ");
        System.out.println("Bits used per value = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
        r.runOptimize();
        System.out.println("Bits used per value after run optimize = " + F.format(r.getSizeInBytes() * 8.0 / r.getCardinality()));
        System.out.println("An uncompressed bitset might use " + F.format(universe_size * 1.0 / r.getCardinality()) + " bits per value set");
        System.out.println();
    }

    public static void println(RoaringBitmap rb) {
        System.out.println(rb.toString());
    }
}
