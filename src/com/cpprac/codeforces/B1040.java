package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1040 {

    public void solve(Scan sn ) {

        int n= sn.nextInt(), k= sn.nextInt();

        int length = (k*2) +1;
        System.out.println((int)Math.ceil(n/(length*1.0)));
        int rem = n%length;
        if(rem!=0) {

            if(length >= n) {
                System.out.print((n / 2) + 1 + " ");
                n = n - length;
            }else
            {
                System.out.print(n-(Math.max(0, rem-(k+1))) + " ");
                n = n - Math.max(rem,k+1);
            }
        }
        while(n>0){
            n= n-k;
            System.out.print(n+" ");
            n= n-(k+1);
        }
    }

    public static void main(String[] args) {
        B1040 solver = new B1040();
        Scan sn = new Scan();
        solver.solve(sn);
    }

    static class Scan {
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