package net.hyperj.gist.java.attach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.*;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.tools.attach.*;
import sun.tools.attach.HotSpotVirtualMachine;


public class HeapHistogram {

    public static void initial() {
        initial(3 * 60 * 1000, 10 * 1000);
    }

    public static void initial(long delay, long period) {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(histogram());
            }
        }, delay, period);
    }

    public static String histogram() {
        try {
            InputStream is = getHisto(getPid());
            return getText(new BufferedReader(new InputStreamReader(is)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        return name.substring(0, name.indexOf('@'));
    }

    private static InputStream getHisto(String vmid) throws IOException, AttachNotSupportedException {
        HotSpotVirtualMachine vm = (HotSpotVirtualMachine) VirtualMachine.attach(vmid);
        InputStream histo = vm.heapHisto(new Object[]{"-live"});
        vm.detach();
        return histo;
    }

    private static String getText(BufferedReader reader) throws IOException {
        StringBuilder histo = new StringBuilder();
        char[] buffer = new char[8192];
        int read;
        while ((read = reader.read(buffer)) != -1) {
            histo.append(buffer, 0, read);
        }
        reader.close();
        return histo.toString();
    }

}