package net.hyperj.gist.leetcode;

public class Q38CountAndSay {

    public String countAndSay(int n) {
        return say("1", n);
    }

    private String say(String str, int n) {
        if (n == 1) return str;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char say = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (say != str.charAt(i)) {
                sb.append(count).append(say);
                count = 1;
                say = str.charAt(i);
            } else {
                count++;
            }
        }
        return say(sb.append(count).append(say).toString(), n - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Q38CountAndSay().countAndSay(6));
    }
}
