package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BIGSALE {

    public void solve() {
        Scan sn = new Scan();
        int t;
        t = sn.nextInt();
        while(t-->0){
            int n = sn.nextInt();
            double loss = 0.0;
            for(int i=0;i<n;i++) {
                int p = sn.nextInt(), q = sn.nextInt(), d = sn.nextInt();
                double actual = p * q;
                double discountAmount = (p * d) / 100.0;
                double temp = p + discountAmount;
                double discounted = temp - (temp * d) / 100.0;
                loss += actual - discounted * q;
            }
            System.out.println(loss);
        }
    }

    public static void main(String[] args) {
        BIGSALE solver = new BIGSALE();
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
