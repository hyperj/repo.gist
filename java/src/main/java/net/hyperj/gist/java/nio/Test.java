package net.hyperj.gist.java.nio;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {
        Server.start();
        Thread.sleep(1000);
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }
}