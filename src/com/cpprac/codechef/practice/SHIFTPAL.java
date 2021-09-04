package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SHIFTPAL {

    public void solve() {
        Scan sn = new Scan();
        String s = sn.nextLine();
        if(!isPalin(s)) {
            System.out.println(s.length());
            System.exit(0);
        }
        else{
            for(int i=0;i<s.length();i++){
                StringBuilder sb = new StringBuilder(s.substring(i,s.length()));
//                System.out.println(sb.toString());
                if(!isPalin(sb.toString())) {
                    System.out.println(sb.length());
                    System.exit(0);
                }
            }
        }
        System.out.println(0);
    }

    private boolean isPalin(String s) {
        String reverse = new StringBuffer(s).reverse().toString();
        if(s.equals(reverse))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        SHIFTPAL solver = new SHIFTPAL();
        solver.solve();
    }

    class Scan {
        BufferedReader br;
        StringTokenizer st;

        public Scan() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
