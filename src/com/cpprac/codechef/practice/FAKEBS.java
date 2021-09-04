package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FAKEBS {

    public void solve() {
        Scan sn = new Scan();
        int t= sn.nextInt();
        while(t-->0){

            int n=sn.nextInt(),q= sn.nextInt();

            long a[] = new long[n];
            Map<Long,Integer> map = new HashMap<>();
            for(int i=0;i<n;i++){
                a[i]=sn.nextLong();
                map.put(a[i],i);
            }



            for(int i=0;i<q;i++){

            }
        }

    }

    public static void main(String[] args) {
        FAKEBS solver = new FAKEBS();
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
