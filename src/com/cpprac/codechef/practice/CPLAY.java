package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CPLAY {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        while ((s = br.readLine()) != null && s.length() != 0) {
            int a = 0, b = 0, kicka = 0, kickb = 0, res = 0;
            int flag = 0;
            int la = 5, lb = 5;
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0 && s.charAt(i) == '1')
                    a++;
                else if (i % 2 == 1 && s.charAt(i) == '1')
                    b++;


                if (i % 2 == 0) {
                    kicka++;
                    la = 5 - kicka;
                } else {
                    kickb++;
                    lb = 5 - kickb;
                }

                if (b - a > la || a - b > lb) {
                    if (a > b)
                        System.out.println("TEAM-A " + (i + 1));
                    else
                        System.out.println("TEAM-B " + (i + 1));
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                continue;

            flag = 0;
            for (int i = 10; i < 20; i += 2) {
                if (s.charAt(i) != s.charAt(i + 1)) {
                    if (s.charAt(i) == '1')
                        System.out.println("TEAM-A " + (i + 2));
                    else
                        System.out.println("TEAM-B " + (i + 2));
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                System.out.println("TIE");
        }
    }
}
