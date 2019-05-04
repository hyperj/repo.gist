package net.hyperj.gist.java.search;

public class HashSearch extends Search {

    public static void main(String[] args) {
        println(new HashSearch());
    }

    int search(int[] array, int key) {
        if (array != null && array.length > 0) {
            int mod = array.length + 1;
            int[] hashed = hash(array, mod);
            int addr = key % mod;
            while (hashed[addr] != 0 && hashed[addr] != key) {
                addr = (++addr) % mod;
            }
            if (hashed[addr] != 0) {
                return addr;
            }
        }
        return -1;
    }

    private int[] hash(int[] array, int mod) {
        int[] hash = new int[mod];
        for (int i = 0; i < array.length; i++) {
            int addr = array[i] % mod;
            while (hash[addr] != 0) {
                addr = (++addr) % mod;
            }
            hash[addr] = array[i];
        }
        return hash;
    }

}
