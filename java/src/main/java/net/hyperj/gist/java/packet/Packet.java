package net.hyperj.gist.java.packet;

import java.util.*;

public class Packet {

    public static List<Integer> generatePacketsByDoubleMean(int people, int money) {
        if (people > 0 && money > 0 && money >= people) {
            ArrayList<Integer> packets = new ArrayList<>();
            Random random = new Random();
            while (people > 1) {
                int pocket = random.nextInt(2 * money) / people;
                if (pocket > 0) {
                    packets.add(pocket);
                    money -= pocket;
                    people--;
                }
            }
            packets.add(money);
            return packets;
        }
        throw new RuntimeException("args err");
    }

    public static List<Integer> generatePacketsByLineCutting(int people, int money) {
        if (people > 0 && money > 0 && money >= people) {
            ArrayList<Integer> packets = new ArrayList<>();
            Random random = new Random();
            Set<Integer> points = new HashSet<>();
            while (points.size() < people - 1) {
                int point = random.nextInt(money - 1);
                if (point > 0) points.add(point);
            }
            Integer tmp = 0;
            for (Integer i : points) {
                packets.add(i - tmp);
                tmp = i;
            }
            packets.add(money - tmp);
            return packets;
        }
        throw new RuntimeException("args err");
    }

    public static void main(String[] args) {
        List<Integer> packets = generatePacketsByDoubleMean(10, 12);
        System.out.println(Arrays.toString(packets.toArray()));
        List<Integer> packets2 = generatePacketsByLineCutting(10, 12);
        System.out.println(Arrays.toString(packets2.toArray()));
    }
}
