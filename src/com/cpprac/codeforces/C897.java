package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C897 {

    static String f0 = "What are you doing at the end of the world? Are you busy? Will you save us?";
    static String t1 = "What are you doing while sending \"";
    static String t2 = "\"? Are you busy? Will you send \"";
    static String t3 = "\"?";
    static long k;
    public static int solve(int n) {

        if (n == 0) {
            if(k<=f0.length()) {
                System.out.print(f0.charAt((int) k - 1));
                return 1;
            }
            else{
                k=k-f0.length();
                return 0;
            }
        }

        if (k <= t1.length()) {
            System.out.print(t1.charAt((int)(k - 1)));
            return 1;
        }
        k = k - t1.length();

        if(solve(n - 1)==1)
            return 1;

        if (k <= t2.length()) {
            System.out.print(t2.charAt(((int)k - 1)));
            return 1;
        }
        k = k - t2.length();

        if(solve(n - 1)==1)
            return 1;

        if (k <= t3.length()) {
            System.out.print(t3.charAt((int)k - 1));
            return 1;
        }
        else{
            k=k-t3.length();
            return 0;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine());


        int len2 = t1.length() + t2.length() + t3.length();

        //System.out.println(t1 + t1+f0+t2+f0+t3 + t2+ t1+f0+t2+f0+t3 + t3);
        while (q-- > 0) {
            String s[] = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            k = Long.parseLong(s[1]);

            if(solve(n)==0)
            System.out.print(".");
        }
    }
}