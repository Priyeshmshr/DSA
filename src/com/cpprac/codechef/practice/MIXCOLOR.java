package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MIXCOLOR {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while(t-->0){
            int n = sn.nextInt();
            long arr [] = new long[n];
            for(int i=0;i<n;i++){
                arr[i] = sn.nextLong();
            }
            Arrays.sort(arr);
            long max = arr[n-1],count=0;
            int prev = 0;
            for(int i=1;i<n;i++){
                if(arr[i]==arr[prev]){
                    arr[i]=arr[i]+max;
                    max = arr[i];
                    count++;
                }else
                    prev = i;
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        MIXCOLOR solver = new MIXCOLOR();
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
